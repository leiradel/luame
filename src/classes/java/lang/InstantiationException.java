package java.lang;

public class InstantiationException extends Exception {
    /**
     * Constructs an InstantiationException with no detail message.
     */
    public InstantiationException() {
        construct();
    }

    private native void construct();

    /**
     * Constructs an InstantiationException with the
     *  specified detail message.
     * 
     * Parameters:s - the detail message.
     */
    public InstantiationException(String s) {
        construct(s);
    }

    private native void construct(String s);

}
