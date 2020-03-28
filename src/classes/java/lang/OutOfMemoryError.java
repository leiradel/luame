package java.lang;

public class OutOfMemoryError extends VirtualMachineError {
    /**
     * Constructs an OutOfMemoryError with no detail message.
     */
    public OutOfMemoryError() {
        construct();
    }

    private native void construct();

    /**
     * Constructs an OutOfMemoryError with the specified
     *  detail message.
     * 
     * Parameters:s - the detail message.
     */
    public OutOfMemoryError(String s) {
        construct(s);
    }

    private native void construct(String s);

}
