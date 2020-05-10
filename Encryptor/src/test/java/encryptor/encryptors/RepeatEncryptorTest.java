package encryptor.encryptors;

import encryptor.algorithms.EncryptionAlgorithm;
import encryptor.generators.KeyGenerator;
import encryptor.listeners.EncryptionObserver;
import encryptor.managers.FileIOHandler;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import encryptor.pojos.EncryptorParameters;
import encryptor.ui.UIManager;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RepeatEncryptorTest  {

    private RepeatEncryptor repeatEncryptor;

    private int repeats = 5;

    @Mock
    private EncryptionAlgorithm algorithm;

    @Mock
    private KeyGenerator keyGenerator;

    @Mock
    private FileIOHandler fileIOHandler;

    @Mock
    private UIManager uiManager;

    //@Mock
    private EncryptorParameters parameters;

    @Mock
    private List<EncryptionObserver> observers;

    @Before
    public void setUp() {
        //MockitoAnnotations.initMocks(this);
        parameters = new EncryptorParameters(",", "\\.","_encrypted.","_decrypted.","\\\\key.txt", ".txt","EncryptionResults.xsd", "result.xml");
        this.repeatEncryptor = new encryptor.encryptors.RepeatEncryptor(algorithm, keyGenerator, fileIOHandler, uiManager, repeats, parameters, observers);
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
