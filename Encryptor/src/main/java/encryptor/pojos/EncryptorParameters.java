package encryptor.pojos;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(value = "application.properties")
public class EncryptorParameters {
    private String separator;
    private String pathSeparator;
    private String encryptedFolderName;
    private String decryptedFolderName;
    private String keyFileName;
    private String fileType;
    private String resultPath;
    private String xsdFilePath;

    @Autowired
    public EncryptorParameters(@Value("${data.encryption-separator}") String separator,
                               @Value("${data.path-separator}") String pathSeparator,
                               @Value("${data.encrypt-folder-name}") String encryptedFolderName,
                               @Value("${data.decrypt-folder-name}") String decryptedFolderName,
                               @Value("${data.key-file-name}") String keyFileName,
                               @Value("${data.file-type}") String fileType,
                               @Value("${data.result-file-path}") String resultPath,
                               @Value("${data.xsd-file-path}") String xsdFilePath){
        this.separator = separator;
        this.pathSeparator = pathSeparator;
        this.encryptedFolderName = encryptedFolderName;
        this.decryptedFolderName = decryptedFolderName;
        this.keyFileName = keyFileName;
        this.fileType = fileType;
        this.resultPath = resultPath;
        this.xsdFilePath = xsdFilePath;
    }
}
