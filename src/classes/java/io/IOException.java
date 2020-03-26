package java.io;

public class IOException extends Exception {
    /**
     * Constructs an IOException with null
     *  as its error detail message.
     */
    public IOException() {
        super();
    }

    /**
     * Constructs an IOException with the
     * specified detail message.
     */
    public IOException(String message) {
        super(message);
    }
}
