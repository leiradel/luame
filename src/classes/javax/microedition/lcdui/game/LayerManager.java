package javax.microedition.lcdui.game;

public class LayerManager {
    /**
     * Appends a Layer to this LayerManager.  The Layer is appended to the
     *  list of existing Layers such that it has the highest index (i.e. it
     *  is furthest away from the user).  The Layer is first removed
     *  from this LayerManager if it has already been added.
     * 
     * Parameters:l - the Layer to be added
     * Throws:
     * NullPointerException - if the Layer is
     *  nullSee Also:insert(Layer, int),
     * remove(Layer)
     */
    public void append(Layer l);

    /**
     * Inserts a new Layer in this LayerManager at the specified index.
     *  The Layer is first removed from this LayerManager if it has already
     *  been added.
     * 
     * Parameters:l - the Layer to be insertedindex - the index at which the new Layer is
     *  to be inserted
     * Throws:
     * NullPointerException - if the Layer is
     *  null
     * IndexOutOfBoundsException - if the index is less than
     *  0 or
     *  greater than the number of Layers already added to the this
     *  LayerManagerSee Also:append(Layer),
     * remove(Layer)
     */
    public void insert(Layer l,
                   int index);

    /**
     * Gets the Layer with the specified index.
     * 
     * Parameters:index - the index of the desired Layer
     * Returns:the Layer that has the specified index
     * Throws:
     * IndexOutOfBoundsException - if the specified
     *  index is less than
     *  zero, or if it is equal to or greater than the number of Layers added
     *  to the this LayerManager
     */
    public Layer getLayerAt(int index);

    /**
     * Gets the number of Layers in this LayerManager.
     * 
     * Returns:the number of Layers
     */
    public int getSize();

    /**
     * Removes the specified Layer from this LayerManager.  This method does
     *  nothing if the specified Layer is not added to the this LayerManager.
     * 
     * Parameters:l - the Layer to be removed
     * Throws:
     * NullPointerException - if the specified Layer is
     *  nullSee Also:append(Layer),
     * insert(Layer, int)
     */
    public void remove(Layer l);

    /**
     * Renders the LayerManager's current view window at the specified
     *  location.
     * 
     *  The LayerManager renders each of its layers in order of descending
     *  index, thereby implementing the correct z-order.  Layers that are
     *  completely outside of the view window are not rendered.
     * 
     *  The coordinates passed to this method determine where the
     *  LayerManager's view window will be rendered relative to the origin
     *  of the Graphics object.  For example, a game may use the top of the
     *  screen to display the current score, so to render the game's layers
     *  below that area, the view window might be rendered at (0, 20).  The
     *  location is relative to the Graphics object's origin, so translating
     *  the Graphics object will change where the view window is rendered on
     *  the screen.
     * 
     *  The clip region of the Graphics object is intersected with a region
     *  having the same dimensions as the view window and located at (x,y).
     *  The LayerManager then translates the graphics object such that the
     *  point (x,y) corresponds to the location of the viewWindow in the
     *  coordinate system of the LayerManager.  The Layers are then rendered
     *  in the appropriate order.  The translation and clip region of the
     *  Graphics object are restored to their prior values before this method
     *  returns.
     * 
     *  Rendering is subject to the clip region and translation of the Graphics
     *  object.  Thus, only part of the specified view window may be rendered
     *  if the clip region is not large enough.
     * 
     *  For performance reasons, this method may ignore Layers that are
     *  invisible or that would be rendered entirely outside of the Graphics
     *  object's clip region.  The attributes of the Graphics object are not
     *  restored to a known state between calls to the Layers' paint methods.
     *  The clip region may extend beyond the bounds of a Layer; it is the
     *  responsibility of the Layer to ensure that rendering operations are
     *  performed within its bounds.
     * 
     * Parameters:g - the graphics instance with which to draw the LayerManagerx - the horizontal location at which to render the view window,
     *  relative to the Graphics' translated originy - the vertical location at which to render the view window,
     *  relative to the Graphics' translated origin
     * Throws:
     * NullPointerException - if g is nullSee Also:setViewWindow(int, int, int, int)
     */
    public void paint(Graphics g,
                  int x,
                  int y);

}
