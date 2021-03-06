package java.lang;

public class ArrayIndexOutOfBoundsException extends IndexOutOfBoundsException {
    /**
     * Constructs an ArrayIndexOutOfBoundsException with no
     *  detail message.
     */
    public ArrayIndexOutOfBoundsException() {
        construct();
    }

    private native void construct();

    /**
     * Constructs a new ArrayIndexOutOfBoundsException
     *  class with an argument indicating the illegal index.
     * 
     * Parameters:index - the illegal index.
     */
    public ArrayIndexOutOfBoundsException(int index) {
        construct(index);
    }

    private native void construct(int index);

    /**
     * Constructs an ArrayIndexOutOfBoundsException class
     *  with the specified detail message.
     * 
     * Parameters:s - the detail message.
     */
    public ArrayIndexOutOfBoundsException(String s) {
        construct(s);
    }

    private native void construct(String s);

}
