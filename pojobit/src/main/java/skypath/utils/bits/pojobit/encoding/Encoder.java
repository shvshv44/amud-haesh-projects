package skypath.utils.bits.pojobit.encoding;

import skypath.utils.bits.pojobit.annotations.Bound;
import skypath.utils.bits.pojobit.bits.BitBuffer;
import skypath.utils.bits.pojobit.codecs.Codec;
import skypath.utils.bits.pojobit.exceptions.BitEncodingException;
import skypath.utils.bits.pojobit.reflection.ReflectionHelper;
import skypath.utils.bits.pojobit.utils.CodecsFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Encoder {

    private CodecsFactory factory;
    private ReflectionHelper reflect;

    public Encoder(CodecsFactory factory) {
        this.factory = factory;
        reflect = new ReflectionHelper();
    }

    public byte [] serialize (Object obj) {
        try {
            if (obj != null && obj.getClass() != Object.class) {
                BitBuffer bitBuffer = new BitBuffer();
                reqursiveObjectSerialize(obj,bitBuffer);
                return bitBuffer.toByteArray();
            }
        } catch (Exception ex) {
            throw new BitEncodingException(ex.getMessage(), ex);
        }

        return null;
    }

    private void reqursiveObjectSerialize(Object object, BitBuffer bitBuffer) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        //TODO: The Real Shit
        Class objClass = object.getClass();
        Field [] fields = objClass.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            serializeSingleField(field,object,bitBuffer);
        }
    }

    private void serializeSingleField (Field field, Object object, BitBuffer bitBuffer) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Bound annot = field.getAnnotation(Bound.class);
        Class fieldClass = field.getType();
        Object fieldValue = field.get(object);
        Codec codec = factory.getCodec(fieldClass);

        if(fieldValue != null && fieldClass != Object.class) {
            if(codec == null) {
                chooseNonCodecSerialization(fieldClass,fieldValue,annot,bitBuffer,codec);
            } else if(annot != null) {
                codec.serialize(fieldValue, bitBuffer, annot);
            }
        }
    }

    private void chooseNonCodecSerialization(Class fieldClass, Object fieldValue,Bound annot,BitBuffer bitBuffer, Codec codec) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        if(fieldClass.isEnum()) {
            handleEnumsSerialization (fieldClass, fieldValue, bitBuffer, annot);
        } else if(reflect.isIteratable(fieldClass)) {
            handleArraysAndLists(fieldClass,fieldValue,bitBuffer);
        }
        else {
            reqursiveObjectSerialize(fieldValue,bitBuffer);
        }
    }

    private void handleEnumsSerialization(Class enumClass, Object enumValue, BitBuffer bitBuffer, Bound annot) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        if(annot != null) {
            Method ordinalM = enumClass.getMethod("ordinal",null);
            Integer ordinalValue = (Integer) ordinalM.invoke(enumValue,null);
            Codec<Integer> intCodec = factory.getCodec(Integer.class);
            intCodec.serialize(ordinalValue, bitBuffer, annot);
        }
    }

    private void handleArraysAndLists(Class fieldClass, Object fieldValue,BitBuffer bitBuffer) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Object [] arr;

        if(reflect.isList(fieldClass)) {
            arr = reflect.convertListToArray(fieldValue);
        } else {
            arr = reflect.convertObjectToArray(fieldValue);
        }

        for (Object currentElement : arr) {
            reqursiveObjectSerialize(currentElement, bitBuffer);
        }
    }


}
