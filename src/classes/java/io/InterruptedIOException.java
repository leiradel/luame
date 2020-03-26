package java.io;

public class InterruptedIOException extends IOException {
    /**
     * Reports how many bytes had been transferred as part of the I/O
     *  operation before it was interrupted.
     */
    public int bytesTransferred = ;

    /**
     * Constructs an InterruptedIOException with
     *  null as its error detail message.
     */
    public InterruptedIOException() {
        construct();
    }

    private native void construct();

}
