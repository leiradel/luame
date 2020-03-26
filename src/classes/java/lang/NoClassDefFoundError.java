package java.lang;

public class NoClassDefFoundError extends Error {
    /**
     * Constructs a NoClassDefFoundError with no detail message.
     */
    public NoClassDefFoundError() {
        construct();
    }

    private native void construct();

}
