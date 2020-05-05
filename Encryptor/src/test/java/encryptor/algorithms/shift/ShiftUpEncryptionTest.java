package encryptor.algorithms.shift;

import encryptor.algorithms.EncryptionAlgorithm;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.StringJoiner;
import static org.junit.Assert.*;


@RunWith(MockitoJUnitRunner.class)
public class ShiftUpEncryptionTest {
    private EncryptionAlgorithm shiftUp = new ShiftUpEncryption();
    private final String SEPARATOR = ",";
    private final String MESSAGE = new StringJoiner(SEPARATOR).add("98").add("99").add("100").add("101").toString();

    @Test
    public void encryptTest_exampleKey() {
        String expected = "99,100,101,102";
        String actual = shiftUp.encrypt(MESSAGE, 1, SEPARATOR);
        assertEquals(expected, actual);
    }

    @Test
    public void decryptTest_exampleKey() {
        String expected = "97,98,99,100";
        String actual = shiftUp.decrypt(MESSAGE, 1, SEPARATOR);
        assertEquals(expected, actual);
    }
}
