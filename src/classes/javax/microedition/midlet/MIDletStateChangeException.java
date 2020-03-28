package javax.microedition.midlet;

public class MIDletStateChangeException extends Exception {
    /**
     * Constructs an exception with no specified detail message.
     */
    public MIDletStateChangeException() {
        construct();
    }

    private native void construct();

    /**
     * Constructs an exception with the specified detail message.
     * 
     * Parameters:s - the detail message
     */
    public MIDletStateChangeException(String s) {
        construct(s);
    }

    private native void construct(String s);

}
