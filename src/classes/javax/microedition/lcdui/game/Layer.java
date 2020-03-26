package javax.microedition.lcdui.game;

public class Layer {
    /**
     * Sets this Layer's position such that its upper-left corner
     *  is located at (x,y) in the painter's coordinate system.
     *  A Layer is located at (0,0) by default.
     * 
     * Parameters:x - the horizontal positiony - the vertical positionSee Also:move(int, int),
     * getX(),
     * getY()
     */
    public void setPosition(int x,
                        int y);

    /**
     * Moves this Layer by the specified horizontal and vertical distances.
     * 
     *  The Layer's coordinates are subject to wrapping if the passed
     *  parameters will cause them to exceed beyond Integer.MAX_VALUE
     *  or Integer.MIN_VALUE.
     * 
     * Parameters:dx - the distance to move along horizontal axis (positive
     *  to the right, negative to the left)dy - the distance to move along vertical axis (positive
     *  down, negative up)See Also:setPosition(int, int),
     * getX(),
     * getY()
     */
    public void move(int dx,
                 int dy);

    /**
     * Gets the horizontal position of this Layer's upper-left corner
     *  in the painter's coordinate system.
     * 
     * Returns:the Layer's horizontal position.See Also:getY(),
     * setPosition(int, int),
     * move(int, int)
     */
    public final int getX();

    /**
     * Gets the vertical position of this Layer's upper-left corner
     *  in the painter's coordinate system.
     * 
     * Returns:the Layer's vertical position.See Also:getX(),
     * setPosition(int, int),
     * move(int, int)
     */
    public final int getY();

    /**
     * Gets the current width of this layer, in pixels.
     * 
     * Returns:the width in pixelsSee Also:getHeight()
     */
    public final int getWidth();

    /**
     * Gets the current height of this layer, in pixels.
     * 
     * Returns:the height in pixelsSee Also:getWidth()
     */
    public final int getHeight();

    /**
     * Sets the visibility of this Layer.  A visible Layer is rendered when
     *  its paint(Graphics) method is called; an invisible Layer is
     *  not rendered.
     * 
     * Parameters:visible - true to make the Layer visible,
     *  false to make it invisibleSee Also:isVisible()
     */
    public void setVisible(boolean visible);

    /**
     * Gets the visibility of this Layer.
     * 
     * Returns:true if the Layer is visible,
     *  false if it is invisible.See Also:setVisible(boolean)
     */
    public final boolean isVisible();

}
