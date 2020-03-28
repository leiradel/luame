package java.lang;

public class Error extends Throwable {
    /**
     * Constructs an Error with no specified detail message.
     */
    public Error() {
        construct();
    }

    private native void construct();

    /**
     * Constructs an Error with the specified detail message.
     * 
     * Parameters:s - the detail message.
     */
    public Error(String s) {
        construct(s);
    }

    private native void construct(String s);

}
