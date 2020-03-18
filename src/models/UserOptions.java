package models;

public enum UserOptions {
    DEFAULT(0),
    ENCRYPT(1),
    DECRYPT (2),
    EXIT(3);

    private int value;

    UserOptions(int optionCode) {
        this.value = optionCode;
    }

    public int getValue() { return value; }

    public static UserOptions getOptionByCodeNumber(int code) {
        switch (code) {
            case 1:
                return ENCRYPT;
            case 2:
                return DECRYPT;
            case 3:
                return EXIT;
            default:
                return DEFAULT;
        }

    }
}
