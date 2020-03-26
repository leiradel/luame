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

}
