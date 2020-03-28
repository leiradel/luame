package javax.microedition.media;

public class MediaException extends Exception {
    /**
     * Constructs a MediaException with null
     *  as its error detail message.
     */
    public MediaException() {
        construct();
    }

    private native void construct();

    /**
     * Constructs a MediaException with the specified detail
     *  message. The error message string s can later be
     *  retrieved by the
     *  Throwable.getMessage()
     *  method of class java.lang.Throwable.
     * 
     * Parameters:reason - the detail message.
     */
    public MediaException(String reason) {
        construct(reason);
    }

    private native void construct(String reason);

}
