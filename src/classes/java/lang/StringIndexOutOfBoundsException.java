package java.lang;

public class StringIndexOutOfBoundsException extends IndexOutOfBoundsException {
    /**
     * Constructs a StringIndexOutOfBoundsException with no
     *  detail message.
     * 
     * Since:
     *   JDK1.0.
     */
    public StringIndexOutOfBoundsException() {
        construct();
    }

    private native void construct();

    /**
     * Constructs a StringIndexOutOfBoundsException with
     *  the specified detail message.
     * 
     * Parameters:s - the detail message.
     */
    public StringIndexOutOfBoundsException(String s) {
        construct(s);
    }

    private native void construct(String s);

}
