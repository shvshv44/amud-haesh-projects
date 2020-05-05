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
    private String encryptedEnding;
    private String decryptedEnding;
    private String keyFileName;
    private String fileType;
    private String resultPath;

    @Autowired
    public EncryptorParameters(@Value("${separator}") String separator,
                               @Value("${pathSeparator}") String pathSeparator,
                               @Value("${encryptedEnding}") String encryptedEnding,
                               @Value("${decryptedEnding}") String decryptedEnding,
                               @Value("${keyFileName}") String keyFileName,
                               @Value("${fileType}") String fileType,
                               @Value("${resultFilePath}") String resultPath){
        this.separator = separator;
        this.pathSeparator = pathSeparator;
        this.encryptedEnding = encryptedEnding;
        this.decryptedEnding = decryptedEnding;
        this.keyFileName = keyFileName;
        this.fileType = fileType;
        this.resultPath = resultPath;
    }
}
