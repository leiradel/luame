package javax.microedition.lcdui;

public class Canvas extends Displayable {
    /**
     * Constant for the UP game action.
     * 
     *  Constant value 1 is set to UP.
     * 
     * See Also:Constant Field Values
     */
    public static final int UP = 1;

    /**
     * Constant for the DOWN game action.
     * 
     *  Constant value 6 is set to DOWN.
     * 
     * See Also:Constant Field Values
     */
    public static final int DOWN = 6;

    /**
     * Constant for the LEFT game action.
     * 
     *  Constant value 2 is set to LEFT.
     * 
     * See Also:Constant Field Values
     */
    public static final int LEFT = 2;

    /**
     * Constant for the RIGHT game action.
     * 
     *  Constant value 5 is set to RIGHT.
     * 
     * See Also:Constant Field Values
     */
    public static final int RIGHT = 5;

    /**
     * Constant for the FIRE game action.
     * 
     *  Constant value 8 is set to FIRE.
     * 
     * See Also:Constant Field Values
     */
    public static final int FIRE = 8;

    /**
     * Constant for the general purpose &quot;A&quot; game action.
     * 
     *  Constant value 9 is set to GAME_A.
     * 
     * See Also:Constant Field Values
     */
    public static final int GAME_A = 9;

    /**
     * Constant for the general purpose &quot;B&quot; game action.
     * 
     *  Constant value 10 is set to GAME_B.
     * 
     * See Also:Constant Field Values
     */
    public static final int GAME_B = 10;

    /**
     * Constant for the general purpose &quot;C&quot; game action.
     * 
     *  Constant value 11 is set to GAME_C.
     * 
     * See Also:Constant Field Values
     */
    public static final int GAME_C = 11;

    /**
     * Constant for the general purpose &quot;D&quot; game action.
     * 
     *  Constant value 12 is set to GAME_D.
     * 
     * See Also:Constant Field Values
     */
    public static final int GAME_D = 12;

    /**
     * keyCode for ITU-T key 0.
     * 
     *  Constant value 48 is set to KEY_NUM0.
     * 
     * See Also:Constant Field Values
     */
    public static final int KEY_NUM0 = 48;

    /**
     * keyCode for ITU-T key 1.
     * 
     *  Constant value 49 is set to KEY_NUM1.
     * 
     * See Also:Constant Field Values
     */
    public static final int KEY_NUM1 = 49;

    /**
     * keyCode for ITU-T key 2.
     * 
     *  Constant value 50 is set to KEY_NUM2.
     * 
     * See Also:Constant Field Values
     */
    public static final int KEY_NUM2 = 50;

    /**
     * keyCode for ITU-T key 3.
     * 
     *  Constant value 51 is set to KEY_NUM3.
     * 
     * See Also:Constant Field Values
     */
    public static final int KEY_NUM3 = 51;

    /**
     * keyCode for ITU-T key 4.
     * 
     *  Constant value 52 is set to KEY_NUM4.
     * 
     * See Also:Constant Field Values
     */
    public static final int KEY_NUM4 = 52;

    /**
     * keyCode for ITU-T key 5.
     * 
     *  Constant value 53 is set to KEY_NUM5.
     * 
     * See Also:Constant Field Values
     */
    public static final int KEY_NUM5 = 53;

    /**
     * keyCode for ITU-T key 6.
     * 
     *  Constant value 54 is set to KEY_NUM6.
     * 
     * See Also:Constant Field Values
     */
    public static final int KEY_NUM6 = 54;

    /**
     * keyCode for ITU-T key 7.
     * 
     *  Constant value 55 is set to KEY_NUM7.
     * 
     * See Also:Constant Field Values
     */
    public static final int KEY_NUM7 = 55;

    /**
     * keyCode for ITU-T key 8.
     * 
     *  Constant value 56 is set to KEY_NUM8.
     * 
     * See Also:Constant Field Values
     */
    public static final int KEY_NUM8 = 56;

    /**
     * keyCode for ITU-T key 9.
     * 
     *  Constant value 57 is set to KEY_NUM09.
     * 
     * See Also:Constant Field Values
     */
    public static final int KEY_NUM9 = 57;

    /**
     * keyCode for ITU-T key &quot;star&quot; (*).
     * 
     *  Constant value 42 is set to KEY_STAR.
     * 
     * See Also:Constant Field Values
     */
    public static final int KEY_STAR = 42;

    /**
     * keyCode for ITU-T key &quot;pound&quot; (#).
     * 
     *  Constant value 35 is set to KEY_POUND.
     * 
     * See Also:Constant Field Values
     */
    public static final int KEY_POUND = 35;

    /**
     * Checks if the Canvas is double buffered by the
     *  implementation.
     * 
     * Returns:true if double buffered,
     *  false otherwise
     */
    public boolean isDoubleBuffered();

    /**
     * Checks if the platform supports pointer press and release events.
     * 
     * Returns:true if the device supports pointer events
     */
    public boolean hasPointerEvents();

    /**
     * Checks if the platform supports pointer motion events (pointer dragged).
     *  Applications may use this method to determine if the platform is capable
     *  of supporting motion events.
     * 
     * Returns:true if the device supports pointer motion events
     */
    public boolean hasPointerMotionEvents();

    /**
     * Checks if the platform can generate repeat events when key
     *  is kept down.
     * 
     * Returns:true if the device supports repeat events
     */
    public boolean hasRepeatEvents();

    /**
     * Gets a key code that corresponds to the specified game action on the
     *  device.  The implementation is required to provide a mapping for every
     *  game action, so this method will always return a valid key code for
     *  every game action.  See above for further
     *  discussion of game actions.  There may be multiple keys associated
     *  with the same game action; however, this method will return only one of
     *  them.  Applications should translate the key code of every key event
     *  into a game action using getGameAction(int) and then interpret the
     *  resulting game action, instead of generating a table of key codes at
     *  using this method during initialization.
     * 
     *  The mapping between key codes and game actions
     *  will not change during the execution of the application.
     * 
     * Parameters:gameAction - the game action
     * Returns:a key code corresponding to this game action
     * Throws:
     * IllegalArgumentException - if gameAction
     *  is not a valid game action
     */
    public int getKeyCode(int gameAction);

    /**
     * Gets an informative key string for a key. The string returned will
     *  resemble the text physically printed on the key.  This string is
     *  suitable for displaying to the user.  For example, on a device
     *  with function keys F1 through F4,
     *  calling this method on the keyCode for
     *  the F1 key will return the string
     *  &quot;F1&quot;. A typical use for this string
     *  will be to compose help text such as &quot;Press
     *  F1 to proceed.&quot;
     * 
     *   This method will return a non-empty string for every valid key code.
     * 
     *   There is no direct mapping from game actions to key names. To get
     *  the string name for a game action GAME_A, the
     *  application must call
     * 
     *     getKeyName(getKeyCode(GAME_A));
     * 
     * Parameters:keyCode - the key code being requested
     * Returns:a string name for the key
     * Throws:
     * IllegalArgumentException - if keyCode
     *  is not a valid key code
     */
    public String getKeyName(int keyCode);

    /**
     * Gets the game action associated with the given key code of the
     *  device.  Returns zero if no game action is associated with this key
     *  code.  See above for further discussion of
     *  game actions.
     * 
     *  The mapping between key codes and game actions
     *  will not change during the execution of the application.
     * 
     * Parameters:keyCode - the key code
     * Returns:the game action corresponding to this key, or
     *  0 if none
     * Throws:
     * IllegalArgumentException - if keyCode
     *  is not a valid key code
     */
    public int getGameAction(int keyCode);

    /**
     * Controls whether the Canvas is in full-screen mode
     *  or in normal mode.
     * 
     * Parameters:mode - true if the Canvas
     *  is to be in full screen mode, false otherwiseSince:
     *   MIDP 2.0
     */
    public void setFullScreenMode(boolean mode);

    /**
     * Called when a key is pressed.
     * 
     *  The getGameAction() method can be called to
     *  determine what game action, if any, is mapped to the key.
     *  Class Canvas has an empty implementation of this method, and
     *  the subclass has to redefine it if it wants to listen this method.
     * 
     * Parameters:keyCode - the key code of the key that was pressed
     */
    protected void keyPressed(int keyCode);

    /**
     * Called when a key is repeated (held down).
     * 
     *  The getGameAction() method can
     *  be called to determine what game action,
     *  if any, is mapped to the key.
     *  Class Canvas has an empty implementation of this method, and
     *  the subclass has to redefine it if it wants to listen this method.
     * 
     * Parameters:keyCode - the key code of the key that was repeatedSee Also:hasRepeatEvents()
     */
    protected void keyRepeated(int keyCode);

    /**
     * Called when a key is released.
     * 
     *  The getGameAction() method can be called to
     *  determine what game action, if any, is mapped to the key.
     *  Class Canvas has an empty implementation of this method, and
     *  the subclass has to redefine it if it wants to listen this method.
     * 
     * Parameters:keyCode - the key code of the key that was released
     */
    protected void keyReleased(int keyCode);

    /**
     * Called when the pointer is pressed.
     * 
     *  The hasPointerEvents()
     *  method may be called to determine if the device supports pointer events.
     *  Class Canvas has an empty implementation of this method, and
     *  the subclass has to redefine it if it wants to listen this method.
     * 
     * Parameters:x - the horizontal location where the pointer was pressed (relative
     *  to the Canvas)y - the vertical location where the pointer was pressed
     *  (relative to the Canvas)
     */
    protected void pointerPressed(int x,
                              int y);

    /**
     * Called when the pointer is released.
     * 
     *  The hasPointerEvents()
     *  method may be called to determine if the device supports pointer events.
     *  Class Canvas has an empty implementation of this method, and
     *  the subclass has to redefine it if it wants to listen this method.
     * 
     * Parameters:x - the horizontal location where the pointer was released
     *  (relative to the Canvas)y - the vertical location where the pointer was released
     *  (relative to the Canvas)
     */
    protected void pointerReleased(int x,
                               int y);

    /**
     * Called when the pointer is dragged.
     * 
     *  The hasPointerMotionEvents()
     *  method may be called to determine if the device supports pointer events.
     *  Class Canvas has an empty implementation of this method, and
     *  the subclass has to redefine it if it wants to listen this method.
     * 
     * Parameters:x - the horizontal location where the pointer was dragged
     *  (relative to the Canvas)y - the vertical location where the pointer was dragged
     *  (relative to the Canvas)
     */
    protected void pointerDragged(int x,
                              int y);

    /**
     * Requests a repaint for the specified region of the
     *  Canvas. Calling
     *  this method may result in subsequent call to
     *  paint(), where the passed
     *  Graphics object's clip region will include at
     *  least the specified
     *  region.
     * 
     *   If the canvas is not visible, or if width and height are zero or
     *  less, or if the rectangle does not specify a visible region of
     *  the display, this call has no effect.
     * 
     *   The call to paint() occurs asynchronously of
     *  the call to repaint().
     *  That is, repaint() will not block waiting for
     *  paint() to finish. The
     *  paint() method will either be called after the
     *  caller of repaint()
     *  returns
     *  to the implementation (if the caller is a callback) or on another thread
     *  entirely.
     * 
     *   To synchronize with its paint() routine,
     *  applications can use either
     *  Display.callSerially() or
     *  serviceRepaints(), or they can code explicit
     *  synchronization into their paint() routine.
     * 
     *   The origin of the coordinate system is above and to the left of the
     *  pixel in the upper left corner of the displayable area of the
     *  Canvas.
     *  The X-coordinate is positive right and the Y-coordinate is
     *  positive downwards.
     * 
     * Parameters:x - the x coordinate of the rectangle to be repaintedy - the y coordinate of the rectangle to be repaintedwidth - the width of the rectangle to be repaintedheight - the height of the rectangle to be repaintedSee Also:Display.callSerially(Runnable),
     * serviceRepaints()
     */
    public final void repaint(int x,
                          int y,
                          int width,
                          int height);

    /**
     * Requests a repaint for the entire Canvas. The
     *  effect is identical to
     *    repaint(0, 0, getWidth(), getHeight());
     */
    public final void repaint();

    /**
     * Forces any pending repaint requests to be serviced immediately. This
     *  method blocks until the pending requests have been serviced. If
     *  there are
     *  no pending repaints, or if this canvas is not visible on the display,
     *  this call does nothing and returns immediately.
     * 
     *  Warning: This method blocks until the call to the
     *  application's paint() method returns. The
     *  application has no
     *  control over
     *  which thread calls paint(); it may vary from
     *  implementation to
     *  implementation. If the caller of serviceRepaints()
     *  holds a lock that the
     *  paint() method acquires, this may result in
     *  deadlock. Therefore, callers
     *  of serviceRepaints() must not hold any
     *  locks that might be
     *  acquired within the paint() method. The
     *  Display.callSerially()
     *  method provides a facility where an application can be called back after
     *  painting has completed, avoiding the danger of deadlock.
     * 
     * See Also:Display.callSerially(Runnable)
     */
    public final void serviceRepaints();

    /**
     * The implementation calls showNotify()
     *  immediately prior to this Canvas being made
     *  visible on the display.
     *  Canvas subclasses may override
     *  this method to perform tasks before being shown, such
     *  as setting up animations, starting timers, etc.
     *  The default implementation of this method in class
     *  Canvas is empty.
     */
    protected void showNotify();

    /**
     * The implementation calls hideNotify() shortly
     *  after the Canvas has been
     *  removed from the display.
     *  Canvas subclasses may override this method in
     *  order to pause
     *  animations,
     *  revoke timers, etc.  The default implementation of this
     *  method in class Canvas is empty.
     */
    protected void hideNotify();

    /**
     * Renders the Canvas. The application must implement
     *  this method in
     *  order to paint any graphics.
     * 
     *  The Graphics object's clip region defines the
     *  area of the screen
     *  that is considered to be invalid. A correctly-written
     *  paint() routine
     *  must paint every pixel within this region. This is necessary
     *  because the implementation is not required to clear the region prior to
     *  calling paint() on it.  Thus, failing to paint
     *  every pixel may result
     *  in a portion of the previous screen image remaining visible.
     * 
     *  Applications must not assume that
     *  they know the underlying source of the paint()
     *  call and use this
     *  assumption
     *  to paint only a subset of the pixels within the clip region. The
     *  reason is
     *  that this particular paint() call may have
     *  resulted from multiple
     *  repaint()
     *  requests, some of which may have been generated from outside the
     *  application. An application that paints only what it thinks is
     *  necessary to
     *  be painted may display incorrectly if the screen contents had been
     *  invalidated by, for example, an incoming telephone call.
     * 
     *  Operations on this graphics object after the paint()
     *  call returns are
     *  undefined. Thus, the application must not cache this
     *  Graphics
     *  object for later use or use by another thread. It must only be
     *  used within
     *  the scope of this method.
     * 
     *  The implementation may postpone visible effects of
     *  graphics operations until the end of the paint method.
     * 
     *   The contents of the Canvas are never saved if
     *  it is hidden and then
     *  is made visible again. Thus, shortly after
     *  showNotify() is called,
     *  paint() will always be called with a
     *  Graphics object whose clip region
     *  specifies the entire displayable area of the
     *  Canvas.  Applications
     *  must not rely on any contents being preserved from a previous
     *  occasion when the Canvas was current. This call to
     *  paint() will not
     *  necessarily occur before any other key or pointer
     *  methods are called on the Canvas.  Applications
     *  whose repaint
     *  recomputation is expensive may create an offscreen
     *  Image, paint into it,
     *  and then draw this image on the Canvas when
     *  paint() is called.
     * 
     *  The application code must never call paint();
     *  it is called only by
     *  the implementation.
     * 
     *  The Graphics object passed to the
     *  paint() method has the following
     *  properties:
     * 
     *  the destination is the actual display, or if double buffering is in
     *  effect, a back buffer for the display;
     *  the clip region includes at least one pixel
     *  within this Canvas;
     *  the current color is black;
     *  the font is the same as the font returned by
     *  Font.getDefaultFont();
     *  the stroke style is SOLID;
     *  the origin of the coordinate system is located at the upper-left
     *  corner of the Canvas; and
     *  the Canvas is visible, that is, a call to
     *  isShown() will return
     *  true.
     * 
     * Parameters:g - the Graphics object to be used for
     *  rendering the Canvas
     */
    protected abstract void paint(Graphics g);

}
