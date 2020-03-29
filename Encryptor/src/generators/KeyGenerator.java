package generators;

public interface KeyGenerator {
    int generateKey();
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
