package pojos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EncryptorParameters {
    private String separator;
    private String pathSeparator;
    private String encryptedEnding;
    private String decryptedEnding;
    private String keyFileName;
}
