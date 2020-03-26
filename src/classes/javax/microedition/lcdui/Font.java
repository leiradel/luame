package javax.microedition.lcdui;

public class Font {
    /**
     * The plain style constant. This may be combined with the
     *  other style constants for mixed styles.
     * 
     *  Value 0 is assigned to STYLE_PLAIN.
     * 
     * See Also:Constant Field Values
     */
    public static final int STYLE_PLAIN = ;

    /**
     * The bold style constant. This may be combined with the
     *  other style constants for mixed styles.
     * 
     *  Value 1 is assigned to STYLE_BOLD.
     * 
     * See Also:Constant Field Values
     */
    public static final int STYLE_BOLD = ;

    /**
     * The italicized style constant. This may be combined with
     *  the other style constants for mixed styles.
     * 
     *  Value 2 is assigned to STYLE_ITALIC.
     * 
     * See Also:Constant Field Values
     */
    public static final int STYLE_ITALIC = ;

    /**
     * The underlined style constant. This may be combined with
     *  the other style constants for mixed styles.
     * 
     *  Value 4 is assigned to STYLE_UNDERLINED.
     * 
     * See Also:Constant Field Values
     */
    public static final int STYLE_UNDERLINED = ;

    /**
     * The &quot;small&quot; system-dependent font size.
     * 
     *  Value 8 is assigned to STYLE_SMALL.
     * 
     * See Also:Constant Field Values
     */
    public static final int SIZE_SMALL = ;

    /**
     * The &quot;medium&quot; system-dependent font size.
     * 
     *  Value 0 is assigned to STYLE_MEDIUM.
     * 
     * See Also:Constant Field Values
     */
    public static final int SIZE_MEDIUM = ;

    /**
     * The &quot;large&quot; system-dependent font size.
     * 
     *  Value 16 is assigned to SIZE_LARGE.
     * 
     * See Also:Constant Field Values
     */
    public static final int SIZE_LARGE = ;

    /**
     * The &quot;system&quot; font face.
     * 
     *  Value 0 is assigned to FACE_SYSTEM.
     * 
     * See Also:Constant Field Values
     */
    public static final int FACE_SYSTEM = ;

    /**
     * The &quot;monospace&quot; font face.
     * 
     *  Value 32 is assigned to FACE_MONOSPACE.
     * 
     * See Also:Constant Field Values
     */
    public static final int FACE_MONOSPACE = ;

    /**
     * The &quot;proportional&quot; font face.
     * 
     *  Value 64 is assigned to
     *  FACE_PROPORTIONAL.
     * 
     * See Also:Constant Field Values
     */
    public static final int FACE_PROPORTIONAL = ;

    /**
     * Default font specifier used to draw Item and Screen contents.
     * 
     *  FONT_STATIC_TEXT has the value 0.
     * 
     * Since:
     *   MIDP 2.0
     * See Also:getFont(int fontSpecifier),
     * Constant Field Values
     */
    public static final int FONT_STATIC_TEXT = 0;

    /**
     * Font specifier used by the implementation to draw text input by
     *  a user.
     * 
     *  FONT_INPUT_TEXT has the value 1.
     * 
     * Since:
     *   MIDP 2.0
     * See Also:getFont(int fontSpecifier),
     * Constant Field Values
     */
    public static final int FONT_INPUT_TEXT = 1;

    /**
     * Gets the Font used by the high level user interface
     *  for the fontSpecifier passed in. It should be used
     *  by subclasses of
     *  CustomItem and Canvas to match user
     *  interface on the device.
     * 
     * Parameters:fontSpecifier - one of FONT_INPUT_TEXT, or
     *  FONT_STATIC_TEXT
     * Returns:font that corresponds to the passed in font specifier
     * Throws:
     * IllegalArgumentException - if fontSpecifier is not
     *  a valid fontSpecifierSince:
     *   MIDP 2.0
     */
    public static Font getFont(int fontSpecifier);

    /**
     * Gets the default font of the system.
     * 
     * Returns:the default font
     */
    public static Font getDefaultFont();

    /**
     * Obtains an object representing a font having the specified face, style,
     *  and size. If a matching font does not exist, the system will
     *  attempt to provide the closest match. This method always
     *  returns
     *  a valid font object, even if it is not a close match to the request.
     * 
     * Parameters:face - one of FACE_SYSTEM,
     *  FACE_MONOSPACE, or FACE_PROPORTIONALstyle - STYLE_PLAIN, or a combination of
     *  STYLE_BOLD,
     *  STYLE_ITALIC, and STYLE_UNDERLINEDsize - one of SIZE_SMALL, SIZE_MEDIUM,
     *  or SIZE_LARGE
     * Returns:instance the nearest font found
     * Throws:
     * IllegalArgumentException - if face,
     *  style, or size are not
     *  legal values
     */
    public static Font getFont(int face,
                           int style,
                           int size);

    /**
     * Gets the style of the font. The value is an OR'ed
     *  combination of
     *  STYLE_BOLD, STYLE_ITALIC, and
     *  STYLE_UNDERLINED; or the value is
     *  zero (STYLE_PLAIN).
     * 
     * Returns:style of the current fontSee Also:isPlain(),
     * isBold(),
     * isItalic()
     */
    public int getStyle();

    /**
     * Gets the size of the font.
     * 
     * Returns:one of SIZE_SMALL, SIZE_MEDIUM,
     *  SIZE_LARGE
     */
    public int getSize();

    /**
     * Gets the face of the font.
     * 
     * Returns:one of FACE_SYSTEM,
     *  FACE_PROPORTIONAL, FACE_MONOSPACE
     */
    public int getFace();

    /**
     * Returns true if the font is plain.
     * 
     * Returns:true if font is plainSee Also:getStyle()
     */
    public boolean isPlain();

    /**
     * Returns true if the font is bold.
     * 
     * Returns:true if font is boldSee Also:getStyle()
     */
    public boolean isBold();

    /**
     * Returns true if the font is italic.
     * 
     * Returns:true if font is italicSee Also:getStyle()
     */
    public boolean isItalic();

    /**
     * Returns true if the font is underlined.
     * 
     * Returns:true if font is underlinedSee Also:getStyle()
     */
    public boolean isUnderlined();

    /**
     * Gets the standard height of a line of text in this font. This value
     *  includes sufficient spacing to ensure that lines of text painted this
     *  distance from anchor point to anchor point are spaced as intended by the
     *  font designer and the device. This extra space (leading) occurs below
     *  the text.
     * 
     * Returns:standard height of a line of text in this font (a
     *  non-negative value)
     */
    public int getHeight();

    /**
     * Gets the distance in pixels from the top of the text to the text's
     *  baseline.
     * 
     * Returns:the distance in pixels from the top of the text to the text's
     *  baseline
     */
    public int getBaselinePosition();

    /**
     * Gets the advance width of the specified character in this Font.
     *  The advance width is the horizontal distance that would be occupied if
     *  ch were to be drawn using this Font,
     *  including inter-character spacing following
     *  ch necessary for proper positioning of subsequent text.
     * 
     * Parameters:ch - the character to be measured
     * Returns:the total advance width (a non-negative value)
     */
    public int charWidth(char ch);

    /**
     * Returns the advance width of the characters in ch,
     *  starting at the specified offset and for the specified number of
     *  characters (length).
     *  The advance width is the horizontal distance that would be occupied if
     *  the characters were to be drawn using this Font,
     *  including inter-character spacing following
     *  the characters necessary for proper positioning of subsequent text.
     * 
     *  The offset and length parameters must
     *  specify a valid range of characters
     *  within the character array ch. The offset
     *  parameter must be within the
     *  range [0..(ch.length)], inclusive.
     *  The length parameter must be a non-negative
     *  integer such that (offset + length) &lt;= ch.length.
     * 
     * Parameters:ch - the array of charactersoffset - the index of the first character to measurelength - the number of characters to measure
     * Returns:the width of the character range
     * Throws:
     * ArrayIndexOutOfBoundsException - if offset and
     *  length specify an
     *  invalid range
     * NullPointerException - if ch is null
     */
    public int charsWidth(char[] ch,
                      int offset,
                      int length);

    /**
     * Gets the total advance width for showing the specified
     *  String
     *  in this Font.
     *  The advance width is the horizontal distance that would be occupied if
     *  str were to be drawn using this Font,
     *  including inter-character spacing following
     *  str necessary for proper positioning of subsequent text.
     * 
     * Parameters:str - the String to be measured
     * Returns:the total advance width
     * Throws:
     * NullPointerException - if str is null
     */
    public int stringWidth(String str);

}
