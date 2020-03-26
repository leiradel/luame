package java.lang;

public class ClassCastException extends RuntimeException {
    /**
     * Constructs a ClassCastException with no detail message.
     */
    public ClassCastException() {
        construct();
    }

    private native void construct();

}
