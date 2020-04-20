package generators;

public abstract class KeyGenerator {
    abstract int makeKey();

    public int generateKey() {
        int key = makeKey();
        while (key == 0) {
            key = makeKey();
        }
        return key;
    }

    public int[] generateKeys() {
        return new int[] {generateKey()};
    }

    public int[] generateKeys(int size) {
        int[] keys = new int[size];
        for (int i = 0; i < size; i++) {
            keys[i] = generateKey();
        }
        return keys;
    }
}
