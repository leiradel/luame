package java.io;

public class UnsupportedEncodingException extends IOException {
    /**
     * Constructs an UnsupportedEncodingException without a detail message.
     */
    public UnsupportedEncodingException() {
        construct();
    }

    private native void construct();

}
