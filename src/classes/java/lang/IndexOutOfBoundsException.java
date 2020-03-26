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

}
