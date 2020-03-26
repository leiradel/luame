package java.lang;

public class RuntimeException extends Exception {
    /**
     * Constructs a RuntimeException with no detail  message.
     */
    public RuntimeException() {
        construct();
    }

    private native void construct();

}
