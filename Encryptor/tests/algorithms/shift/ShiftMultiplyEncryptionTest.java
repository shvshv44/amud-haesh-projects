package algorithms.shift;

import algorithms.EncryptionAlgorithm;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.StringJoiner;
import static org.junit.Assert.assertEquals;


@RunWith(MockitoJUnitRunner.class)
public class ShiftMultiplyEncryptionTest {
    private EncryptionAlgorithm shiftMultiply = new ShiftMultiplyEncryption();
    private final String SEPARATOR = ",";
    private final String CIPHER = new StringJoiner(SEPARATOR).add("196").add("198").add("200").add("202").toString();

    @Test
    public void encryptTest_exampleKey() {
        String message = "98,99,100,101";
        String actual = shiftMultiply.encrypt(message, 2, SEPARATOR);
        assertEquals(CIPHER, actual);
    }

    @Test
    public void decryptTest_exampleKey() {
        String expected = "98,99,100,101";
        String actual = shiftMultiply.decrypt(CIPHER, 2, SEPARATOR);
        assertEquals(expected, actual);
    }
}
