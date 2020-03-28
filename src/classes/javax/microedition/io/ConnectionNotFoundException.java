package javax.microedition.io;

public class ConnectionNotFoundException extends IOException {
    /**
     * Constructs a ConnectionNotFoundException with no detail
     *  message.
     */
    public ConnectionNotFoundException() {
        construct();
    }

    private native void construct();

    /**
     * Constructs a ConnectionNotFoundException with the
     *  specified detail message.  A detail message is a String
     *  that describes this particular exception.
     * 
     * Parameters:s - the detail message
     */
    public ConnectionNotFoundException(String s) {
        construct(s);
    }

    private native void construct(String s);

}
