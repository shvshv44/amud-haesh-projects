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
        try {
            return UserOptions.values()[code];
        } catch (IndexOutOfBoundsException e) {
            return DEFAULT;
        }
    }
}
