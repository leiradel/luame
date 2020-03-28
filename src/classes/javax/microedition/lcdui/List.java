package javax.microedition.lcdui;

public class List {
    /**
     * The default select command for IMPLICIT Lists.
     *  Applications using an IMPLICIT List
     *  should set their own select command
     *  using
     *  setSelectCommand.
     * 
     *  The field values of SELECT_COMMAND are:
     *  - label = &quot;&quot; (an empty string)
     *  - type = SCREEN
     *  - priority = 0
     * 
     *  (It would be more appropriate if the type were
     *  ITEM, but the type of
     *  SCREEN is retained for historical purposes.)
     * 
     *  The application should not use these values for recognizing
     *  the SELECT_COMMAND. Instead, object identities of
     *  the Command and
     *  Displayable (List) should be used.
     * 
     *  SELECT_COMMAND is treated as an ordinary
     *  Command if it is used with other Displayable
     *  types.
     */
    public static final Command SELECT_COMMAND = ;

    /**
     * Creates a new, empty List, specifying its title
     *  and the type of the
     *  list.
     * 
     * Parameters:title - the screen's title (see Displayable)listType - one of IMPLICIT, EXCLUSIVE,
     *  or MULTIPLE
     * Throws:
     * IllegalArgumentException - if listType is not
     *  one of
     *  IMPLICIT,
     *  EXCLUSIVE, or MULTIPLESee Also:Choice
     */
    public List(String title, int listType) {
        construct(title, listType);
    }

    private native void construct(String title, int listType);

    /**
     * Creates a new List, specifying its title, the type
     *  of the List, and
     *  an array of Strings and Images to be
     *  used as its initial contents.
     * 
     *  The stringElements array must be non-null and
     *  every array element
     *  must also be non-null.  The length of the
     *  stringElements array
     *  determines the number of elements in the List.
     *  The imageElements array
     *  may be null to indicate that the List
     *  elements have no images.  If the
     *  imageElements array is non-null, it must be the
     *  same length as the
     *  stringElements array.  Individual elements of the
     *  imageElements array
     *  may be null in order to indicate the absence of an
     *  image for the
     *  corresponding List element. Non-null elements of the
     *  imageElements array may refer to mutable or
     *  immutable images.
     * 
     * Parameters:title - the screen's title (see Displayable)listType - one of IMPLICIT, EXCLUSIVE,
     *  or MULTIPLEstringElements - set of strings specifying the string parts of the
     *  List elementsimageElements - set of images specifying the image parts of
     *  the List elements
     * Throws:
     * NullPointerException - if stringElements is
     *  null
     * NullPointerException - if the stringElements
     *  array contains any null elements
     * IllegalArgumentException - if the imageElements
     *  array is non-null
     *  and has a different length from the stringElements array
     * IllegalArgumentException - if listType is not one
     *  of IMPLICIT,
     *  EXCLUSIVE, or MULTIPLESee Also:Choice.EXCLUSIVE,
     * Choice.MULTIPLE,
     * Choice.IMPLICIT
     */
    public List(String title, int listType, String[] stringElements, Image[] imageElements) {
        construct(title, listType, stringElements, imageElements);
    }

    private native void construct(String title, int listType, String[] stringElements, Image[] imageElements);

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
     *  If the application adds, removes, or sets the ticker text at runtime,
     *  this can dynamically change the size of the content area.
     *  This is most important to be aware of when using the
     *  Canvas class.
     * 
     * Overrides:setTicker in class Displayable
     * 
     * Parameters:ticker - the ticker object used on this screenSince:
     *   MIDP 2.0
     * See Also:Displayable.getTicker()
     */
    public void setTicker(Ticker ticker);

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
     *  If the application adds, removes, or sets the title text at runtime,
     *  this can dynamically change the size of the content area.
     *  This is most important to be aware of when using the
     *  Canvas class.
     * 
     * Overrides:setTitle in class Displayable
     * 
     * Parameters:s - the new title, or null for no titleSince:
     *   MIDP 2.0
     * See Also:Displayable.getTitle()
     */
    public void setTitle(String s);

    /**
     * Gets the number of elements in the List.
     * 
     * Specified by:size in interface Choice
     * 
     * Returns:the number of elements in the List
     */
    public int size();

    /**
     * Gets the String part of the element referenced by
     *  elementNum.
     * 
     * Specified by:getString in interface Choice
     * 
     * Parameters:elementNum - the index of the element to be queried
     * Returns:the string part of the element
     * Throws:
     * IndexOutOfBoundsException - if elementNum is invalidSee Also:getImage(int)
     */
    public String getString(int elementNum);

    /**
     * Gets the Image part of the element referenced by
     *  elementNum.
     * 
     * Specified by:getImage in interface Choice
     * 
     * Parameters:elementNum - the number of the element to be queried
     * Returns:the image part of the element, or null
     *  if there is no image
     * Throws:
     * IndexOutOfBoundsException - if elementNum is invalidSee Also:getString(int)
     */
    public Image getImage(int elementNum);

    /**
     * Appends an element to the List.
     * 
     * Specified by:append in interface Choice
     * 
     * Parameters:stringPart - the string part of the element to be addedimagePart - the image part of the element to be added, or
     *  null if there is no image part
     * Returns:the assigned index of the element
     * Throws:
     * NullPointerException - if stringPart is
     *  null
     */
    public int append(String stringPart, Image imagePart);

    /**
     * Inserts an element into the List just prior to
     *  the element specified.
     * 
     * Specified by:insert in interface Choice
     * 
     * Parameters:elementNum - the index of the element where insertion is to occurstringPart - the string part of the element to be insertedimagePart - the image part of the element to be inserted,
     *  or null if there is no image part
     * Throws:
     * IndexOutOfBoundsException - if elementNum is invalid
     * NullPointerException - if stringPart is
     *  null
     */
    public void insert(int elementNum, String stringPart, Image imagePart);

    /**
     * Deletes the element referenced by elementNum.
     * 
     * Specified by:delete in interface Choice
     * 
     * Parameters:elementNum - the index of the element to be deleted
     * Throws:
     * IndexOutOfBoundsException - if elementNum is invalid
     */
    public void delete(int elementNum);

    /**
     * Deletes all elements from this List.
     * 
     * Specified by:deleteAll in interface Choice
     */
    public void deleteAll();

    /**
     * Sets the String and Image parts of the
     *  element referenced by elementNum,
     *  replacing the previous contents of the element.
     * 
     * Specified by:set in interface Choice
     * 
     * Parameters:elementNum - the index of the element to be setstringPart - the string part of the new elementimagePart - the image part of the element, or null
     *  if there is no image part
     * Throws:
     * IndexOutOfBoundsException - if elementNum is invalid
     * NullPointerException - if stringPart is
     *  null
     */
    public void set(int elementNum, String stringPart, Image imagePart);

    /**
     * Gets a boolean value indicating whether this element is selected.
     * 
     * Specified by:isSelected in interface Choice
     * 
     * Parameters:elementNum - index to element to be queried
     * Returns:selection state of the element
     * Throws:
     * IndexOutOfBoundsException - if elementNum is invalid
     */
    public boolean isSelected(int elementNum);

    /**
     * Returns the index number of an element in the List
     *  that is selected.
     * 
     * Specified by:getSelectedIndex in interface Choice
     * 
     * Returns:index of selected element, or -1 if noneSee Also:setSelectedIndex(int, boolean)
     */
    public int getSelectedIndex();

    /**
     * Queries the state of a List and returns the
     *  state of all elements
     *  in the
     *  boolean array
     *  selectedArray_return.
     * 
     * Specified by:getSelectedFlags in interface Choice
     * 
     * Parameters:selectedArray_return - array to contain the results
     * Returns:the number of selected elements in the Choice
     * Throws:
     * IllegalArgumentException - if selectedArray_return
     *  is shorter than the size of the List
     * NullPointerException - if selectedArray_return
     *  is nullSee Also:setSelectedFlags(boolean[])
     */
    public int getSelectedFlags(boolean[] selectedArray_return);

    /**
     * Sets the selected state of an element.
     * 
     * Specified by:setSelectedIndex in interface Choice
     * 
     * Parameters:elementNum - the index of the element, starting from zeroselected - the state of the element, where true means
     *  selected and false means not selected
     * Throws:
     * IndexOutOfBoundsException - if elementNum is invalidSee Also:getSelectedIndex()
     */
    public void setSelectedIndex(int elementNum, boolean selected);

    /**
     * Sets the selected state of all elements of the List.
     * 
     * Specified by:setSelectedFlags in interface Choice
     * 
     * Parameters:selectedArray - an array in which the method collect
     *  the selection status
     * Throws:
     * IllegalArgumentException - if selectedArray is
     *  shorter than the size of the List
     * NullPointerException - if selectedArray is
     *  nullSee Also:getSelectedFlags(boolean[])
     */
    public void setSelectedFlags(boolean[] selectedArray);

    /**
     * The same as Displayable.removeCommand
     *  but with the following additional semantics.
     * 
     *  If the command to be removed happens to be the select command, the
     *  List is set to have no select command, and the command is
     *  removed from the List.
     * 
     *  The following code:
     * 
     *      // Command c is the select command on List list
     *      list.removeCommand(c);
     * 
     *  is equivalent to the following code:
     * 
     *      // Command c is the select command on List list
     *      list.setSelectCommand(null);
     *      list.removeCommand(c);
     * 
     * Overrides:removeCommand in class Displayable
     * 
     * Parameters:cmd - the command to be removedSince:
     *   MIDP 2.0
     */
    public void removeCommand(Command cmd);

    /**
     * Sets the Command to be used for an
     *  IMPLICIT List selection
     *  action.
     *  By default, an implicit selection of a List will result in the
     *  predefined List.SELECT_COMMAND being used. This
     *  behavior may be
     *  overridden by calling the List.setSelectCommand()
     *  method with an
     *  appropriate parameter value.  If a null reference
     *  is passed, this
     *  indicates that no &quot;select&quot; action is appropriate for
     *  the contents
     *  of this List.
     * 
     *   If a reference to a command object is passed, and
     *  it is not the special command List.SELECT_COMMAND, and
     *  it is not currently present on this List object,
     *  the command object is added to this List as if
     *  addCommand(command) had been called
     *  prior to the command being made the select command.  This
     *  indicates that this command
     *  is to be invoked when the user performs the &quot;select&quot;
     *  on an element of
     *  this List.
     * 
     *   The select command should have a command type of
     *  ITEM to indicate
     *  that it operates on the currently selected object.  It is not an error
     *  if the command is of some other type.
     *  (List.SELECT_COMMAND has a type
     *  of SCREEN for historical purposes.)  For purposes
     *  of presentation and
     *  placement within its user interface, the implementation is allowed to
     *  treat the select command as if it were of type ITEM.
     * 
     *   If the select command is later removed from the List
     *  with removeCommand(), the List is set to have
     *  no select command as if List.setSelectCommand(null) had
     *  been called.
     * 
     *   The default behavior can be reestablished explicitly by calling
     *  setSelectCommand() with an argument of
     *  List.SELECT_COMMAND.
     * 
     *   This method has no effect if the type of the
     *  List is not IMPLICIT.
     * 
     * Parameters:command - the command to be used for an IMPLICIT list
     *  selection action, or null if there is noneSince:
     *   MIDP 2.0
     */
    public void setSelectCommand(Command command);

    /**
     * Sets the application's preferred policy for fitting
     *  Choice element
     *  contents to the available screen space. The set policy applies for all
     *  elements of the Choice object.  Valid values are
     *  Choice.TEXT_WRAP_DEFAULT, Choice.TEXT_WRAP_ON,
     *  and Choice.TEXT_WRAP_OFF. Fit policy is a hint, and the
     *  implementation may disregard the application's preferred policy.
     * 
     * Specified by:setFitPolicy in interface Choice
     * 
     * Parameters:fitPolicy - preferred content fit policy for choice elements
     * Throws:
     * IllegalArgumentException - if fitPolicy is invalidSince:
     *   MIDP 2.0
     * See Also:getFitPolicy()
     */
    public void setFitPolicy(int fitPolicy);

    /**
     * Gets the application's preferred policy for fitting
     *  Choice element
     *  contents to the available screen space.  The value returned is the
     *  policy that had been set by the application, even if that value had
     *  been disregarded by the implementation.
     * 
     * Specified by:getFitPolicy in interface Choice
     * 
     * Returns:one of Choice.TEXT_WRAP_DEFAULT, Choice.TEXT_WRAP_ON, or
     *  Choice.TEXT_WRAP_OFFSince:
     *   MIDP 2.0
     * See Also:setFitPolicy(int)
     */
    public int getFitPolicy();

    /**
     * Sets the application's preferred font for
     *  rendering the specified element of this Choice.
     *  An element's font is a hint, and the implementation may disregard
     *  the application's preferred font.
     * 
     *   The elementNum parameter must be within the range
     *  [0..size()-1], inclusive.
     * 
     *   The font parameter must be a valid Font
     *  object or null. If the font parameter is
     *  null, the implementation must use its default font
     *  to render the element.
     * 
     * Specified by:setFont in interface Choice
     * 
     * Parameters:elementNum - the index of the element, starting from zerofont - the preferred font to use to render the element
     * Throws:
     * IndexOutOfBoundsException - if elementNum is invalidSince:
     *   MIDP 2.0
     * See Also:getFont(int)
     */
    public void setFont(int elementNum, Font font);

    /**
     * Gets the application's preferred font for
     *  rendering the specified element of this Choice. The
     *  value returned is the font that had been set by the application,
     *  even if that value had been disregarded by the implementation.
     *  If no font had been set by the application, or if the application
     *  explicitly set the font to null, the value is the default
     *  font chosen by the implementation.
     * 
     *   The elementNum parameter must be within the range
     *  [0..size()-1], inclusive.
     * 
     * Specified by:getFont in interface Choice
     * 
     * Parameters:elementNum - the index of the element, starting from zero
     * Returns:the preferred font to use to render the element
     * Throws:
     * IndexOutOfBoundsException - if elementNum is invalidSince:
     *   MIDP 2.0
     * See Also:setFont(int elementNum, Font font)
     */
    public Font getFont(int elementNum);

}
