package java.lang;

public class VirtualMachineError extends Error {
    /**
     * Constructs a VirtualMachineError with no detail message.
     */
    public VirtualMachineError() {
        construct();
    }

    private native void construct();

}
