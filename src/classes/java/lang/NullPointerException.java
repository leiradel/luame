package java.lang;

public class NullPointerException extends RuntimeException {
    /**
     * Constructs a NullPointerException with no detail message.
     */
    public NullPointerException() {
        construct();
    }

    private native void construct();

    /**
     * Constructs a NullPointerException with the specified
     *  detail message.
     * 
     * Parameters:s - the detail message.
     */
    public NullPointerException(String s) {
        construct(s);
    }

    private native void construct(String s);

}
