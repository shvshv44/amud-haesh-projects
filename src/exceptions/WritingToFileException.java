package exceptions;

import java.io.IOException;

public class WritingToFileException extends IOException {
    public WritingToFileException(String errorMessage) {
        super(errorMessage);
    }
}
