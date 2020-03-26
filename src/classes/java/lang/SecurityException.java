package java.lang;

public class SecurityException extends RuntimeException {
    /**
     * Constructs a SecurityException with no detail  message.
     */
    public SecurityException() {
        construct();
    }

    private native void construct();

}
