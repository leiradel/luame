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

    /**
     * Constructs an ArithmeticException with the specified
     *  detail message.
     * 
     * Parameters:s - the detail message.
     */
    public ArithmeticException(String s) {
        construct(s);
    }

    private native void construct(String s);

}
