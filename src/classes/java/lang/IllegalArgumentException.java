package java.lang;

public class IllegalArgumentException extends RuntimeException {
    /**
     * Constructs an IllegalArgumentException with no
     *  detail message.
     */
    public IllegalArgumentException() {
        construct();
    }

    private native void construct();

    /**
     * Constructs an IllegalArgumentException with the
     *  specified detail message.
     * 
     * Parameters:s - the detail message.
     */
    public IllegalArgumentException(String s) {
        construct(s);
    }

    private native void construct(String s);

}
