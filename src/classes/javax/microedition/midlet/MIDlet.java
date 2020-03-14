package javax.microedition.midlet;

public abstract class MIDlet {
    // Protected constructor for subclasses.
    protected MIDlet() {construct(this);}
    private native void construct(MIDlet self);

    // Signals the MIDlet to terminate and enter the Destroyed state.
    protected abstract void destroyApp(boolean unconditional);

    // Provides a MIDlet with a mechanism to retrieve named properties from the application management software.
    public native String getAppProperty(String key);

    // Used by an MIDlet to notify the application management software that it has entered into the Destroyed state.
    public native void notifyDestroyed();

    // Notifies the application management software that the MIDlet does not want to be active and has entered the Paused state.
    public native void notifyPaused();

    // Signals the MIDlet to stop and enter the Paused state.
    protected abstract void pauseApp();
 
    // Provides a MIDlet with a mechanism to indicate that it is interested in entering the Active state.
    public native void resumeRequest();

    // Signals the MIDlet that it has entered the Active state.
    protected abstract void startApp();
}
