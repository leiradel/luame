package java.lang;

public class ArithmeticException extends RuntimeException {
    /**
     * Constructs an ArithmeticException with no detail
     *  message.
     */
    public ArithmeticException() {
        construct();
    }

    private native void construct();

}
