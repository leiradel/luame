package java.lang;

public class IndexOutOfBoundsException extends RuntimeException {
    /**
     * Constructs an IndexOutOfBoundsException with no
     *  detail message.
     */
    public IndexOutOfBoundsException() {
        construct();
    }

    private native void construct();

    /**
     * Constructs an IndexOutOfBoundsException with the
     *  specified detail message.
     * 
     * Parameters:s - the detail message.
     */
    public IndexOutOfBoundsException(String s) {
        construct(s);
    }

    private native void construct(String s);

}
