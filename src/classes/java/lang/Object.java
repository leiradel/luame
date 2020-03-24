package java.lang;

public abstract class Object {
    public Object() {}

    // Indicates whether some other object is "equal to" this one.
    public boolean equals(Object obj) {
        return this == obj;
    }

    // Returns the runtime class of an object.
    public abstract Class getClass();

    // Returns a hash code value for the object.
    public int hashCode() {
        return System.identityHashCode(this);
    }

    // Wakes up a single thread that is waiting on this object's monitor.
    public abstract void notify();

    // Wakes up all threads that are waiting on this object's monitor.
    public abstract void notifyAll();

    // Returns a string representation of the object.
    public abstract String toString();

    // Causes current thread to wait until another thread invokes the notify() method or the notifyAll() method for this object.
    public abstract void wait();

    // Causes current thread to wait until either another thread invokes the notify() method or the notifyAll() method for this object, or a specified amount of time has elapsed.
    public abstract void wait(long timeout);

    // Causes current thread to wait until another thread invokes the notify() method or the notifyAll() method for this object, or some other thread interrupts the current thread, or a certain amount of real time has elapsed.
    public abstract void wait(long timeout, int nanos);
}
