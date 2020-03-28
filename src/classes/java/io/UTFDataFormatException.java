package java.io;

public class UTFDataFormatException extends IOException {
    /**
     * Constructs a UTFDataFormatException with
     *  null as its error detail message.
     */
    public UTFDataFormatException() {
        construct();
    }

    private native void construct();

    /**
     * Constructs a UTFDataFormatException with the
     *  specified detail message. The string s can be
     *  retrieved later by the
     *  Throwable.getMessage()
     *  method of class java.lang.Throwable.
     * 
     * Parameters:s - the detail message.
     */
    public UTFDataFormatException(String s) {
        construct(s);
    }

    private native void construct(String s);

}
