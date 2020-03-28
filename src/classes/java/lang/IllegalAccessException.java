package java.lang;

public class IllegalAccessException extends Exception {
    /**
     * Constructs an IllegalAccessException without a
     *  detail message.
     */
    public IllegalAccessException() {
        construct();
    }

    private native void construct();

    /**
     * Constructs an IllegalAccessException with a detail message.
     * 
     * Parameters:s - the detail message.
     */
    public IllegalAccessException(String s) {
        construct(s);
    }

    private native void construct(String s);

}
