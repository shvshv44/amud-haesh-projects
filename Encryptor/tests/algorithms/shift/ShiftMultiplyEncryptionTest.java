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
    private final String MESSAGE = new StringJoiner(SEPARATOR).add("98").add("99").add("100").add("101").toString();

    @Test
    void encryptTest_exampleKey() {
        String expected = "196,198,200,202";
        String actual = shiftMultiply.encrypt(MESSAGE, 2, SEPARATOR);
        assertEquals(expected, actual);
    }

    @Test
    void decryptTest_exampleKey() {
        String encryptedMessage = "196,198,200,202";
        String actual = shiftMultiply.decrypt(encryptedMessage, 2, SEPARATOR);
        assertEquals(MESSAGE, actual);
    }
}
