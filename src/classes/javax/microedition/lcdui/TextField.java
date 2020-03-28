package javax.microedition.lcdui;

public class TextField extends Item {
    /**
     * The user is allowed to enter any text.
     *  Line breaks may be entered.
     * 
     *  Constant 0 is assigned to ANY.
     * 
     * See Also:Constant Field Values
     */
    public static final int ANY = ;

    /**
     * The user is allowed to enter an e-mail address.
     * 
     *  Constant 1 is assigned to EMAILADDR.
     * 
     * See Also:Constant Field Values
     */
    public static final int EMAILADDR = ;

    /**
     * The user is allowed to enter only an integer value. The implementation
     *  must restrict the contents either to be empty or to consist of an
     *  optional minus sign followed by a string of one or more decimal
     *  numerals.  Unless the value is empty, it will be successfully parsable
     *  using Integer.parseInt(String).
     * 
     *  The minus sign consumes space in the text object.  It is thus
     *  impossible to enter negative numbers into a text object whose maximum
     *  size is 1.
     * 
     *  Constant 2 is assigned to NUMERIC.
     * 
     * See Also:Constant Field Values
     */
    public static final int NUMERIC = 2;

    /**
     * The user is allowed to enter a phone number. The phone number is a
     *  special
     *  case, since a phone-based implementation may be linked to the
     *  native phone
     *  dialing application. The implementation may automatically start a phone
     *  dialer application that is initialized so that pressing a single key
     *  would be enough to make a call. The call must not made automatically
     *  without requiring user's confirmation.  Implementations may also
     *  provide a feature to look up the phone number in the device's phone or
     *  address database.
     * 
     *  The exact set of characters allowed is specific to the device and to
     *  the device's network and may include non-numeric characters, such as a
     *  &quot;+&quot; prefix character.
     * 
     *  Some platforms may provide the capability to initiate voice calls
     *  using the MIDlet.platformRequest method.
     * 
     *  Constant 3 is assigned to PHONENUMBER.
     * 
     * See Also:Constant Field Values
     */
    public static final int PHONENUMBER = ;

    /**
     * The user is allowed to enter a URL.
     * 
     *  Constant 4 is assigned to URL.
     * 
     * See Also:Constant Field Values
     */
    public static final int URL = ;

    /**
     * The user is allowed to enter numeric values with optional decimal
     *  fractions, for example &quot;-123&quot;, &quot;0.123&quot;, or
     *  &quot;.5&quot;.
     * 
     *  The implementation may display a period &quot;.&quot; or a
     *  comma &quot;,&quot; for the decimal fraction separator, depending on
     *  the conventions in use on the device.  Similarly, the implementation
     *  may display other device-specific characters as part of a decimal
     *  string, such as spaces or commas for digit separators.  However, the
     *  only characters allowed in the actual contents of the text object are
     *  period &quot;.&quot;, minus sign &quot;-&quot;, and the decimal
     *  digits.
     * 
     *  The actual contents of a DECIMAL text object may be
     *  empty.  If the actual contents are not empty, they must conform to a
     *  subset of the syntax for a FloatingPointLiteral as defined
     *  by the Java Language Specification, section 3.10.2.  This
     *  subset syntax is defined as follows: the actual contents
     *  must consist of an optional minus sign
     *  &quot;-&quot;, followed by one or more whole-number decimal digits,
     *  followed by an optional fraction separator, followed by zero or more
     *  decimal fraction digits.  The whole-number decimal digits may be
     *  omitted if the fraction separator and one or more decimal fraction
     *  digits are present.
     * 
     *  The syntax defined above is also enforced whenever the application
     *  attempts to set or modify the contents of the text object by calling
     *  a constructor or a method.
     * 
     *  Parsing this string value into a numeric value suitable for
     *  computation is the responsibility of the application.  If the contents
     *  are not empty, the result can be parsed successfully by
     *  Double.valueOf and related methods if they are present
     *  in the runtime environment.
     * 
     *  The sign and the fraction separator consume space in the text
     *  object.  Applications should account for this when assigning a maximum
     *  size for the text object.
     * 
     *  Constant 5 is assigned to DECIMAL.
     * 
     * Since:
     *   MIDP 2.0
     * See Also:Constant Field Values
     */
    public static final int DECIMAL = 5;

    /**
     * Indicates that the text entered is confidential data that should be
     *  obscured whenever possible.  The contents may be visible while the
     *  user is entering data.  However, the contents must never be divulged
     *  to the user.  In particular, the existing contents must not be shown
     *  when the user edits the contents.  The means by which the contents
     *  are obscured is implementation-dependent.  For example, each
     *  character of the data might be masked with a
     *  &quot;*&quot; character.  The
     *  PASSWORD modifier is useful for entering
     *  confidential information
     *  such as passwords or personal identification numbers (PINs).
     * 
     *  Data entered into a PASSWORD field is treated
     *  similarly to SENSITIVE
     *  in that the implementation must never store the contents into a
     *  dictionary or table for use in predictive, auto-completing, or other
     *  accelerated input schemes.  If the PASSWORD bit is
     *  set in a constraint
     *  value, the SENSITIVE and
     *  NON_PREDICTIVE bits are also considered to be
     *  set, regardless of their actual values.  In addition, the
     *  INITIAL_CAPS_WORD and
     *  INITIAL_CAPS_SENTENCE flag bits should be ignored
     *  even if they are set.
     * 
     *  The PASSWORD modifier can be combined with
     *  other input constraints
     *  by using the bit-wise OR operator (|).
     *  The PASSWORD modifier is not
     *  useful with some constraint values such as
     *  EMAILADDR, PHONENUMBER,
     *  and URL. These combinations are legal, however,
     *  and no exception is
     *  thrown if such a constraint is specified.
     * 
     *  Constant 0x10000 is assigned to
     *  PASSWORD.
     * 
     * See Also:Constant Field Values
     */
    public static final int PASSWORD = ;

    /**
     * Indicates that editing is currently disallowed.  When this flag is set,
     *  the implementation must prevent the user from changing the text
     *  contents of this object.  The implementation should also provide a
     *  visual indication that the object's text cannot be edited.  The intent
     *  of this flag is that this text object has the potential to be edited,
     *  and that there are circumstances where the application will clear this
     *  flag and allow the user to edit the contents.
     * 
     *  The UNEDITABLE modifier can be combined with
     *  other input constraints
     *  by using the bit-wise OR operator (|).
     * 
     *  Constant 0x20000 is assigned to UNEDITABLE.
     * 
     * Since:
     *   MIDP 2.0
     * See Also:Constant Field Values
     */
    public static final int UNEDITABLE = ;

    /**
     * Indicates that the text entered is sensitive data that the
     *  implementation must never store into a dictionary or table for use in
     *  predictive, auto-completing, or other accelerated input schemes.  A
     *  credit card number is an example of sensitive data.
     * 
     *  The SENSITIVE modifier can be combined with other input
     *  constraints by using the bit-wise OR operator
     *  (|).
     * 
     *  Constant 0x40000 is assigned to
     *  SENSITIVE.
     * 
     * Since:
     *   MIDP 2.0
     * See Also:Constant Field Values
     */
    public static final int SENSITIVE = ;

    /**
     * Indicates that the text entered does not consist of words that are
     *  likely to be found in dictionaries typically used by predictive input
     *  schemes.  If this bit is clear, the implementation is allowed to (but
     *  is not required to) use predictive input facilities.  If this bit is
     *  set, the implementation should not use any predictive input facilities,
     *  but it instead should allow character-by-character text entry.
     * 
     *  The NON_PREDICTIVE modifier can be combined
     *  with other input
     *  constraints by using the bit-wise OR operator
     *  (|).
     * 
     *  Constant 0x80000 is assigned to
     *  NON_PREDICTIVE.
     * 
     * Since:
     *   MIDP 2.0
     * See Also:Constant Field Values
     */
    public static final int NON_PREDICTIVE = ;

    /**
     * This flag is a hint to the implementation that during text editing, the
     *  initial letter of each word should be capitalized.  This hint should be
     *  honored only on devices for which automatic capitalization is
     *  appropriate and when the character set of the text being edited has the
     *  notion of upper case and lower case letters.  The definition of
     *  word boundaries is implementation-specific.
     * 
     *  If the application specifies both the
     *  INITIAL_CAPS_WORD and the
     *  INITIAL_CAPS_SENTENCE flags,
     *  INITIAL_CAPS_WORD behavior should be used.
     * 
     *  The INITIAL_CAPS_WORD modifier can be combined
     *  with other input
     *  constraints by using the bit-wise OR operator
     *  (|).
     * 
     *  Constant 0x100000 is assigned to
     *  INITIAL_CAPS_WORD.
     * 
     * Since:
     *   MIDP 2.0
     * See Also:Constant Field Values
     */
    public static final int INITIAL_CAPS_WORD = ;

    /**
     * This flag is a hint to the implementation that during text editing, the
     *  initial letter of each sentence should be capitalized.  This hint
     *  should be honored only on devices for which automatic capitalization is
     *  appropriate and when the character set of the text being edited has the
     *  notion of upper case and lower case letters.  The definition of
     *  sentence boundaries is implementation-specific.
     * 
     *  If the application specifies both the
     *  INITIAL_CAPS_WORD and the
     *  INITIAL_CAPS_SENTENCE flags,
     *  INITIAL_CAPS_WORD behavior should be used.
     * 
     *  The INITIAL_CAPS_SENTENCE modifier can be
     *  combined with other input
     *  constraints by using the bit-wise OR operator
     *  (|).
     * 
     *  Constant 0x200000 is assigned to
     *  INITIAL_CAPS_SENTENCE.
     * 
     * Since:
     *   MIDP 2.0
     * See Also:Constant Field Values
     */
    public static final int INITIAL_CAPS_SENTENCE = ;

    /**
     * The mask value for determining the constraint mode. The application
     *  should
     *  use the bit-wise AND operation with a value returned by
     *  getConstraints() and
     *  CONSTRAINT_MASK in order to retrieve the current
     *  constraint mode,
     *  in order to remove any modifier flags such as the
     *  PASSWORD flag.
     * 
     *  Constant 0xFFFF is assigned to
     *  CONSTRAINT_MASK.
     * 
     * See Also:Constant Field Values
     */
    public static final int CONSTRAINT_MASK = ;

    /**
     * Creates a new TextField object with the given label, initial
     *  contents, maximum size in characters, and constraints.
     *  If the text parameter is null, the
     *  TextField is created empty.
     *  The maxSize parameter must be greater than zero.
     *  An IllegalArgumentException is thrown if the
     *  length of the initial contents string exceeds maxSize.
     *  However,
     *  the implementation may assign a maximum size smaller than the
     *  application had requested.  If this occurs, and if the length of the
     *  contents exceeds the newly assigned maximum size, the contents are
     *  truncated from the end in order to fit, and no exception is thrown.
     * 
     * Parameters:label - item labeltext - the initial contents, or null if the
     *  TextField is to be emptymaxSize - the maximum capacity in charactersconstraints - see input constraints
     * Throws:
     * IllegalArgumentException - if maxSize is zero or less
     * IllegalArgumentException - if the value of the constraints
     *  parameter
     *  is invalid
     * IllegalArgumentException - if text is illegal
     *  for the specified constraints
     * IllegalArgumentException - if the length of the string exceeds
     *  the requested maximum capacity
     */
    public TextField(String label, String text, int maxSize, int constraints) {
        construct(label, text, maxSize, constraints);
    }

    private native void construct(String label, String text, int maxSize, int constraints);

    /**
     * Gets the contents of the TextField as a string value.
     * 
     * Returns:the current contentsSee Also:setString(java.lang.String)
     */
    public String getString();

    /**
     * Sets the contents of the TextField as a string
     *  value, replacing the
     *  previous contents.
     * 
     * Parameters:text - the new value of the TextField, or
     *  null if the TextField is to be made empty
     * Throws:
     * IllegalArgumentException - if text
     *  is illegal for the current
     *  input constraints
     * IllegalArgumentException - if the text would exceed the current
     *  maximum capacitySee Also:getString()
     */
    public void setString(String text);

    /**
     * Copies the contents of the TextField into a
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
     * Sets the contents of the TextField from a
     *  character array, replacing the
     *  previous contents. Characters are copied from the region of the
     *  data array
     *  starting at array index offset and running for
     *  length characters.
     *  If the data array is null, the TextField
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
    public void setChars(char[] data, int offset, int length);

    /**
     * Inserts a string into the contents of the
     *  TextField.  The string is
     *  inserted just prior to the character indicated by the
     *  position parameter, where zero specifies the first
     *  character of the contents of the TextField.  If
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
     * IllegalArgumentException - if the insertion would exceed
     *  the current
     *  maximum capacity
     * NullPointerException - if src is null
     */
    public void insert(String src, int position);

    /**
     * Inserts a subrange of an array of characters into the contents of
     *  the TextField.  The offset and
     *  length parameters indicate the subrange
     *  of the data array to be used for insertion. Behavior is otherwise
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
     *  and length do not specify
     *  a valid range within the data array
     * IllegalArgumentException - if the resulting contents
     *  would be illegal for the current
     *  input constraints
     * IllegalArgumentException - if the insertion would exceed
     *  the current
     *  maximum capacity
     * NullPointerException - if data is null
     */
    public void insert(char[] data, int offset, int length, int position);

    /**
     * Deletes characters from the TextField.
     * 
     *  The offset and length parameters must
     *  specify a valid range of characters within
     *  the contents of the TextField.
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
     *  specify a valid range within the contents of the TextField
     */
    public void delete(int offset, int length);

    /**
     * Returns the maximum size (number of characters) that can be
     *  stored in this TextField.
     * 
     * Returns:the maximum size in charactersSee Also:setMaxSize(int)
     */
    public int getMaxSize();

    /**
     * Sets the maximum size (number of characters) that can be contained
     *  in this
     *  TextField. If the current contents of the
     *  TextField are larger than
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
     *  TextField.
     * 
     * Returns:number of characters in the TextField
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
     * Sets the input constraints of the TextField. If
     *  the the current contents
     *  of the TextField do not match the new
     *  constraints, the contents are
     *  set to empty.
     * 
     * Parameters:constraints - see input constraints
     * Throws:
     * IllegalArgumentException - if constraints is not any of the ones
     *  specified in input constraintsSee Also:getConstraints()
     */
    public void setConstraints(int constraints);

    /**
     * Gets the current input constraints of the TextField.
     * 
     * Returns:the current constraints value (see
     *  input constraints)See Also:setConstraints(int)
     */
    public int getConstraints();

    /**
     * Sets a hint to the implementation as to the input mode that should be
     *  used when the user initiates editing of this TextField.  The
     *  characterSubset parameter names a subset of Unicode
     *  characters that is used by the implementation to choose an initial
     *  input mode.  If null is passed, the implementation should
     *  choose a default input mode.
     * 
     *  See Input Modes for a full explanation of input
     *  modes.
     * 
     * Parameters:characterSubset - a string naming a Unicode character subset,
     *  or nullSince:
     *   MIDP 2.0
     */
    public void setInitialInputMode(String characterSubset);

}
