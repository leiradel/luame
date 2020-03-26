package javax.microedition.lcdui;

public class TextBox extends Screen {
    /**
     * Gets the contents of the TextBox as a string value.
     * 
     * Returns:the current contentsSee Also:setString(java.lang.String)
     */
    public String getString();

    /**
     * Sets the contents of the TextBox as a string
     *  value, replacing the previous contents.
     * 
     * Parameters:text - the new value of the TextBox, or
     *  null if the TextBox is to
     *  be made empty
     * Throws:
     * IllegalArgumentException - if text
     *  is illegal for the current
     *  input constraints
     * IllegalArgumentException - if the text would exceed the current
     *  maximum capacitySee Also:getString()
     */
    public void setString(String text);

    /**
     * Copies the contents of the TextBox into a
     *  character array starting at
     *  index zero. Array elements beyond the characters copied are left
     *  unchanged.
     * 
     * Parameters:data - the character array to receive the value
     * Returns:the number of characters copied
     * Throws:
     * ArrayIndexOutOfBoundsException - if the array is too short for the
     *  contents
     * NullPointerException - if data is nullSee Also:setChars(char[], int, int)
     */
    public int getChars(char[] data);

    /**
     * Sets the contents of the TextBox from a character
     *  array, replacing the
     *  previous contents. Characters are copied from the region of the
     *  data array
     *  starting at array index offset and running for
     *  length characters.
     *  If the data array is null, the TextBox
     *  is set to be empty and the other parameters are ignored.
     * 
     *  The offset and length parameters must
     *  specify a valid range of characters within
     *  the character array data.
     *  The offset parameter must be within the
     *  range [0..(data.length)], inclusive.
     *  The length parameter
     *  must be a non-negative integer such that
     *  (offset + length) &lt;= data.length.
     * 
     * Parameters:data - the source of the character dataoffset - the beginning of the region of characters to copylength - the number of characters to copy
     * Throws:
     * ArrayIndexOutOfBoundsException - if offset
     *  and length do not specify
     *  a valid range within the data array
     * IllegalArgumentException - if data
     *  is illegal for the current
     *  input constraints
     * IllegalArgumentException - if the text would exceed the current
     *  maximum capacitySee Also:getChars(char[])
     */
    public void setChars(char[] data,
                     int offset,
                     int length);

    /**
     * Inserts a string into the contents of the TextBox.
     *  The string is
     *  inserted just prior to the character indicated by the
     *  position parameter, where zero specifies the first
     *  character of the contents of the TextBox.  If
     *  position is
     *  less than or equal to zero, the insertion occurs at the beginning of
     *  the contents, thus effecting a prepend operation.  If
     *  position is greater than or equal to the current size of
     *  the contents, the insertion occurs immediately after the end of the
     *  contents, thus effecting an append operation.  For example,
     *  text.insert(s, text.size()) always appends the string
     *  s to the current contents.
     * 
     *  The current size of the contents is increased by the number of
     *  inserted characters. The resulting string must fit within the current
     *  maximum capacity.
     * 
     *  If the application needs to simulate typing of characters it can
     *  determining the location of the current insertion point
     *  (&quot;caret&quot;)
     *  using the with getCaretPosition() method.
     *  For example,
     *  text.insert(s, text.getCaretPosition()) inserts the string
     *  s at the current caret position.
     * 
     * Parameters:src - the String to be insertedposition - the position at which insertion is to occur
     * Throws:
     * IllegalArgumentException - if the resulting contents
     *  would be illegal for the current
     *  input constraints
     * IllegalArgumentException - if the insertion would
     *  exceed the current
     *  maximum capacity
     * NullPointerException - if src is null
     */
    public void insert(String src,
                   int position);

    /**
     * Inserts a subrange of an array of characters into the contents of
     *  the TextBox.  The offset and
     *  length parameters indicate the subrange of
     *  the data array to be used for insertion. Behavior is otherwise
     *  identical to insert(String, int).
     * 
     *  The offset and length parameters must
     *  specify a valid range of characters within
     *  the character array data.
     *  The offset parameter must be within the
     *  range [0..(data.length)], inclusive.
     *  The length parameter
     *  must be a non-negative integer such that
     *  (offset + length) &lt;= data.length.
     * 
     * Parameters:data - the source of the character dataoffset - the beginning of the region of characters to copylength - the number of characters to copyposition - the position at which insertion is to occur
     * Throws:
     * ArrayIndexOutOfBoundsException - if offset
     *  and length do not
     *  specify a valid range within the data array
     * IllegalArgumentException - if the resulting contents
     *  would be illegal for the current
     *  input constraints
     * IllegalArgumentException - if the insertion would
     *  exceed the current
     *  maximum capacity
     * NullPointerException - if data is null
     */
    public void insert(char[] data,
                   int offset,
                   int length,
                   int position);

    /**
     * Deletes characters from the TextBox.
     * 
     *  The offset and length parameters must
     *  specify a valid range of characters within
     *  the contents of the TextBox.
     *  The offset parameter must be within the
     *  range [0..(size())], inclusive.
     *  The length parameter
     *  must be a non-negative integer such that
     *  (offset + length) &lt;= size().
     * 
     * Parameters:offset - the beginning of the region to be deletedlength - the number of characters to be deleted
     * Throws:
     * IllegalArgumentException - if the resulting contents
     *  would be illegal for the current
     *  input constraints
     * StringIndexOutOfBoundsException - if offset
     *  and length do not
     *  specify a valid range within the contents of the TextBox
     */
    public void delete(int offset,
                   int length);

    /**
     * Returns the maximum size (number of characters) that can be
     *  stored in this TextBox.
     * 
     * Returns:the maximum size in charactersSee Also:setMaxSize(int)
     */
    public int getMaxSize();

    /**
     * Sets the maximum size (number of characters) that can be
     *  contained in this
     *  TextBox. If the current contents of the
     *  TextBox are larger than
     *  maxSize, the contents are truncated to fit.
     * 
     * Parameters:maxSize - the new maximum size
     * Returns:assigned maximum capacity - may be smaller than requested.
     * Throws:
     * IllegalArgumentException - if maxSize is zero or less.
     * IllegalArgumentException - if the contents
     *  after truncation would be illegal for the current
     *  input constraintsSee Also:getMaxSize()
     */
    public int setMaxSize(int maxSize);

    /**
     * Gets the number of characters that are currently stored in this
     *  TextBox.
     * 
     * Returns:the number of characters
     */
    public int size();

    /**
     * Gets the current input position.  For some UIs this may block and ask
     *  the user for the intended caret position, and on other UIs this may
     *  simply return the current caret position.
     * 
     * Returns:the current caret position, 0 if at the beginning
     */
    public int getCaretPosition();

    /**
     * Sets the input constraints of the TextBox. If the
     *  current contents
     *  of the TextBox do not match the new constraints,
     *  the contents are
     *  set to empty.
     * 
     * Parameters:constraints - see
     *  input constraints
     * Throws:
     * IllegalArgumentException - if the value of the constraints
     *  parameter is invalidSee Also:getConstraints()
     */
    public void setConstraints(int constraints);

    /**
     * Gets the current input constraints of the TextBox.
     * 
     * Returns:the current constraints value (see
     *  input constraints)See Also:setConstraints(int)
     */
    public int getConstraints();

    /**
     * Sets a hint to the implementation as to the input mode that should be
     *  used when the user initiates editing of this
     *  TextBox. The
     *  characterSubset parameter names a subset of Unicode
     *  characters that is used by the implementation to choose an initial
     *  input mode.  If null is passed, the implementation should
     *  choose a default input mode.
     * 
     *  See Input Modes for a full
     *  explanation of input modes.
     * 
     * Parameters:characterSubset - a string naming a Unicode character subset,
     *  or nullSince:
     *   MIDP 2.0
     */
    public void setInitialInputMode(String characterSubset);

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

}
