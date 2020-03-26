package java.lang;

public class IllegalStateException extends RuntimeException {
    /**
     * Constructs an IllegalStateException with no detail message.
     *  A detail message is a String that describes this particular exception.
     */
    public IllegalStateException() {
        construct();
    }

    private native void construct();

}
