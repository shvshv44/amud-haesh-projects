package pojos;

public class EncryptorParameters {
    private String separator;
    private String pathSeparator;
    private String encryptedEnding;
    private String decryptedEnding;
    private String keyFileName;


    public EncryptorParameters(String separator, String pathSeparator, String encryptedEnding, String decryptedEnding, String keyFileName) {
        this.separator = separator;
        this.pathSeparator = pathSeparator;
        this.encryptedEnding = encryptedEnding;
        this.decryptedEnding = decryptedEnding;
        this.keyFileName = keyFileName;
    }

    public String getSeparator() {
        return separator;
    }

    public void setSeparator(String separator) {
        this.separator = separator;
    }

    public String getPathSeparator() {
        return pathSeparator;
    }

    public void setPathSeparator(String pathSeparator) {
        this.pathSeparator = pathSeparator;
    }

    public String getEncryptedEnding() {
        return encryptedEnding;
    }

    public void setEncryptedEnding(String encryptedEnding) {
        this.encryptedEnding = encryptedEnding;
    }

    public String getDecryptedEnding() {
        return decryptedEnding;
    }

    public void setDecryptedEnding(String decryptedEnding) {
        this.decryptedEnding = decryptedEnding;
    }

    public String getKeyFileName() {
        return keyFileName;
    }

    public void setKeyFileName(String keyFileName) {
        this.keyFileName = keyFileName;
    }
}
