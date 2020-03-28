package javax.microedition.lcdui;

public class Spacer extends Item {
    /**
     * Creates a new Spacer with the given minimum
     *  size.  The Spacer's label
     *  is null.
     *  The minimum size must be zero or greater.
     *  If minWidth is greater than the
     *  implementation-defined maximum width, the maximum
     *  width will be used instead.
     *  If minHeight is greater than the
     *  implementation-defined maximum height, the maximum
     *  height will be used instead.
     * 
     * Parameters:minWidth - the minimum width in pixelsminHeight - the minimum height in pixels
     * Throws:
     * IllegalArgumentException - if either minWidth
     *  or minHeight is less than zero
     */
    public Spacer(int minWidth, int minHeight) {
        construct(minWidth, minHeight);
    }

    private native void construct(int minWidth, int minHeight);

    /**
     * Sets the minimum size for this spacer.  The
     *  Form will not
     *  be allowed to make the item smaller than this size.
     *  The minimum size must be zero or greater.
     *  If minWidth is greater than the
     *  implementation-defined maximum width, the maximum
     *  width will be used instead.
     *  If minHeight is greater than the
     *  implementation-defined maximum height, the maximum
     *  height will be used instead.
     * 
     * Parameters:minWidth - the minimum width in pixelsminHeight - the minimum height in pixels
     * Throws:
     * IllegalArgumentException - if either minWidth
     *  or minHeight is less than zero
     */
    public void setMinimumSize(int minWidth, int minHeight);

    /**
     * Spacers are restricted from having
     *  Commands, so this method will always
     *  throw IllegalStateException whenever it is called.
     * 
     * Overrides:addCommand in class Item
     * 
     * Parameters:cmd - the Command
     * Throws:
     * IllegalStateException - always
     */
    public void addCommand(Command cmd);

    /**
     * Spacers are restricted from having Commands,
     *  so this method will always
     *  throw IllegalStateException whenever it is called.
     * 
     * Overrides:setDefaultCommand in class Item
     * 
     * Parameters:cmd - the Command
     * Throws:
     * IllegalStateException - always
     */
    public void setDefaultCommand(Command cmd);

    /**
     * Spacers are restricted to having
     *  null labels, so this method will
     *  always throw
     *  IllegalStateException whenever it is called.
     * 
     * Overrides:setLabel in class Item
     * 
     * Parameters:label - the label string
     * Throws:
     * IllegalStateException - alwaysSee Also:Item.getLabel()
     */
    public void setLabel(String label);

}
