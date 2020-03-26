package java.lang;

public class ArrayStoreException extends RuntimeException {
    /**
     * Constructs an ArrayStoreException with no detail message.
     */
    public ArrayStoreException() {
        construct();
    }

    private native void construct();

}
