package java.lang;

public class Exception extends Throwable {
    /**
     * Constructs an Exception with no specified detail message.
     */
    public Exception() {
        construct();
    }

    private native void construct();

}
