package javax.microedition.rms;

public class RecordStoreFullException extends RecordStoreException {
    /**
     * Constructs a new RecordStoreFullException with the
     *  specified detail message.
     * 
     * Parameters:message - the detail message
     */
    public RecordStoreFullException(String message) {
        construct(message);
    }

    private native void construct(String message);

    /**
     * Constructs a new RecordStoreFullException with no detail
     *  message.
     */
    public RecordStoreFullException() {
        construct();
    }

    private native void construct();

}
