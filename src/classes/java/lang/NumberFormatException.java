package java.lang;

public class NumberFormatException extends IllegalArgumentException {
    /**
     * Constructs a NumberFormatException with no detail message.
     */
    public NumberFormatException() {
        construct();
    }

    private native void construct();

    /**
     * Constructs a NumberFormatException with the
     *  specified detail message.
     * 
     * Parameters:s - the detail message.
     */
    public NumberFormatException(String s) {
        construct(s);
    }

    private native void construct(String s);

}
