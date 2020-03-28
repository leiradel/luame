package java.lang;

public class ArrayStoreException extends RuntimeException {
    /**
     * Constructs an ArrayStoreException with no detail message.
     */
    public ArrayStoreException() {
        construct();
    }

    private native void construct();

    /**
     * Constructs an ArrayStoreException with the specified
     *  detail message.
     * 
     * Parameters:s - the detail message.
     */
    public ArrayStoreException(String s) {
        construct(s);
    }

    private native void construct(String s);

}
