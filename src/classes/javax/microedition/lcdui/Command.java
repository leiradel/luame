package javax.microedition.lcdui;

public class Command {
    /**
     * Specifies an application-defined command that pertains to the current
     *  screen. Examples could be &quot;Load&quot; and
     *  &quot;Save&quot;.  A SCREEN command
     *  generally applies to the entire screen's contents or to navigation
     *  among screens.  This is in constrast to the ITEM type,
     *  which applies to the currently activated or focused item or element
     *  contained within this screen.
     * 
     *  Value 1 is assigned to SCREEN.
     * 
     * See Also:Constant Field Values
     */
    public static final int SCREEN = ;

    /**
     * A navigation command that returns the user to the logically
     *  previous screen.
     *  The jump to the previous screen is not done automatically by the
     *  implementation
     *  but by the commandAction
     *  provided by
     *  the application.
     *  Note that the application defines the actual action since the strictly
     *  previous screen may not be logically correct.
     * 
     *  Value 2 is assigned to BACK.
     * 
     * See Also:CANCEL,
     * STOP,
     * Constant Field Values
     */
    public static final int BACK = ;

    /**
     * A command that is a standard negative answer to a dialog implemented by
     *  current screen.
     *  Nothing is cancelled automatically by the implementation; cancellation
     *  is implemented
     *  by the commandAction provided by
     *  the application.
     * 
     *   With this command type, the application hints to the implementation
     *  that the user wants to dismiss the current screen without taking any
     *  action
     *  on anything that has been entered into the current screen, and usually
     *  that
     *  the user wants to return to the prior screen. In many cases
     *  CANCEL is
     *  interchangeable with BACK, but BACK
     *  is mainly used for navigation
     *  as in a browser-oriented applications.
     * 
     *  Value 3 is assigned to CANCEL.
     * 
     * See Also:BACK,
     * STOP,
     * Constant Field Values
     */
    public static final int CANCEL = ;

    /**
     * A command that is a standard positive answer to a dialog implemented by
     *  current screen.
     *  Nothing is done automatically by the implementation; any action taken
     *  is implemented
     *  by the commandAction provided by
     *  the application.
     * 
     *   With this command type the application hints to the
     *  implementation that
     *  the user will use this command to ask the application to confirm
     *  the data
     *  that has been entered in the current screen and to proceed to the next
     *  logical screen.
     * 
     *  CANCEL is often used together with OK.
     * 
     *  Value 4 is assigned to OK.
     * 
     * See Also:CANCEL,
     * Constant Field Values
     */
    public static final int OK = ;

    /**
     * This command specifies a request for on-line help.
     *  No help information is shown automatically by the implementation.
     *  The
     *  commandAction provided by the
     *  application is responsible for showing the help information.
     * 
     *  Value 5 is assigned to HELP.
     * 
     * See Also:Constant Field Values
     */
    public static final int HELP = ;

    /**
     * A command that will stop some currently running
     *  process, operation, etc.
     *  Nothing is stopped automatically by the implementation.
     *  The cessation must
     *  be performed
     *  by the commandAction provided by
     *  the application.
     * 
     *   With this command type the application hints to the
     *  implementation that
     *  the user will use this command to stop any currently running process
     *  visible to the user on the current screen. Examples of running processes
     *  might include downloading or sending of data. Use of the
     *  STOP
     *  command does
     *  not necessarily imply a switch to another screen.
     * 
     *  Value 6 is assigned to STOP.
     * 
     * See Also:BACK,
     * CANCEL,
     * Constant Field Values
     */
    public static final int STOP = ;

    /**
     * A command used for exiting from the application.  When the user
     *  invokes this command, the implementation does not exit automatically.
     *  The application's
     *  commandAction
     *  will be called, and it should exit the application if it
     *  is appropriate to do so.
     * 
     *  Value 7 is assigned to EXIT.
     * 
     * See Also:Constant Field Values
     */
    public static final int EXIT = ;

    /**
     * With this command type the application can hint to the
     *  implementation that the command is specific to the items of
     *  the Screen or the elements of a
     *  Choice. Normally this
     *  means that command relates to the focused item or element.
     *  For example, an implementation of List can use
     *  this information for
     *  creating context sensitive menus.
     * 
     *  Value 8 is assigned to ITEM.
     * 
     * See Also:Constant Field Values
     */
    public static final int ITEM = ;

    /**
     * Creates a new command object with the given short
     * 
     *  label,
     *  type, and
     *  priority.
     * 
     *  The newly created command has no long label.  This constructor is
     *  identical to Command(label, null, commandType, priority).
     * 
     * Parameters:label - the command's short labelcommandType - the command's typepriority - the command's priority value
     * Throws:
     * NullPointerException - if label is null
     * IllegalArgumentException - if the commandType
     *  is an invalid typeSee Also:Command(String, String, int, int)
     */
    public Command(String label, int commandType, int priority) {
        construct(label, commandType, priority);
    }

    private native void construct(String label, int commandType, int priority);

    /**
     * Creates a new command object with the given
     *  labels,
     *  type, and
     *  priority.
     * 
     *  The short label is required and must not be
     *  null.  The long label is
     *  optional and may be null if the command is to have
     *  no long label.
     * 
     * Parameters:shortLabel - the command's short labellongLabel - the command's long label, or null if nonecommandType - the command's typepriority - the command's priority value
     * Throws:
     * NullPointerException - if shortLabel is
     *  null
     * IllegalArgumentException - if the commandType is an
     *  invalid typeSince:
     *   MIDP 2.0
     */
    public Command(String shortLabel, String longLabel, int commandType, int priority) {
        construct(shortLabel, longLabel, commandType, priority);
    }

    private native void construct(String shortLabel, String longLabel, int commandType, int priority);

    /**
     * Gets the short label of the command.
     * 
     * Returns:the Command's short label
     */
    public String getLabel();

    /**
     * Gets the long label of the command.
     * 
     * Returns:the Command's long label, or
     *  null if the Command has no long
     *  labelSince:
     *   MIDP 2.0
     */
    public String getLongLabel();

    /**
     * Gets the type of the command.
     * 
     * Returns:type of the Command
     */
    public int getCommandType();

    /**
     * Gets the priority of the command.
     * 
     * Returns:priority of the Command
     */
    public int getPriority();

}
