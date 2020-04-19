package algorithms.shift;

import algorithms.EncryptionAlgorithm;
import org.junit.Test;

import java.util.StringJoiner;

import static org.junit.Assert.*;

public class ShiftUpEncryptionTest {
    private EncryptionAlgorithm shiftUp = new ShiftUpEncryption();
    private final String SEPARATOR = ",";
    private final String MESSAGE = new StringJoiner(SEPARATOR).add("98").add("99").add("100").add("101").toString();
    @Test
    void encryptTest_exampleKey() {
        String expected = "99,100,101,102";
        String actual = shiftUp.encrypt(MESSAGE, 1, SEPARATOR);
        assertEquals(expected, actual);
    }

    @Test
    void decryptTest_exampleKey() {
        String expected = "97,98,99,100";
        String actual = shiftUp.decrypt(MESSAGE, 1, SEPARATOR);
        assertEquals(expected, actual);
    }

    @Test
    void encryptTest_keyIsZero() {
        String actual = shiftUp.encrypt(MESSAGE, 0, SEPARATOR);
        assertEquals(MESSAGE, actual);
    }

    @Test
    void decryptTest_keyIsZero() {
        String actual = shiftUp.decrypt(MESSAGE, 0, SEPARATOR);
        assertEquals(MESSAGE, actual);
    }

}
