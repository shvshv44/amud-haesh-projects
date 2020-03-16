package skypath.utils.bits.pojobit.reflection;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ReflectionHelperTest {

    @Test
    public void isArrayTest_NonArrayClass() {
        ReflectionHelper reflectionHelper = new ReflectionHelper();
        Class clazz = Integer.class;
        assertFalse(reflectionHelper.isArray(clazz));
    }

    @Test
    public void isArrayTest_ArrayClass() {
        ReflectionHelper reflectionHelper = new ReflectionHelper();
        Class clazz = Integer[].class;
        assertTrue(reflectionHelper.isArray(clazz));
    }

    @Test
    public void isListTest_NonListClass() {
        ReflectionHelper reflectionHelper = new ReflectionHelper();
        Class clazz = Integer.class;
        assertFalse(reflectionHelper.isList(clazz));
    }

    @Test
    public void isListTest_ListClass() {
        ReflectionHelper reflectionHelper = new ReflectionHelper();
        Class clazz = ArrayList.class;
        assertTrue(reflectionHelper.isList(clazz));
    }

    @Test
    public void isIteratableTest() {
        ReflectionHelper reflectionHelper = new ReflectionHelper();
        Class clazzL = Integer[].class;
        Class clazzA = ArrayList.class;
        Class clazzNon = Integer.class;
        assertTrue(reflectionHelper.isIteratable(clazzL));
        assertTrue(reflectionHelper.isIteratable(clazzA));
        assertFalse(reflectionHelper.isIteratable(clazzNon));
    }

    @Test
    public void convertListToArray() {
        ReflectionHelper reflectionHelper = new ReflectionHelper();
        ArrayList<Integer> numberList = new ArrayList<>();
        numberList.add(1);
        numberList.add(2);
        numberList.add(3);
        Object [] result = reflectionHelper.convertListToArray(numberList);
        assertEquals(new Integer(1), result[0]);
        assertEquals(new Integer(2), result[1]);
        assertEquals(new Integer(3), result[2]);
    }

}
