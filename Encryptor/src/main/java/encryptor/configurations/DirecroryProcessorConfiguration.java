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
import org.springframework.context.annotation.Primary;

@Configuration
public class DirecroryProcessorConfiguration {

    @Autowired
    @Primary
    @Bean("asynProcessor")
    public DirectoryProcessorInterface createAsynDirectoryProcessor(FileEncryptor fileEncryptor) {
        return new AsyncDirectoryProcessor(fileEncryptor);
    }

    @Autowired
    @Bean("synProcessor")
    public DirectoryProcessorInterface createSynDirectoryProcessor(FileEncryptor fileEncryptor) {
        return new SyncDirectoryProcessor(fileEncryptor);
    }
}
