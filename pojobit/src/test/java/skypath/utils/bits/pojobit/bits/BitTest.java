package skypath.utils.bits.pojobit.bits;

import org.junit.Test;
import static org.junit.Assert.*;

public class BitTest {

    @Test
    public void constructorTest_TrueBoolean() {
        boolean b = true;
        Bit bit = new Bit(b);
        assertTrue(bit.value());
    }

    @Test
    public void constructorTest_FalseBoolean() {
        boolean b = false;
        Bit bit = new Bit(b);
        assertFalse(bit.value());
    }

    @Test
    public void constructorTest_TrueByte() {
        byte b = 1;
        Bit bit = new Bit(b);
        assertTrue(bit.value());
    }

    @Test
    public void constructorTest_FalseByte() {
        byte b = 0;
        Bit bit = new Bit(b);
        assertFalse(bit.value());
    }

    @Test
    public void constructorTest_TrueInteger() {
        int integer = 1;
        Bit bit = new Bit(integer);
        assertTrue(bit.value());
    }

    @Test
    public void constructorTest_FalseInteger() {
        int integer = 0;
        Bit bit = new Bit(integer);
        assertFalse(bit.value());
    }

    @Test
    public void setValueTest() {
        Bit bit = new Bit();
        bit.setValue(true);
        assertTrue(bit.value());
    }

    @Test
    public void asByteTest() {
        Bit bitT = new Bit(true);
        Bit bitF = new Bit(false);
        assertEquals(bitT.asByte(),(byte)1);
        assertEquals(bitF.asByte(),(byte)0);
    }

    @Test
    public void xorTest() {
        Bit bitT = new Bit(true);
        Bit bitF = new Bit(false);
        assertFalse(bitT.xor(bitT).value());
        assertFalse(bitF.xor(bitF).value());
        assertTrue(bitF.xor(bitT).value());
        assertTrue(bitT.xor(bitF).value());
    }




}
