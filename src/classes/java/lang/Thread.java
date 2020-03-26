package java.lang;

public class Thread {
    /**
     * The minimum priority that a thread can have.
     * 
     * See Also:Constant Field Values
     */
    public static final int MIN_PRIORITY = ;

    /**
     * The default priority that is assigned to a thread.
     * 
     * See Also:Constant Field Values
     */
    public static final int NORM_PRIORITY = ;

    /**
     * The maximum priority that a thread can have.
     * 
     * See Also:Constant Field Values
     */
    public static final int MAX_PRIORITY = ;

    /**
     * Allocates a new Thread object.
     * 
     *  Threads created this way must have overridden their
     *  run() method to actually do anything.
     * 
     * See Also:Runnable
     */
    public Thread() {
        construct();
    }

    private native void construct();

    /**
     * Allocates a new Thread object with the
     *  given name.
     * 
     *  Threads created this way must have overridden their
     *  run() method to actually do anything.
     * 
     * Parameters:name - the name of the new thread.
     */
    public Thread(String name) {
        construct(name);
    }

    private native void construct(String name);

    /**
     * Allocates a new Thread object with a
     *  specific target object whose run method
     *  is called.
     * 
     * Parameters:target - the object whose run method is called.
     */
    public Thread(Runnable target) {
        construct(target);
    }

    private native void construct(Runnable target);

    /**
     * Returns a reference to the currently executing
     *  Thread object.
     * 
     * Returns:the currently executing thread.
     */
    public static Thread currentThread();

    /**
     * Causes the currently executing thread object
     *  to temporarily pause and allow other threads to execute.
     */
    public static void yield();

    /**
     * Causes the currently executing thread to sleep (temporarily cease
     *  execution) for the specified number of milliseconds. The thread
     *  does not lose ownership of any monitors.
     * 
     * Parameters:millis - the length of time to sleep in milliseconds.
     * Throws:
     * InterruptedException - if another thread has interrupted
     *              the current thread.  The interrupted status of the
     *              current thread is cleared when this exception is thrown.See Also:Object.notify()
     */
    public static void sleep(long millis)
                  throws InterruptedException;

    /**
     * Causes this thread to begin execution; the Java Virtual Machine
     *  calls the run method of this thread.
     * 
     *  The result is that two threads are running concurrently: the
     *  current thread (which returns from the call to the
     *  start method) and the other thread (which executes its
     *  run method).
     * 
     * Throws:
     * IllegalThreadStateException - if the thread was already
     *                started.See Also:run()
     */
    public void start();

    /**
     * If this thread was constructed using a separate
     *  Runnable run object, then that
     *  Runnable object's run method is called;
     *  otherwise, this method does nothing and returns.
     * 
     *  Subclasses of Thread should override this method.
     * 
     * Specified by:run in interface Runnable
     * 
     * See Also:start(),
     * Runnable.run()
     */
    public void run();

    /**
     * Interrupts this thread.  In an implementation conforming
     *  to the CLDC Specification, this operation is not
     *  required to cancel or clean up any pending I/O operations
     *  that the thread may be waiting for.
     * 
     * Since:
     *   JDK 1.0, CLDC 1.1
     */
    public void interrupt();

    /**
     * Tests if this thread is alive. A thread is alive if it has
     *  been started and has not yet died.
     * 
     * Returns:true if this thread is alive;
     *           false otherwise.
     */
    public final boolean isAlive();

    /**
     * Changes the priority of this thread.
     * 
     * Parameters:newPriority - priority to set this thread to
     * Throws:
     * IllegalArgumentException - If the priority is not in the
     *              range MIN_PRIORITY to
     *              MAX_PRIORITY.See Also:getPriority(),
     * MAX_PRIORITY,
     * MIN_PRIORITY
     */
    public final void setPriority(int newPriority);

    /**
     * Returns this thread's priority.
     * 
     * Returns:this thread's priority.See Also:setPriority(int)
     */
    public final int getPriority();

    /**
     * Returns this thread's name.  Note that in CLDC the name
     *  of the thread can only be set when creating the thread.
     * 
     * Returns:this thread's name.
     */
    public final String getName();

    /**
     * Returns the current number of active threads in the virtual machine.
     * 
     * Returns:the current number of active threads.
     */
    public static int activeCount();

    /**
     * Waits for this thread to die.
     * 
     * Throws:
     * InterruptedException - if another thread has interrupted
     *              the current thread.  The interrupted status of the
     *              current thread is cleared when this exception is thrown.
     */
    public final void join()
                throws InterruptedException;

}
