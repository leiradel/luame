package java.lang;

public class IllegalThreadStateException extends IllegalArgumentException {
    /**
     * Constructs an IllegalThreadStateException with no
     *  detail message.
     */
    public IllegalThreadStateException() {
        construct();
    }

    private native void construct();

    /**
     * Constructs an IllegalThreadStateException with the
     *  specified detail message.
     * 
     * Parameters:s - the detail message.
     */
    public IllegalThreadStateException(String s) {
        construct(s);
    }

    private native void construct(String s);

}
