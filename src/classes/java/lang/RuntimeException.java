package java.lang;

public class RuntimeException extends Exception {
    /**
     * Constructs a RuntimeException with no detail  message.
     */
    public RuntimeException() {
        construct();
    }

    private native void construct();

    /**
     * Constructs a RuntimeException with the specified
     *  detail message.
     * 
     * Parameters:s - the detail message.
     */
    public RuntimeException(String s) {
        construct(s);
    }

    private native void construct(String s);

}
