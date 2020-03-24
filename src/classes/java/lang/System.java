package java.lang;

public class System {
    // Copies an array from the specified source array, beginning at the specified position, to the specified position of the destination array.
    public static native void arraycopy(Object src, int src_position, Object dst, int dst_position, int length);

    // Returns the current time in milliseconds.
    public static native long currentTimeMillis();

    // Terminates the currently running Java application.
    public static native void exit(int status);

    // Runs the garbage collector.
    public static native void gc();

    // Gets the system property indicated by the specified key.
    public static native String getProperty(String key);

    // Returns the same hashcode for the given object as would be returned by the default method hashCode(), whether or not the given object's class overrides hashCode().
    public static native int identityHashCode(Object x);
}
