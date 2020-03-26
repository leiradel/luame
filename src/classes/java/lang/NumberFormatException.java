package java.lang;

public class NumberFormatException extends IllegalArgumentException {
    /**
     * Constructs a NumberFormatException with no detail message.
     */
    public NumberFormatException() {
        construct();
    }

    private native void construct();

}
