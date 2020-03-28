package java.lang;

public class NegativeArraySizeException extends RuntimeException {
    /**
     * Constructs a NegativeArraySizeException with no
     *  detail message.
     */
    public NegativeArraySizeException() {
        construct();
    }

    private native void construct();

    /**
     * Constructs a NegativeArraySizeException with the
     *  specified detail message.
     * 
     * Parameters:s - the detail message.
     */
    public NegativeArraySizeException(String s) {
        construct(s);
    }

    private native void construct(String s);

}
