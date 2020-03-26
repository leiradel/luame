package java.lang;

public class NullPointerException extends RuntimeException {
    /**
     * Constructs a NullPointerException with no detail message.
     */
    public NullPointerException() {
        construct();
    }

    private native void construct();

}
