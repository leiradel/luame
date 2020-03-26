package javax.microedition.lcdui;

public class Displayable {
    /**
     * Gets the title of the Displayable. Returns
     *  null if there is no title.
     * 
     * Returns:the title of the instance, or null if no titleSince:
     *   MIDP 2.0
     * See Also:setTitle(java.lang.String)
     */
    public String getTitle();

    /**
     * Sets the title of the Displayable. If
     *  null is given,
     *  removes the title.
     * 
     *  If the Displayable is actually visible on
     *  the display,
     *  the implementation should update
     *  the display as soon as it is feasible to do so.
     * 
     *  The existence of a title  may affect the size
     *  of the area available for Displayable content.
     *  Addition, removal, or the setting of the title text at runtime
     *  may dynamically change the size of the content area.
     *  This is most important to be aware of when using the
     *  Canvas class.
     *  If the available area does change, the application will be notified
     *  via a call to sizeChanged().
     * 
     * Parameters:s - the new title, or null for no titleSince:
     *   MIDP 2.0
     * See Also:getTitle()
     */
    public void setTitle(String s);

    /**
     * Gets the ticker used by this Displayable.
     * 
     * Returns:ticker object used, or null if no
     *  ticker is presentSince:
     *   MIDP 2.0
     * See Also:setTicker(javax.microedition.lcdui.Ticker)
     */
    public Ticker getTicker();

    /**
     * Sets a ticker for use with this Displayable,
     *  replacing any
     *  previous ticker.
     *  If null, removes the ticker object
     *  from this Displayable. The same ticker may be shared by
     *  several Displayable
     *  objects within an application. This is done by calling
     *  setTicker()
     *  with the same Ticker object on several
     *  different Displayable objects.
     *  If the Displayable is actually visible on the display,
     *  the implementation should update
     *  the display as soon as it is feasible to do so.
     * 
     *  The existence of a ticker may affect the size
     *  of the area available for Displayable's contents.
     *  Addition, removal, or the setting of the ticker at runtime
     *  may dynamically change the size of the content area.
     *  This is most important to be aware of when using the
     *  Canvas class.
     *  If the available area does change, the application will be notified
     *  via a call to sizeChanged().
     * 
     * Parameters:ticker - the ticker object used on this screenSince:
     *   MIDP 2.0
     * See Also:getTicker()
     */
    public void setTicker(Ticker ticker);

    /**
     * Checks if the Displayable is actually visible
     *  on the display.  In order
     *  for a Displayable to be visible, all of the
     *  following must be true:
     *  the Display's MIDlet must be
     *  running in the foreground, the Displayable
     *  must be the Display's current screen, and the
     *  Displayable must not be
     *  obscured by a
     *  system screen.
     * 
     * Returns:true if the
     *  Displayable is currently visible
     */
    public boolean isShown();

    /**
     * Adds a command to the Displayable. The
     *  implementation may choose,
     *  for example,
     *  to add the command to any of the available soft buttons or place it
     *  in a menu.
     *  If the added command is already in the screen (tested by comparing the
     *  object references), the method has no effect.
     *  If the Displayable is actually visible on the
     *  display, and this call
     *  affects the set of visible commands, the implementation should update
     *  the display as soon as it is feasible to do so.
     * 
     * Parameters:cmd - the command to be added
     * Throws:
     * NullPointerException - if cmd is
     *  null
     */
    public void addCommand(Command cmd);

    /**
     * Removes a command from the Displayable.
     *  If the command is not in the Displayable
     *  (tested by comparing the
     *  object references), the method has no effect.
     *  If the Displayable is actually visible on the
     *  display, and this call
     *  affects the set of visible commands, the implementation should update
     *  the display as soon as it is feasible to do so.
     *  If cmd is null, this method
     *  does nothing.
     * 
     * Parameters:cmd - the command to be removed
     */
    public void removeCommand(Command cmd);

    /**
     * Sets a listener for Commands to this
     *  Displayable,
     *  replacing any previous CommandListener. A
     *  null reference is
     *  allowed and has the effect of removing any existing listener.
     * 
     * Parameters:l - the new listener, or null.
     */
    public void setCommandListener(CommandListener l);

    /**
     * Gets the width in pixels of the displayable area available to the
     *  application.  The value returned is appropriate for the particular
     *  Displayable subclass.  This value may depend
     *  on how the device uses the
     *  display and may be affected by the presence of a title, a ticker, or
     *  commands.
     *  This method returns the proper result at all times, even if the
     *  Displayable object has not yet been shown.
     * 
     * Returns:width of the area available to the applicationSince:
     *   MIDP 2.0
     */
    public int getWidth();

    /**
     * Gets the height in pixels of the displayable area available to the
     *  application.  The value returned is appropriate for the particular
     *  Displayable subclass.  This value may depend
     *  on how the device uses the
     *  display and may be affected by the presence of a title, a ticker, or
     *  commands.
     *  This method returns the proper result at all times, even if the
     *  Displayable object has not yet been shown.
     * 
     * Returns:height of the area available to the applicationSince:
     *   MIDP 2.0
     */
    public int getHeight();

}
