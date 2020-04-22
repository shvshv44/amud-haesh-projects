package encryptors;

import algorithms.EncryptionAlgorithm;
import generators.KeyGenerator;
import managers.FileIOHandler;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pojos.EncryptorParameters;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
public class RepeatEncryptorTest  {

    private  RepeatEncryptor repeatEncryptor;

    private int repeats = 5;

    @Mock
    private EncryptionAlgorithm algorithm;

    @Mock
    private KeyGenerator keyGenerator;

    @Mock
    private FileIOHandler fileIOHandler;

    //@Mock
    private EncryptorParameters parameters;

    @Before
    public void setUp() {
        //MockitoAnnotations.initMocks(this);
        parameters = new EncryptorParameters(",", "\\.","_encrypted.","_decrypted.","\\\\key.txt");
        this.repeatEncryptor = new RepeatEncryptor(algorithm, keyGenerator, fileIOHandler, repeats, parameters);
    }

    @Test
    public void encryptTest() {
        repeatEncryptor.encrypt("98,99,100");
        verify(algorithm, times(repeats)).encrypt(anyString(), anyInt(), anyString());
    }

    @Test
    public void decryptTest() {
        repeatEncryptor.decrypt("100,101,102");
        verify(algorithm, times(repeats)).decrypt(anyString(), anyInt(), anyString());
    }
}
