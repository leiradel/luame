package java.lang;

public class ClassCastException extends RuntimeException {
    /**
     * Constructs a ClassCastException with no detail message.
     */
    public ClassCastException() {
        construct();
    }

    private native void construct();

    /**
     * Constructs a ClassCastException with the specified
     *  detail message.
     * 
     * Parameters:s - the detail message.
     */
    public ClassCastException(String s) {
        construct(s);
    }

    private native void construct(String s);

}
