package exceptions;

import java.io.IOException;

public class ReadingFromFileException extends IOException {
    public ReadingFromFileException(String errorMessage) {
        super(errorMessage);
    }
}
