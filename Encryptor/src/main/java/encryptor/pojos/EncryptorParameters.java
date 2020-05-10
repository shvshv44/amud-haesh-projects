package encryptor.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EncryptorParameters {
    private String separator;
    private String pathSeparator;
    private String encryptedFolderName;
    private String decryptedFolderName;
    private String keyFileName;
    private String fileType;
    private String resultPath;
    private String xsdFilePath;
}
