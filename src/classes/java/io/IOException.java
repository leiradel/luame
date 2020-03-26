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

}
