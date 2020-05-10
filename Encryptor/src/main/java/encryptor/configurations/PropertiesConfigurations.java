package encryptor.configurations;

import encryptor.pojos.EncryptorParameters;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(value = "application.properties")
public class PropertiesConfigurations {

    @Bean
    public EncryptorParameters createParameters(@Value("${data.encryption-separator}") String separator,
                                                @Value("${data.path-separator}") String pathSeparator,
                                                @Value("${data.encrypt-folder-name}") String encryptedFolderName,
                                                @Value("${data.decrypt-folder-name}") String decryptedFolderName,
                                                @Value("${data.key-file-name}") String keyFileName,
                                                @Value("${data.file-type}") String fileType,
                                                @Value("${data.result-file-path}") String resultPath,
                                                @Value("${data.xsd-file-path}") String xsdFilePath) {

        EncryptorParameters parameters = new EncryptorParameters();

        parameters.setSeparator(separator);
        parameters.setEncryptedFolderName(encryptedFolderName);
        parameters.setDecryptedFolderName(decryptedFolderName);
        parameters.setKeyFileName(keyFileName);
        parameters.setFileType(fileType);
        parameters.setResultPath(resultPath);
        parameters.setXsdFilePath(xsdFilePath);

        return parameters;
    }
}
