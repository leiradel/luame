package javax.microedition.rms;

public class RecordStoreNotOpenException extends RecordStoreException {
    /**
     * Constructs a new RecordStoreNotOpenException with the
     *  specified detail message.
     * 
     * Parameters:message - the detail message
     */
    public RecordStoreNotOpenException(String message) {
        construct(message);
    }

    private native void construct(String message);

    /**
     * Constructs a new RecordStoreNotOpenException with no detail
     *  message.
     */
    public RecordStoreNotOpenException() {
        construct();
    }

    private native void construct();

}
