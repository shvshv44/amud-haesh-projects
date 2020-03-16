package skypath.utils.bits.pojobit.utils;

import skypath.utils.bits.pojobit.codecs.*;
import java.util.HashMap;

public class CodecsFactory {

    private HashMap<Class<?>, Codec> codecs;

    public CodecsFactory() {
        this.codecs = new HashMap<Class<?>, Codec>();
        initializeDefaultCodecs();
    }

    private void initializeDefaultCodecs () {
        addCodec(Boolean.class, new BooleanCodec());
        addCodec(boolean.class, new BooleanCodec());
        addCodec(Integer.class, new IntegerCodec());
        addCodec(int.class, new IntegerCodec());
        addCodec(Float.class, new FloatCodec());
        addCodec(float.class, new FloatCodec());
        addCodec(Double.class, new DoubleCodec());
        addCodec(double.class, new DoubleCodec());
        addCodec(Byte.class, new ByteCodec());
        addCodec(byte.class, new ByteCodec());
        addCodec(Long.class, new LongCodec());
        addCodec(long.class, new LongCodec());
        addCodec(Short.class, new ShortCodec());
        addCodec(short.class, new ShortCodec());
        addCodec(Character.class, new CharCodec());
        addCodec(char.class, new CharCodec());
        addCodec(byte[].class, new ByteArrayCodec());
        addCodec(String.class, new StringCodec());
    }

    public void addCodec(Class<?> bindedClass, Codec codec) {
        codecs.put(bindedClass, codec);
    }

    public Codec getCodec (Class<?> bindedClass) {
        return codecs.get(bindedClass);
    }
}
