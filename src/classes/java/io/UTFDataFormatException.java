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

}
