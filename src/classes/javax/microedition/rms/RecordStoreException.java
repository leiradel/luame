package javax.microedition.rms;

public class RecordStoreException extends Exception {
    /**
     * Constructs a new RecordStoreException with the
     *  specified detail message.
     * 
     * Parameters:message - the detail message
     */
    public RecordStoreException(String message) {
        construct(message);
    }

    private native void construct(String message);

    /**
     * Constructs a new RecordStoreException with no detail
     *  message.
     */
    public RecordStoreException() {
        construct();
    }

    private native void construct();

}
