package java.util;

public class EmptyStackException extends RuntimeException {
    /**
     * Constructs a new EmptyStackException with null
     *  as its error message string.
     */
    public EmptyStackException() {
        construct();
    }

    private native void construct();

}
