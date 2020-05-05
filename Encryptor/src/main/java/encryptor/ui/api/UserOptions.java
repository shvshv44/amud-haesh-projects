package encryptor.ui.api;

public enum UserOptions {
    DEFAULT(0),
    ENCRYPTION(1),
    DECRYPTION (2),
    EXIT(3);

    private int value;

    UserOptions(int optionCode) {
        this.value = optionCode;
    }

    public int getValue() { return value; }

    public boolean isPrintable() {
        return this != DEFAULT;
    }

    public static UserOptions getOptionByCodeNumber(int code) {
        switch (code) {
            case 1:
                return ENCRYPTION;
            case 2:
                return DECRYPTION;
            case 3:
                return EXIT;
            default:
                return DEFAULT;
        }
    }
}
