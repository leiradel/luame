package java.io;

public class EOFException extends IOException {
    /**
     * Constructs an EOFException with null
     *  as its error detail message.
     */
    public EOFException() {
        construct();
    }

    private native void construct();

    /**
     * Constructs an EOFException with the specified detail
     *  message. The string s may later be retrieved by the
     *  Throwable.getMessage() method of class
     *  java.lang.Throwable.
     * 
     * Parameters:s - the detail message.
     */
    public EOFException(String s) {
        construct(s);
    }

    private native void construct(String s);

}
