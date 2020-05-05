package encryptor.configurations;

import encryptor.encryptors.FileEncryptor;
import encryptor.processors.AsyncDirectoryProcessor;
import encryptor.processors.DirectoryProcessorInterface;
import encryptor.processors.SyncDirectoryProcessor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DirecroryProcessorConfiguration {

    @Bean("asynProcessor")
    public DirectoryProcessorInterface createAsynDirectoryProcessor(@Qualifier("shiftEnc") FileEncryptor fileEncryptor) {
        return new AsyncDirectoryProcessor(fileEncryptor);
    }

    @Bean("synProcessor")
    public DirectoryProcessorInterface createSynDirectoryProcessor(@Qualifier("shiftEnc") FileEncryptor fileEncryptor) {
        return new SyncDirectoryProcessor(fileEncryptor);
    }
}
