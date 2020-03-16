package shaq.pojobit.bits;


public enum ByteSizeOf {

    BYTE(1),
    BOOLEAN(1),
    CHAR(2),
    SHORT(2),
    INT(4),
    FLOAT(4),
    LONG(8),
    DOUBLE(8);

    private int value;

    private ByteSizeOf(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }

}
