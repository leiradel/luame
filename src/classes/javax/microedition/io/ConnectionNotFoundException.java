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

}
