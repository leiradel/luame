package java.util;

public class NoSuchElementException extends RuntimeException {
    /**
     * Constructs a NoSuchElementException with null
     *  as its error message string.
     */
    public NoSuchElementException() {
        construct();
    }

    private native void construct();

}
