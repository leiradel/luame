package java.lang;

public class ClassNotFoundException extends Exception {
    /**
     * Constructs a ClassNotFoundException with no detail message.
     */
    public ClassNotFoundException() {
        construct();
    }

    private native void construct();

    /**
     * Constructs a ClassNotFoundException with the
     *  specified detail message.
     * 
     * Parameters:s - the detail message.
     */
    public ClassNotFoundException(String s) {
        construct(s);
    }

    private native void construct(String s);

}
