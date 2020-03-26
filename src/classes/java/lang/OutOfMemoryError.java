package java.lang;

public class OutOfMemoryError extends VirtualMachineError {
    /**
     * Constructs an OutOfMemoryError with no detail message.
     */
    public OutOfMemoryError() {
        construct();
    }

    private native void construct();

}
