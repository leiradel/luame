package java.lang;

public class ClassNotFoundException extends Exception {
    /**
     * Constructs a ClassNotFoundException with no detail message.
     */
    public ClassNotFoundException() {
        construct();
    }

    private native void construct();

}
