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

    /**
     * Constructs a NoSuchElementException, saving a reference
     *  to the error message string s for later retrieval by the
     *  getMessage method.
     * 
     * Parameters:s - the detail message.
     */
    public NoSuchElementException(String s) {
        construct(s);
    }

    private native void construct(String s);

}
