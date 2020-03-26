package javax.microedition.lcdui;

public class ChoiceGroup {
    /**
     * Creates a new, empty ChoiceGroup, specifying its
     *  title and its type.
     *  The type must be one of EXCLUSIVE,
     *  MULTIPLE, or POPUP. The
     *  IMPLICIT
     *  choice type is not allowed within a ChoiceGroup.
     * 
     * Parameters:label - the item's label (see Item)choiceType - EXCLUSIVE, MULTIPLE,
     *  or POPUP
     * Throws:
     * IllegalArgumentException - if choiceType is not one of
     *  EXCLUSIVE, MULTIPLE, or POPUPSee Also:Choice.EXCLUSIVE,
     * Choice.MULTIPLE,
     * Choice.IMPLICIT,
     * Choice.POPUP
     */
    public ChoiceGroup(String label,
                   int choiceType) {
        construct(label, choiceType);
    }

    private native void construct(String label,
                   int choiceType);

    /**
     * Returns the number of elements in the ChoiceGroup.
     * 
     * Specified by:size in interface Choice
     * 
     * Returns:the number of elements in the ChoiceGroup
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
     * Returns:the image part of the element, or null if there is no image
     * Throws:
     * IndexOutOfBoundsException - if elementNum is invalidSee Also:getString(int)
     */
    public Image getImage(int elementNum);

    /**
     * Appends an element to the ChoiceGroup.
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
    public int append(String stringPart,
                  Image imagePart);

    /**
     * Inserts an element into the ChoiceGroup just prior to
     *  the element specified.
     * 
     * Specified by:insert in interface Choice
     * 
     * Parameters:elementNum - the index of the element where insertion is to occurstringPart - the string part of the element to be insertedimagePart - the image part of the element to be inserted,
     *  or null if there is no image part
     * Throws:
     * IndexOutOfBoundsException - if elementNum is invalid
     * NullPointerException - if stringPart
     *  is null
     */
    public void insert(int elementNum,
                   String stringPart,
                   Image imagePart);

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
     * Deletes all elements from this ChoiceGroup.
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
    public void set(int elementNum,
                String stringPart,
                Image imagePart);

    /**
     * Gets a boolean value indicating whether this element is selected.
     * 
     * Specified by:isSelected in interface Choice
     * 
     * Parameters:elementNum - the index of the element to be queried
     * Returns:selection state of the element
     * Throws:
     * IndexOutOfBoundsException - if elementNum is invalid
     */
    public boolean isSelected(int elementNum);

    /**
     * Returns the index number of an element in the
     *  ChoiceGroup that is
     *  selected. For ChoiceGroup objects of type
     *  EXCLUSIVE and POPUP
     *  there is at most one element selected, so
     *  this method is useful for determining the user's choice.
     *  Returns -1 if
     *  there are no elements in the ChoiceGroup.
     * 
     *  For ChoiceGroup objects of type
     *  MULTIPLE, this always
     *  returns -1 because no
     *  single value can in general represent the state of such a
     *  ChoiceGroup.
     *  To get the complete state of a MULTIPLE
     *  Choice, see getSelectedFlags.
     * 
     * Specified by:getSelectedIndex in interface Choice
     * 
     * Returns:index of selected element, or -1 if noneSee Also:setSelectedIndex(int, boolean)
     */
    public int getSelectedIndex();

    /**
     * Queries the state of a ChoiceGroup and returns the state of
     *  all elements in the
     *  boolean array
     *  selectedArray_return. Note: this
     *  is a result parameter.
     *  It must be at least as long as the size
     *  of the ChoiceGroup as returned by size().
     *  If the array is longer, the extra
     *  elements are set to false.
     * 
     *  For ChoiceGroup objects of type
     *  MULTIPLE, any
     *  number of elements may be selected and set to true in the result
     *  array.  For ChoiceGroup objects of type
     *  EXCLUSIVE and POPUP
     *  exactly one element will be selected, unless there are
     *  zero elements in the ChoiceGroup.
     * 
     * Specified by:getSelectedFlags in interface Choice
     * 
     * Parameters:selectedArray_return - array to contain the results
     * Returns:the number of selected elements in the ChoiceGroup
     * Throws:
     * IllegalArgumentException - if selectedArray_return
     *  is shorter than the size of the ChoiceGroup
     * NullPointerException - if selectedArray_return
     *  is nullSee Also:setSelectedFlags(boolean[])
     */
    public int getSelectedFlags(boolean[] selectedArray_return);

    /**
     * For ChoiceGroup objects of type
     *  MULTIPLE, this simply sets an
     *  individual element's selected state.
     * 
     *  For ChoiceGroup objects of type
     *  EXCLUSIVE and POPUP, this can be used only to
     *  select an element.  That is, the  selected  parameter must
     *  be  true . When an element is selected, the previously
     *  selected element is deselected. If  selected  is
     *  false , this call is ignored.
     * 
     *  For both list types, the elementNum parameter
     *  must be within
     *  the range
     *  [0..size()-1], inclusive.
     * 
     * Specified by:setSelectedIndex in interface Choice
     * 
     * Parameters:elementNum - the number of the element. Indexing of the
     *  elements is zero-basedselected - the new state of the element true=selected,
     *  false=not selected
     * Throws:
     * IndexOutOfBoundsException - if elementNum is invalidSee Also:getSelectedIndex()
     */
    public void setSelectedIndex(int elementNum,
                             boolean selected);

    /**
     * Attempts to set the selected state of every element in the
     *  ChoiceGroup. The array
     *  must be at least as long as the size of the
     *  ChoiceGroup. If the array is
     *  longer, the additional values are ignored.
     * 
     *  For ChoiceGroup objects of type
     *  MULTIPLE, this sets the selected
     *  state of every
     *  element in the Choice. An arbitrary number of
     *  elements may be selected.
     * 
     *  For ChoiceGroup objects of type
     *  EXCLUSIVE and POPUP, exactly one array
     *  element must have the value true. If no element is
     *  true,
     *  the first element
     *  in the Choice will be selected. If two or more
     *  elements are true, the
     *  implementation will choose the first true element
     *  and select it.
     * 
     * Specified by:setSelectedFlags in interface Choice
     * 
     * Parameters:selectedArray - an array in which the method collect the
     *  selection status
     * Throws:
     * IllegalArgumentException - if selectedArray
     *  is shorter than the size of the ChoiceGroup
     * NullPointerException - if the selectedArray
     *  is nullSee Also:getSelectedFlags(boolean[])
     */
    public void setSelectedFlags(boolean[] selectedArray);

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
    public void setFont(int elementNum,
                    Font font);

}
