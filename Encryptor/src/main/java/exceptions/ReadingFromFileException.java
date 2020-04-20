package exceptions;

import java.io.IOException;

public class ReadingFromFileException extends IOException {
    public ReadingFromFileException(String errorMessage) {
        super("Error reading from file " + errorMessage);
    }
}
