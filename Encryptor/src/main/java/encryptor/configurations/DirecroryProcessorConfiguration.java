package encryptor.configurations;

import encryptor.encryptors.FileEncryptor;
import encryptor.pojos.EncryptorParameters;
import encryptor.processors.AsyncDirectoryProcessor;
import encryptor.processors.DirectoryProcessorInterface;
import encryptor.processors.SyncDirectoryProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DirecroryProcessorConfiguration {

    @Autowired
    @Bean("asynProcessor")
    public DirectoryProcessorInterface createAsynDirectoryProcessor(@Qualifier("shiftEnc") FileEncryptor fileEncryptor,
                                                                    EncryptorParameters parameters) {
        return new AsyncDirectoryProcessor(fileEncryptor, parameters);
    }

    @Autowired
    @Bean("synProcessor")
    public DirectoryProcessorInterface createSynDirectoryProcessor(@Qualifier("shiftEnc") FileEncryptor fileEncryptor,
                                                                   EncryptorParameters parameters) {
        return new SyncDirectoryProcessor(fileEncryptor, parameters);
    }
}
