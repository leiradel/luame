package javax.microedition.rms;

public class RecordStoreNotFoundException extends RecordStoreException {
    /**
     * Constructs a new RecordStoreNotFoundException with the
     *  specified detail message.
     * 
     * Parameters:message - the detail message
     */
    public RecordStoreNotFoundException(String message) {
        construct(message);
    }

    private native void construct(String message);

    /**
     * Constructs a new RecordStoreNotFoundException
     *  with no detail message.
     */
    public RecordStoreNotFoundException() {
        construct();
    }

    private native void construct();

}
