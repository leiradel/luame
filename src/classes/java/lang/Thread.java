package java.lang;

public class Thread {
    // The maximum priority that a thread can have.
    public static int MAX_PRIORITY = 1;

    // The minimum priority that a thread can have.
    public static int MIN_PRIORITY = 2;

    // The default priority that is assigned to a thread.
    public static int NORM_PRIORITY = 3;

    // Allocates a new Thread object.
    public Thread() {this(null);}

    // Allocates a new Thread object.
    public Thread(Runnable target) {construct(this);}
    private native void construct(Thread self);

    // Returns the current number of active threads in the VM.
    public static native int activeCount();

    // Returns a reference to the currently executing thread object.
    public static native Thread currentThread();

    // Returns this thread's priority.
    public native int getPriority();

    // Tests if this thread is alive.
    public native boolean isAlive();

    // Waits for this thread to die.
    public native void join();

    // If this thread was constructed using a separate Runnable run object, then that Runnable object's run method is called; otherwise, this method does nothing and returns.
    public native void run();

    // Changes the priority of this thread.
    public native void setPriority(int newPriority);

    // Causes the currently executing thread to sleep (temporarily cease execution) for the specified number of milliseconds.
    public static native void sleep(long millis);

    // Causes this thread to begin execution; the Java Virtual Machine calls the run method of this thread.
    public native void start();

    // Returns a string representation of this thread, including a unique number that identifies the thread and the thread's priority.
    public native String toString();

    // Causes the currently executing thread object to temporarily pause and allow other threads to execute.
    public static native void yield();
}
