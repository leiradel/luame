package java.lang;

public class Exception extends Throwable {
    /**
     * Constructs an Exception with no specified detail message.
     */
    public Exception() {
        construct();
    }

    private native void construct();

    /**
     * Constructs an Exception with the specified detail message.
     * 
     * Parameters:s - the detail message.
     */
    public Exception(String s) {
        construct(s);
    }

    private native void construct(String s);

}
