package javax.microedition.lcdui;

public class Spacer extends Item {
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
    public void setMinimumSize(int minWidth,
                           int minHeight);

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

}
