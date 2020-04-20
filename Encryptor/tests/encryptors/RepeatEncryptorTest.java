package encryptors;

import algorithms.EncryptionAlgorithm;
import generators.KeyGenerator;
import managers.FileManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.MockitoAnnotations;
import pojos.EncryptorParameters;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
public class RepeatEncryptorTest  {

    private  RepeatEncryptor repeatEncryptor;

    @Mock
    private int repeats = 3;

    @Mock
    private EncryptionAlgorithm algorithm;

    @Mock
    private KeyGenerator keyGenerator;

    @Mock
    private FileManager fileManager;

    @Mock
    private EncryptorParameters parameters;

    public RepeatEncryptorTest() {
        MockitoAnnotations.initMocks(this);
        this.repeatEncryptor = new RepeatEncryptor(algorithm, keyGenerator, fileManager, repeats, parameters);
    }

    @Test
    public void encryptTest() {
        repeatEncryptor.encrypt("message");
        verify(algorithm, times(repeats)).encrypt("message", 1, parameters.getSeparator());
    }

    @Test
    public void decryptTest() {
        repeatEncryptor.encrypt("message");
        verify(algorithm, times(repeats)).decrypt("message", 1, parameters.getSeparator());
    }
}
