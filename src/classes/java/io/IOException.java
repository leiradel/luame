package java.io;

public class IOException extends Exception {
    /**
     * Constructs an IOException with null
     *  as its error detail message.
     */
    public IOException() {
        construct();
    }

    private native void construct();

    /**
     * Constructs an IOException with the specified detail
     *  message. The error message string s can later be
     *  retrieved by the Throwable.getMessage()
     *  method of class java.lang.Throwable.
     * 
     * Parameters:s - the detail message.
     */
    public IOException(String s) {
        construct(s);
    }

    private native void construct(String s);

}
