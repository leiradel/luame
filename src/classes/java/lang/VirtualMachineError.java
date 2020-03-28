package java.lang;

public class VirtualMachineError extends Error {
    /**
     * Constructs a VirtualMachineError with no detail message.
     */
    public VirtualMachineError() {
        construct();
    }

    private native void construct();

    /**
     * Constructs a VirtualMachineError with the specified
     *  detail message.
     * 
     * Parameters:s - the detail message.
     */
    public VirtualMachineError(String s) {
        construct(s);
    }

    private native void construct(String s);

}
