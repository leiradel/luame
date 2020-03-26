package java.lang;

public class IllegalArgumentException extends RuntimeException {
    /**
     * Constructs an IllegalArgumentException with no
     *  detail message.
     */
    public IllegalArgumentException() {
        construct();
    }

    private native void construct();

}
