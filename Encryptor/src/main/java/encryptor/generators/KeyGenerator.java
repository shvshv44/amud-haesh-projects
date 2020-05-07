package encryptor.generators;

import org.springframework.stereotype.Component;

@Component
public interface KeyGenerator {
    int makeKey();

    default int generateSingleKey() {
        int key = makeKey();
        while (key == 0) {
            key = makeKey();
        }
        return key;
    }

    default int[] generateKeys() {
        return new int[] {generateSingleKey()};
    }

    default int[] generateKeys(int size) {
        int[] keys = new int[size];
        for (int i = 0; i < size; i++) {
            keys[i] = generateSingleKey();
        }
        return keys;
    }
}
