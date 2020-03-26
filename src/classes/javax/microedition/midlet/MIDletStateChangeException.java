package javax.microedition.midlet;

public class MIDletStateChangeException extends Exception {
    /**
     * Constructs an exception with no specified detail message.
     */
    public MIDletStateChangeException() {
        construct();
    }

    private native void construct();

}
