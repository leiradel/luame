package java.lang;

public class Throwable {
    /**
     * Constructs a new Throwable with null as
     *  its error message string.
     */
    public Throwable() {
        construct();
    }

    private native void construct();

    /**
     * Returns the error message string of this Throwable object.
     * 
     * Returns:the error message string of this Throwable
     *           object if it was created with an
     *           error message string; or null if it was
     *           created with no error message.
     */
    public String getMessage();

    /**
     * Returns a short description of this Throwable object.
     *  If this Throwable object was
     *  created with an error message string,
     *  then the result is the concatenation of three strings:
     * 
     *  The name of the actual class of this object
     *  ": " (a colon and a space)
     *  The result of the getMessage() method for this object
     * 
     *  If this Throwable object was created
     *  with no error message string, then the name of the actual class of
     *  this object is returned.
     * 
     * Overrides:toString in class Object
     * 
     * Returns:a string representation of this Throwable.
     */
    public String toString();

}
