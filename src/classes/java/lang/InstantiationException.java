package java.lang;

public class InstantiationException extends Exception {
    /**
     * Constructs an InstantiationException with no detail message.
     */
    public InstantiationException() {
        construct();
    }

    private native void construct();

}
