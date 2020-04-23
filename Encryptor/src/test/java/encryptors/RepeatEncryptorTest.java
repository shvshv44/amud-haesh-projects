package encryptors;

import algorithms.EncryptionAlgorithm;
import generators.KeyGenerator;
import managers.FileIOHandler;
import managers.JAXBManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pojos.EncryptorParameters;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RepeatEncryptorTest  {

    private RepeatEncryptor repeatEncryptor;

    @Mock
    private JAXBManager jaxbManager;

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
        this.repeatEncryptor = new encryptors.RepeatEncryptor(jaxbManager, algorithm, keyGenerator, fileIOHandler, repeats, parameters);
        doAnswer(invocationOnMock -> {
            return invocationOnMock.getArgument(0);
            }).when(algorithm).encrypt(anyString(), anyInt(), anyString());

        doAnswer(invocationOnMock -> {
            return invocationOnMock.getArgument(0);
        }).when(algorithm).decrypt(anyString(), anyInt(), anyString());
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
