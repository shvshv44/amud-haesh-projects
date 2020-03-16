package skypath.utils.bits.pojobit.exceptions;


public class BitEncodingException extends RuntimeException {

    public BitEncodingException() {
    }

    public BitEncodingException(String s) {
        super(s);
    }

    public BitEncodingException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public BitEncodingException(Throwable throwable) {
        super(throwable);
    }
}
