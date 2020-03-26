package javax.microedition.lcdui.game;

public class GameCanvas extends Canvas {
    /**
     * The bit representing the UP key.  This constant has a value of
     *  0x0002 (1
     * 
     * See Also:Constant Field Values
     */
    public static final int UP_PRESSED = ;

    /**
     * The bit representing the DOWN key.  This constant has a value of
     *  0x0040 (1
     * 
     * See Also:Constant Field Values
     */
    public static final int DOWN_PRESSED = ;

    /**
     * The bit representing the LEFT key.  This constant has a value of
     *  0x0004 (1
     * 
     * See Also:Constant Field Values
     */
    public static final int LEFT_PRESSED = ;

    /**
     * The bit representing the RIGHT key.  This constant has a value of
     *  0x0020 (1
     * 
     * See Also:Constant Field Values
     */
    public static final int RIGHT_PRESSED = ;

    /**
     * The bit representing the FIRE key.  This constant has a value of
     *  0x0100 (1
     * 
     * See Also:Constant Field Values
     */
    public static final int FIRE_PRESSED = ;

    /**
     * The bit representing the GAME_A key (may not be supported on all
     *  devices).  This constant has a value of
     *  0x0200 (1
     * 
     * See Also:Constant Field Values
     */
    public static final int GAME_A_PRESSED = ;

    /**
     * The bit representing the GAME_B key (may not be supported on all
     *  devices).  This constant has a value of
     *  0x0400 (1
     * 
     * See Also:Constant Field Values
     */
    public static final int GAME_B_PRESSED = ;

    /**
     * The bit representing the GAME_C key (may not be supported on all
     *  devices).  This constant has a value of
     *  0x0800 (1
     * 
     * See Also:Constant Field Values
     */
    public static final int GAME_C_PRESSED = ;

    /**
     * The bit representing the GAME_D key (may not be supported on all
     *  devices).  This constant has a value of
     *  0x1000 (1
     * 
     * See Also:Constant Field Values
     */
    public static final int GAME_D_PRESSED = ;

    /**
     * Obtains the Graphics object for rendering a GameCanvas.  The returned
     *  Graphics object renders to the off-screen buffer belonging to this
     *  GameCanvas.
     * 
     *  Rendering operations do not appear on the display until flushGraphics()
     *  is called; flushing the buffer does not change its contents (the pixels
     *  are not cleared as a result of the flushing operation).
     * 
     *  A new Graphics object is created and returned each time this method is
     *  called; therefore, the needed Graphics object(s) should be obtained
     *  before the game starts then re-used while the game is running.
     *  For each GameCanvas instance, all of the provided graphics objects will
     *  render to the same off-screen buffer.
     * 
     *  The newly created Graphics object has the following properties:
     * 
     *  the destination is this GameCanvas' buffer;
     *  the clip region encompasses the entire buffer;
     *  the current color is black;
     *  the font is the same as the font returned by
     *  Font.getDefaultFont();
     *  the stroke style is SOLID; and
     *  the origin of the coordinate system is located at the upper-left
     *  corner of the buffer.
     * 
     * Returns:the Graphics object that renders to this GameCanvas'
     *  off-screen bufferSee Also:flushGraphics(),
     * flushGraphics(int, int, int, int)
     */
    protected Graphics getGraphics();

    /**
     * Gets the states of the physical game keys.  Each bit in the returned
     *  integer represents a specific key on the device.  A key's bit will be
     *  1 if the key is currently down or has been pressed at least once since
     *  the last time this method was called.  The bit will be 0 if the key
     *  is currently up and has not been pressed at all since the last time
     *  this method was called.  This latching behavior ensures that a rapid
     *  key press and release will always be caught by the game loop,
     *  regardless of how slowly the loop runs.
     * 
     *  For example:
     * 
     *       // Get the key state and store it
     *       int keyState = getKeyStates();
     *       if ((keyState & LEFT_KEY) != 0) {
     *           positionX--;
     *       }
     *       else if ((keyState & RIGHT_KEY) != 0) {
     *           positionX++;
     *       }
     * 
     *  Calling this method has the side effect of clearing any latched state.
     *  Another call to getKeyStates immediately after a prior call will
     *  therefore report the system's best idea of the current state of the
     *  keys, the latched bits having been cleared by the first call.
     * 
     *  Some devices may not be able to query the keypad hardware directly and
     *  therefore, this method may be implemented by monitoring key press and
     *  release events instead.  Thus the state reported by getKeyStates might
     *  lag the actual state of the physical keys since the timeliness
     *  of the key information is be subject to the capabilities of each
     *  device.  Also, some devices may be incapable of detecting simultaneous
     *  presses of multiple keys.
     * 
     *  This method returns 0 unless the GameCanvas is currently visible as
     *  reported by Displayable.isShown().
     *  Upon becoming visible, a GameCanvas will initially indicate that
     *  all keys are unpressed (0); if a key is held down while the GameCanvas
     *  is being shown, the key must be first released and then pressed in
     *  order for the key press to be reported by the GameCanvas.
     * 
     * Returns:An integer containing the key state information (one bit per
     *  key), or 0 if the GameCanvas is not currently shown.See Also:UP_PRESSED,
     * DOWN_PRESSED,
     * LEFT_PRESSED,
     * RIGHT_PRESSED,
     * FIRE_PRESSED,
     * GAME_A_PRESSED,
     * GAME_B_PRESSED,
     * GAME_C_PRESSED,
     * GAME_D_PRESSED
     */
    public int getKeyStates();

    /**
     * Paints this GameCanvas.  By default, this method renders the
     *  the off-screen buffer at (0,0).  Rendering of the buffer is
     *  subject to the clip region and origin translation of the Graphics
     *  object.
     * 
     * Specified by:paint in class Canvas
     * 
     * Parameters:g - the Graphics object with which to render the screen.
     * Throws:
     * NullPointerException - if g is null
     */
    public void paint(Graphics g);

    /**
     * Flushes the specified region of the off-screen buffer to the display.
     *  The contents of the off-screen buffer are not changed as a result of
     *  the flush operation.  This method does not return until the flush has
     *  been completed, so the app may immediately begin to render the next
     *  frame to the same buffer once this method returns.
     * 
     *  If the specified region extends beyond the current bounds of the
     *  GameCanvas, only the intersecting region is flushed.  No pixels are
     *  flushed if the specified width or height is less than 1.
     * 
     *  This method does nothing and returns immediately if the GameCanvas is
     *  not currently shown or the flush request cannot be honored because the
     *  system is busy.
     * 
     * Parameters:x - the left edge of the region to be flushedy - the top edge of the region to be flushedwidth - the width of the region to be flushedheight - the height of the region to be flushedSee Also:flushGraphics()
     */
    public void flushGraphics(int x,
                          int y,
                          int width,
                          int height);

}
