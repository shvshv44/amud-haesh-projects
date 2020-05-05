package encryptor.configurations;

import encryptor.encryptors.FileEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import encryptor.processors.AsyncDirectoryProcessor;
import encryptor.processors.DirectoryProcessorInterface;
import encryptor.processors.SyncDirectoryProcessor;

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
