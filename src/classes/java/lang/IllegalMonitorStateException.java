package java.lang;

public class IllegalMonitorStateException extends RuntimeException {
    /**
     * Constructs an IllegalMonitorStateException with no
     *  detail message.
     */
    public IllegalMonitorStateException() {
        construct();
    }

    private native void construct();

    /**
     * Constructs an IllegalMonitorStateException with the
     *  specified detail message.
     * 
     * Parameters:s - the detail message.
     */
    public IllegalMonitorStateException(String s) {
        construct(s);
    }

    private native void construct(String s);

}
