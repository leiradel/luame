package javax.microedition.media;

public class MediaException extends Exception {
    /**
     * Constructs a MediaException with null
     *  as its error detail message.
     */
    public MediaException() {
        construct();
    }

    private native void construct();

}
