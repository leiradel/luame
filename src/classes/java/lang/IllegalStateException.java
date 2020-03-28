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

    /**
     * Constructs an IllegalStateException with the specified detail
     *  message.  A detail message is a String that describes this particular
     *  exception.
     * 
     * Parameters:s - the String that contains a detailed message
     */
    public IllegalStateException(String s) {
        construct(s);
    }

    private native void construct(String s);

}
