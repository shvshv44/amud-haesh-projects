package skypath.utils.bits.pojobit.bits;

public enum BitSizeOf {

    BYTE(8),
    BOOLEAN(8),
    CHAR(16),
    SHORT(16),
    INT(32),
    FLOAT(32),
    LONG(64),
    DOUBLE(64);

    private int value;

    private BitSizeOf(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }
}
