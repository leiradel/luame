package javax.microedition.lcdui;

public class DateField extends Item {
    /**
     * Input mode for date information (day, month, year). With this mode this
     *  DateField presents and allows only to modify date
     *  value. The time
     *  information of date object is ignored.
     * 
     *  Value 1 is assigned to DATE.
     * 
     * See Also:Constant Field Values
     */
    public static final int DATE = 1;

    /**
     * Input mode for time information (hours and minutes). With this mode this
     *  DateField presents and allows only to modify
     *  time. The date components
     *  should be set to the &quot;zero epoch&quot; value of January 1, 1970 and
     *  should not be accessed.
     * 
     *  Value 2 is assigned to TIME.
     * 
     * See Also:Constant Field Values
     */
    public static final int TIME = 2;

    /**
     * Input mode for date (day, month, year) and time (minutes, hours)
     *  information. With this mode this DateField
     *  presents and allows to modify
     *  both time and date information.
     * 
     *  Value 3 is assigned to DATE_TIME.
     * 
     * See Also:Constant Field Values
     */
    public static final int DATE_TIME = ;

    /**
     * Creates a DateField object with the specified
     *  label and mode. This call
     *  is identical to DateField(label, mode, null).
     * 
     * Parameters:label - item labelmode - the input mode, one of DATE, TIME
     *  or DATE_TIME
     * Throws:
     * IllegalArgumentException - if the input mode's
     *  value is invalid
     */
    public DateField(String label,
                 int mode) {
        construct(label, mode);
    }

    private native void construct(String label,
                 int mode);

    /**
     * Returns date value of this field. Returned value is
     *  null if field
     *  value is
     *  not initialized. The date object is constructed according the rules of
     *  locale specific calendaring system and defined time zone.
     * 
     *  In TIME mode field the date components are set to
     *  the &quot;zero
     *  epoch&quot; value of January 1, 1970. If a date object that presents time
     *  beyond one day from this &quot;zero epoch&quot; then this field
     *  is in &quot;not
     *  initialized&quot; state and this method returns null.
     * 
     *  In DATE mode field the time component of the calendar is set
     *  to zero when
     *  constructing the date object.
     * 
     * Returns:date object representing time or date depending on input modeSee Also:setDate(java.util.Date)
     */
    public Date getDate();

    /**
     * Sets a new value for this field. null can be
     *  passed to set the field
     *  state to &quot;not initialized&quot; state. The input mode of
     *  this field defines
     *  what components of passed Date object is used.
     * 
     *  In TIME input mode the date components must be set
     *  to the &quot;zero
     *  epoch&quot; value of January 1, 1970. If a date object that presents time
     *  beyond one day then this field is in &quot;not initialized&quot; state.
     *  In TIME input mode the date component of
     *  Date object is ignored and time
     *  component is used to precision of minutes.
     * 
     *  In DATE input mode the time component of
     *  Date object is ignored.
     * 
     *  In DATE_TIME input mode the date and time
     *  component of Date are used but
     *  only to precision of minutes.
     * 
     * Parameters:date - new value for this fieldSee Also:getDate()
     */
    public void setDate(Date date);

    /**
     * Gets input mode for this date field. Valid input modes are
     *  DATE, TIME and DATE_TIME.
     * 
     * Returns:input mode of this fieldSee Also:setInputMode(int)
     */
    public int getInputMode();

}
