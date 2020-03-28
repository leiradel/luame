package java.lang;

public class Runtime {
    /**
     * Returns the runtime object associated with the current Java application.
     *  Most of the methods of class Runtime are instance
     *  methods and must be invoked with respect to the current runtime object.
     * 
     * Returns:the Runtime object associated with the current
     *           Java application.
     */
    public static Runtime getRuntime();

    /**
     * Terminates the currently running Java application. This
     *  method never returns normally.
     * 
     *  The argument serves as a status code; by convention, a nonzero
     *  status code indicates abnormal termination.
     * 
     * Parameters:status - exit status.Since:
     *   JDK1.0
     */
    public void exit(int status);

    /**
     * Returns the amount of free memory in the system. Calling the
     *  gc method may result in increasing the value returned
     *  by freeMemory.
     * 
     * Returns:an approximation to the total amount of memory currently
     *           available for future allocated objects, measured in bytes.
     */
    public long freeMemory();

    /**
     * Returns the total amount of memory in the Java Virtual Machine.
     *  The value returned by this method may vary over time, depending on
     *  the host environment.
     * 
     *  Note that the amount of memory required to hold an object of any
     *  given type may be implementation-dependent.
     * 
     * Returns:the total amount of memory currently available for current
     *           and future objects, measured in bytes.
     */
    public long totalMemory();

    /**
     * Runs the garbage collector.
     *  Calling this method suggests that the Java Virtual Machine expend
     *  effort toward recycling unused objects in order to make the memory
     *  they currently occupy available for quick reuse. When control
     *  returns from the method call, the Java Virtual Machine has made
     *  its best effort to recycle all discarded objects.
     * 
     *  The name gc stands for "garbage
     *  collector". The Java Virtual Machine performs this recycling
     *  process automatically as needed, in a separate thread, even if the
     *  gc method is not invoked explicitly.
     * 
     *  The method System.gc() is hte conventional and convenient
     *  means of invoking this method.
     */
    public void gc();

}
