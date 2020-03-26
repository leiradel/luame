package java.lang;

public class Exception extends Throwable {
    /**
     * Constructs an Exception with no specified detail message.
     */
    public Exception() {
        super();
    }

    /**
     * Constructs an IOException with the
     * specified detail message.
     */
    public Exception(String message) {
        super(message);
    }
}
