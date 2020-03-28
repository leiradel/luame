package javax.microedition.lcdui;

public class Form extends Screen {
    /**
     * Creates a new, empty Form.
     * 
     * Parameters:title - the Form's title, or
     *  null for no title
     */
    public Form(String title) {
        construct(title);
    }

    private native void construct(String title);

    /**
     * Creates a new Form with the specified
     *  contents. This is identical to
     *  creating an empty Form and then using a set of
     *  append
     *  methods.  The
     *  items array may be null, in which case the
     *  Form is created empty.  If
     *  the items array is non-null, each element must be a valid
     *  Item not
     *  already contained within another Form.
     * 
     * Parameters:title - the Form's title stringitems - the array of items to be placed in the
     *  Form, or null if there are no
     *  items
     * Throws:
     * IllegalStateException - if one of the items is already owned by
     *  another container
     * NullPointerException - if an element of the items array is
     *  null
     */
    public Form(String title, Item[] items) {
        construct(title, items);
    }

    private native void construct(String title, Item[] items);

    /**
     * Adds an Item into the Form.  The newly
     *  added Item becomes the last Item in the
     *  Form, and the size of the Form grows
     *  by one.
     * 
     * Parameters:item - the Item to be added.
     * Returns:the assigned index of the Item
     * Throws:
     * IllegalStateException - if the item is already owned by
     *  a container
     * NullPointerException - if item is null
     */
    public int append(Item item);

    /**
     * Adds an item consisting of one String to the
     *  Form. The effect of
     *  this method is identical to
     * 
     *  append(new StringItem(null, str))
     * 
     * Parameters:str - the String to be added
     * Returns:the assigned index of the Item
     * Throws:
     * NullPointerException - if str is null
     */
    public int append(String str);

    /**
     * Adds an item consisting of one Image to the
     *  Form. The effect of
     *  this method is identical to
     * 
     *  append(new ImageItem(null, img, ImageItem.LAYOUT_DEFAULT, null))
     * 
     * Parameters:img - the image to be added
     * Returns:the assigned index of the Item
     * Throws:
     * NullPointerException - if img is null
     */
    public int append(Image img);

    /**
     * Inserts an item into the Form just prior to
     *  the item specified.
     *  The size of the Form grows by one.  The
     *  itemNum parameter must be
     *  within the range [0..size()], inclusive.
     *  The index of the last item is size()-1, and
     *  so there is actually no item whose index is
     *  size(). If this value
     *  is used for itemNum, the new item is inserted
     *  immediately after
     *  the last item. In this case, the effect is identical to
     *  append(Item).
     * 
     *   The semantics are otherwise identical to
     *  append(Item).
     * 
     * Parameters:itemNum - the index where insertion is to occuritem - the item to be inserted
     * Throws:
     * IndexOutOfBoundsException - if itemNum is invalid
     * IllegalStateException - if the item is already owned by
     *  a container
     * NullPointerException - if item is
     *  null
     */
    public void insert(int itemNum, Item item);

    /**
     * Deletes the Item referenced by
     *  itemNum. The size of the Form
     *  shrinks by one. It is legal to delete all items from a
     *  Form.
     *  The itemNum parameter must be
     *  within the range [0..size()-1], inclusive.
     * 
     * Parameters:itemNum - the index of the item to be deleted
     * Throws:
     * IndexOutOfBoundsException - if itemNum is invalid
     */
    public void delete(int itemNum);

    /**
     * Deletes all the items from this Form, leaving
     *  it with zero items.
     *  This method does nothing if the Form is already empty.
     * 
     * Since:
     *   MIDP 2.0
     */
    public void deleteAll();

    /**
     * Sets the item referenced by itemNum to the
     *  specified item,
     *  replacing the previous item. The previous item is removed
     *  from this Form.
     *  The itemNum parameter must be
     *  within the range [0..size()-1], inclusive.
     * 
     *  The end result is equal to
     *  insert(n, item); delete(n+1);
     *  although the implementation may optimize the repainting
     *  and usage of the array that stores the items.
     * 
     * Parameters:itemNum - the index of the item to be replaceditem - the new item to be placed in the Form
     * Throws:
     * IndexOutOfBoundsException - if itemNum is invalid
     * IllegalStateException - if the item is already owned by
     *  a container
     * NullPointerException - if item is
     *  null
     */
    public void set(int itemNum, Item item);

    /**
     * Gets the item at given position.  The contents of the
     *  Form are left
     *  unchanged.
     *  The itemNum parameter must be
     *  within the range [0..size()-1], inclusive.
     * 
     * Parameters:itemNum - the index of item
     * Returns:the item at the given position
     * Throws:
     * IndexOutOfBoundsException - if itemNum is invalid
     */
    public Item get(int itemNum);

    /**
     * Sets the ItemStateListener for the
     *  Form, replacing any previous
     *  ItemStateListener. If
     *  iListener is null, simply
     *  removes the previous ItemStateListener.
     * 
     * Parameters:iListener - the new listener, or null to remove it
     */
    public void setItemStateListener(ItemStateListener iListener);

    /**
     * Gets the number of items in the Form.
     * 
     * Returns:the number of items
     */
    public int size();

    /**
     * Returns the width in pixels of the displayable area available for items.
     *  The value may depend on how the device uses the screen and may be
     *  affected by the presence or absence of the ticker, title, or commands.
     *  The Items of the Form are
     *  laid out to fit within this width.
     * 
     * Overrides:getWidth in class Displayable
     * 
     * Returns:the width of the Form in pixelsSince:
     *   MIDP 2.0
     */
    public int getWidth();

    /**
     * Returns the height in pixels of the displayable area available
     *  for items.
     *  This value is the height of the form that can be displayed without
     *  scrolling.
     *  The value may depend on how the device uses the screen and may be
     *  affected by the presence or absence of the ticker, title, or commands.
     * 
     * Overrides:getHeight in class Displayable
     * 
     * Returns:the height of the displayable area of the
     *  Form in pixelsSince:
     *   MIDP 2.0
     */
    public int getHeight();

}
