package exceptions;

import java.io.IOException;

public class WritingToFileException extends IOException {
    public WritingToFileException(String errorMessage) {
        super("Error writing to file " + errorMessage);
    }
}
