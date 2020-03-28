package java.io;

public class UnsupportedEncodingException extends IOException {
    /**
     * Constructs an UnsupportedEncodingException without a detail message.
     */
    public UnsupportedEncodingException() {
        construct();
    }

    private native void construct();

    /**
     * Constructs an UnsupportedEncodingException with a detail message.
     * 
     * Parameters:s - Describes the reason for the exception.
     */
    public UnsupportedEncodingException(String s) {
        construct(s);
    }

    private native void construct(String s);

}
