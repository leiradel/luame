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

}
