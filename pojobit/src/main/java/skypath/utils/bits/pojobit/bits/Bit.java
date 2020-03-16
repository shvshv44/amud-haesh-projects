package skypath.utils.bits.pojobit.bits;

public class Bit {

    private boolean value;
    private static boolean DEFAULT = false;

    public Bit (boolean value) {
        this.value = value;
    }

    public Bit (byte value) {
        this.value = value % 2 == 0;
    }

    public Bit (int value) {
        this.value = !(value % 2 == 0);
    }

    public Bit() {
        this.value = DEFAULT;
    }

    public boolean value() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    public byte asByte() {
        return (value())? (byte)1 : (byte)0;
    }

    private Bit xor(Bit b) {
        return new Bit(this.value != b.value);
    }


}
