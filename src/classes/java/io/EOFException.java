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

}
