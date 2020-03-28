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

    /**
     * Constructs an InterruptedIOException with the
     *  specified detail message. The string s can be
     *  retrieved later by the
     *  Throwable.getMessage()
     *  method of class java.lang.Throwable.
     * 
     * Parameters:s - the detail message.
     */
    public InterruptedIOException(String s) {
        construct(s);
    }

    private native void construct(String s);

}
