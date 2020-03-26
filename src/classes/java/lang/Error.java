package java.lang;

public class Error extends Throwable {
    /**
     * Constructs an Error with no specified detail message.
     */
    public Error() {
        construct();
    }

    private native void construct();

}
