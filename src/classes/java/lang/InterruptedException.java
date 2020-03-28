package java.lang;

public class InterruptedException extends Exception {
    /**
     * Constructs an InterruptedException with no detail message.
     */
    public InterruptedException() {
        construct();
    }

    private native void construct();

    /**
     * Constructs an InterruptedException with the
     *  specified detail message.
     * 
     * Parameters:s - the detail message.
     */
    public InterruptedException(String s) {
        construct(s);
    }

    private native void construct(String s);

}
