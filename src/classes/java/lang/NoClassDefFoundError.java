package java.lang;

public class NoClassDefFoundError extends Error {
    /**
     * Constructs a NoClassDefFoundError with no detail message.
     */
    public NoClassDefFoundError() {
        construct();
    }

    private native void construct();

    /**
     * Constructs a NoClassDefFoundError with the specified
     *  detail message.
     * 
     * Parameters:s - the detail message.
     */
    public NoClassDefFoundError(String s) {
        construct(s);
    }

    private native void construct(String s);

}
