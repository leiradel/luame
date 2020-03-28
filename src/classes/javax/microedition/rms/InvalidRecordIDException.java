package javax.microedition.rms;

public class InvalidRecordIDException extends RecordStoreException {
    /**
     * Constructs a new InvalidRecordIDException with the
     *  specified detail message.
     * 
     * Parameters:message - the detail message
     */
    public InvalidRecordIDException(String message) {
        construct(message);
    }

    private native void construct(String message);

    /**
     * Constructs a new InvalidRecordIDException with no detail
     *  message.
     */
    public InvalidRecordIDException() {
        construct();
    }

    private native void construct();

}
