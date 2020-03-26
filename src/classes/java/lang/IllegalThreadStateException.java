package java.lang;

public class IllegalThreadStateException extends IllegalArgumentException {
    /**
     * Constructs an IllegalThreadStateException with no
     *  detail message.
     */
    public IllegalThreadStateException() {
        construct();
    }

    private native void construct();

}
