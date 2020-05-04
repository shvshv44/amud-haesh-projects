package generators;

public interface KeyGenerator {
    int makeKey();

    default int generateKey() {
        int key = makeKey();
        while (key == 0) {
            key = makeKey();
        }
        return key;
    }

    default int[] generateKeys() {
        return new int[] {generateKey()};
    }

    default int[] generateKeys(int size) {
        int[] keys = new int[size];
        for (int i = 0; i < size; i++) {
            keys[i] = generateKey();
        }
        return keys;
    }
}
