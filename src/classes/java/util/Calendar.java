package java.util;

public class Calendar {
    /**
     * Field number for get and set indicating the
     *  year. This is a calendar-specific value.
     * 
     * See Also:Constant Field Values
     */
    public static final int YEAR = ;

    /**
     * Field number for get and set indicating the
     *  month. This is a calendar-specific value.
     * 
     * See Also:Constant Field Values
     */
    public static final int MONTH = ;

    /**
     * Field number for get and set indicating the
     *  day of the month. This is a synonym for DAY_OF_MONTH.
     * 
     * See Also:DAY_OF_MONTH,
     * Constant Field Values
     */
    public static final int DATE = ;

    /**
     * Field number for get and set indicating the
     *  day of the month. This is a synonym for DATE.
     * 
     * See Also:DATE,
     * Constant Field Values
     */
    public static final int DAY_OF_MONTH = ;

    /**
     * Field number for get and set indicating the
     *  day of the week.
     * 
     * See Also:Constant Field Values
     */
    public static final int DAY_OF_WEEK = ;

    /**
     * Field number for get and set indicating
     *  whether the HOUR is before or after noon.
     *  E.g., at 10:04:15.250 PM the AM_PM is PM.
     * 
     * See Also:AM,
     * PM,
     * HOUR,
     * Constant Field Values
     */
    public static final int AM_PM = ;

    /**
     * Field number for get and set indicating the
     *  hour of the morning or afternoon. HOUR is used for the
     *  12-hour clock.
     *  E.g., at 10:04:15.250 PM the HOUR is 10.
     * 
     * See Also:AM_PM,
     * HOUR_OF_DAY,
     * Constant Field Values
     */
    public static final int HOUR = ;

    /**
     * Field number for get and set indicating the
     *  hour of the day. HOUR_OF_DAY is used for the 24-hour clock.
     *  E.g., at 10:04:15.250 PM the HOUR_OF_DAY is 22.
     * 
     * See Also:Constant Field Values
     */
    public static final int HOUR_OF_DAY = ;

    /**
     * Field number for get and set indicating the
     *  minute within the hour.
     *  E.g., at 10:04:15.250 PM the MINUTE is 4.
     * 
     * See Also:Constant Field Values
     */
    public static final int MINUTE = ;

    /**
     * Field number for get and set indicating the
     *  second within the minute.
     *  E.g., at 10:04:15.250 PM the SECOND is 15.
     * 
     * See Also:Constant Field Values
     */
    public static final int SECOND = ;

    /**
     * Field number for get and set indicating the
     *  millisecond within the second.
     *  E.g., at 10:04:15.250 PM the MILLISECOND is 250.
     * 
     * See Also:Constant Field Values
     */
    public static final int MILLISECOND = ;

    /**
     * Value of the DAY_OF_WEEK field indicating
     *  Sunday.
     * 
     * See Also:Constant Field Values
     */
    public static final int SUNDAY = ;

    /**
     * Value of the DAY_OF_WEEK field indicating
     *  Monday.
     * 
     * See Also:Constant Field Values
     */
    public static final int MONDAY = ;

    /**
     * Value of the DAY_OF_WEEK field indicating
     *  Tuesday.
     * 
     * See Also:Constant Field Values
     */
    public static final int TUESDAY = ;

    /**
     * Value of the DAY_OF_WEEK field indicating
     *  Wednesday.
     * 
     * See Also:Constant Field Values
     */
    public static final int WEDNESDAY = ;

    /**
     * Value of the DAY_OF_WEEK field indicating
     *  Thursday.
     * 
     * See Also:Constant Field Values
     */
    public static final int THURSDAY = ;

    /**
     * Value of the DAY_OF_WEEK field indicating
     *  Friday.
     * 
     * See Also:Constant Field Values
     */
    public static final int FRIDAY = ;

    /**
     * Value of the DAY_OF_WEEK field indicating
     *  Saturday.
     * 
     * See Also:Constant Field Values
     */
    public static final int SATURDAY = ;

    /**
     * Value of the MONTH field indicating the
     *  first month of the year.
     * 
     * See Also:Constant Field Values
     */
    public static final int JANUARY = ;

    /**
     * Value of the MONTH field indicating the
     *  second month of the year.
     * 
     * See Also:Constant Field Values
     */
    public static final int FEBRUARY = ;

    /**
     * Value of the MONTH field indicating the
     *  third month of the year.
     * 
     * See Also:Constant Field Values
     */
    public static final int MARCH = ;

    /**
     * Value of the MONTH field indicating the
     *  fourth month of the year.
     * 
     * See Also:Constant Field Values
     */
    public static final int APRIL = ;

    /**
     * Value of the MONTH field indicating the
     *  fifth month of the year.
     * 
     * See Also:Constant Field Values
     */
    public static final int MAY = ;

    /**
     * Value of the MONTH field indicating the
     *  sixth month of the year.
     * 
     * See Also:Constant Field Values
     */
    public static final int JUNE = ;

    /**
     * Value of the MONTH field indicating the
     *  seventh month of the year.
     * 
     * See Also:Constant Field Values
     */
    public static final int JULY = ;

    /**
     * Value of the MONTH field indicating the
     *  eighth month of the year.
     * 
     * See Also:Constant Field Values
     */
    public static final int AUGUST = ;

    /**
     * Value of the MONTH field indicating the
     *  ninth month of the year.
     * 
     * See Also:Constant Field Values
     */
    public static final int SEPTEMBER = ;

    /**
     * Value of the MONTH field indicating the
     *  tenth month of the year.
     * 
     * See Also:Constant Field Values
     */
    public static final int OCTOBER = ;

    /**
     * Value of the MONTH field indicating the
     *  eleventh month of the year.
     * 
     * See Also:Constant Field Values
     */
    public static final int NOVEMBER = ;

    /**
     * Value of the MONTH field indicating the
     *  twelfth month of the year.
     * 
     * See Also:Constant Field Values
     */
    public static final int DECEMBER = ;

    /**
     * Value of the AM_PM field indicating the
     *  period of the day from midnight to just before noon.
     * 
     * See Also:Constant Field Values
     */
    public static final int AM = ;

    /**
     * Value of the AM_PM field indicating the
     *  period of the day from noon to just before midnight.
     * 
     * See Also:Constant Field Values
     */
    public static final int PM = ;

    /**
     * The field values for the currently set time for this calendar.
     */
    protected int[] fields = ;

    /**
     * The flags which tell if a specified time field for the calendar is set.
     *  This is an array of FIELD_COUNT booleans,
     */
    protected boolean[] isSet = ;

    /**
     * The currently set time for this calendar, expressed in milliseconds after
     *  January 1, 1970, 0:00:00 GMT.
     */
    protected long time = ;

    /**
     * Gets this Calendar's current time.
     * 
     * Returns:the current time.See Also:setTime(java.util.Date)
     */
    public final Date getTime();

    /**
     * Sets this Calendar's current time with the given Date.
     * 
     *  Note: Calling setTime() with
     *  Date(Long.MAX_VALUE) or Date(Long.MIN_VALUE)
     *  may yield incorrect field values from get().
     * 
     * Parameters:date - the given Date.See Also:getTime()
     */
    public final void setTime(Date date);

    /**
     * Gets a calendar using the default time zone.
     * 
     * Returns:a Calendar.
     */
    public static Calendar getInstance();

    /**
     * Gets a calendar using the specified time zone.
     * 
     * Parameters:zone - the time zone to use
     * Returns:a Calendar.
     */
    public static Calendar getInstance(TimeZone zone);

    /**
     * Gets this Calendar's current time as a long expressed in milliseconds
     *  after January 1, 1970, 0:00:00 GMT (the epoch).
     * 
     * Returns:the current time as UTC milliseconds from the epoch.See Also:setTimeInMillis(long)
     */
    protected long getTimeInMillis();

    /**
     * Sets this Calendar's current time from the given long value.
     * 
     * Parameters:millis - the new time in UTC milliseconds from the epoch.See Also:getTimeInMillis()
     */
    protected void setTimeInMillis(long millis);

    /**
     * Gets the value for a given time field.
     * 
     * Parameters:field - the given time field (either YEAR, MONTH, DATE, DAY_OF_WEEK,
     *                                     HOUR_OF_DAY, HOUR, AM_PM, MINUTE,
     *                                     SECOND, or MILLISECOND
     * Returns:the value for the given time field.
     * Throws:
     * ArrayIndexOutOfBoundsException - if the parameter is not
     *  one of the above.
     */
    public final int get(int field);

    /**
     * Sets the time field with the given value.
     * 
     * Parameters:field - the given time field.value - the value to be set for the given time field.
     * Throws:
     * ArrayIndexOutOfBoundsException - if an illegal field
     *  parameter is received.
     */
    public final void set(int field,
                      int value);

    /**
     * Compares this calendar to the specified object.
     *  The result is true if and only if the argument is
     *  not null and is a Calendar object that
     *  represents the same calendar as this object.
     * 
     * Overrides:equals in class Object
     * 
     * Parameters:obj - the object to compare with.
     * Returns:true if the objects are the same;
     *  false otherwise.See Also:Boolean.hashCode(),
     * Hashtable
     */
    public boolean equals(Object obj);

    /**
     * Compares the time field records.
     *  Equivalent to comparing result of conversion to UTC.
     * 
     * Parameters:when - the Calendar to be compared with this Calendar.
     * Returns:true if the current time of this Calendar is before
     *  the time of Calendar when; false otherwise.
     */
    public boolean before(Object when);

    /**
     * Compares the time field records.
     *  Equivalent to comparing result of conversion to UTC.
     * 
     * Parameters:when - the Calendar to be compared with this Calendar.
     * Returns:true if the current time of this Calendar is after
     *  the time of Calendar when; false otherwise.
     */
    public boolean after(Object when);

    /**
     * Sets the time zone with the given time zone value.
     * 
     * Parameters:value - the given time zone.See Also:getTimeZone()
     */
    public void setTimeZone(TimeZone value);

    /**
     * Gets the time zone.
     * 
     * Returns:the time zone object associated with this calendar.See Also:setTimeZone(java.util.TimeZone)
     */
    public TimeZone getTimeZone();

    /**
     * Converts
     *  the current millisecond time value
     *  time
     *  to field values in fields[].
     *  This allows you to sync up the time field values with
     *  a new time that is set for the calendar.
     */
    protected abstract void computeFields();

}
