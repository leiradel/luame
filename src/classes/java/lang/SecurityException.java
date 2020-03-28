package java.lang;

public class SecurityException extends RuntimeException {
    /**
     * Constructs a SecurityException with no detail  message.
     */
    public SecurityException() {
        construct();
    }

    private native void construct();

    /**
     * Constructs a SecurityException with the specified
     *  detail message.
     * 
     * Parameters:s - the detail message.
     */
    public SecurityException(String s) {
        construct(s);
    }

    private native void construct(String s);

}
