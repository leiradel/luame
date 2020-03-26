package java.lang;

public class InterruptedException extends Exception {
    /**
     * Constructs an InterruptedException with no detail message.
     */
    public InterruptedException() {
        construct();
    }

    private native void construct();

}
