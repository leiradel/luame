package java.util;

public class Timer {
    /**
     * Schedules the specified task for execution after the specified delay.
     * 
     * Parameters:task - task to be scheduled.delay - delay in milliseconds before task is to be executed.
     * Throws:
     * IllegalArgumentException - if delay is negative, or
     *          delay + System.currentTimeMillis() is negative.
     * IllegalStateException - if task was already scheduled or
     *          cancelled, or timer was cancelled.
     */
    public void schedule(TimerTask task,
                     long delay);

    /**
     * Schedules the specified task for execution at the specified time.  If
     *  the time is in the past, the task is scheduled for immediate execution.
     * 
     * Parameters:task - task to be scheduled.time - time at which task is to be executed.
     * Throws:
     * IllegalArgumentException - if time.getTime() is negative.
     * IllegalStateException - if task was already scheduled or
     *          cancelled, timer was cancelled, or timer thread terminated.
     */
    public void schedule(TimerTask task,
                     Date time);

    /**
     * Schedules the specified task for repeated fixed-delay execution,
     *  beginning after the specified delay.  Subsequent executions take place
     *  at approximately regular intervals separated by the specified period.
     * 
     *  In fixed-delay execution, each execution is scheduled relative to
     *  the actual execution time of the previous execution.  If an execution
     *  is delayed for any reason (such as garbage collection or other
     *  background activity), subsequent executions will be delayed as well.
     *  In the long run, the frequency of execution will generally be slightly
     *  lower than the reciprocal of the specified period (assuming the system
     *  clock underlying Object.wait(long) is accurate).
     * 
     *  Fixed-delay execution is appropriate for recurring activities
     *  that require "smoothness."  In other words, it is appropriate for
     *  activities where it is more important to keep the frequency accurate
     *  in the short run than in the long run.  This includes most animation
     *  tasks, such as blinking a cursor at regular intervals.  It also includes
     *  tasks wherein regular activity is performed in response to human
     *  input, such as automatically repeating a character as long as a key
     *  is held down.
     * 
     * Parameters:task - task to be scheduled.delay - delay in milliseconds before task is to be executed.period - time in milliseconds between successive task executions.
     * Throws:
     * IllegalArgumentException - if delay is negative, or
     *          delay + System.currentTimeMillis() is negative.
     * IllegalStateException - if task was already scheduled or
     *          cancelled, timer was cancelled, or timer thread terminated.
     */
    public void schedule(TimerTask task,
                     long delay,
                     long period);

    /**
     * Schedules the specified task for repeated fixed-delay execution,
     *  beginning at the specified time. Subsequent executions take place at
     *  approximately regular intervals, separated by the specified period.
     * 
     *  In fixed-delay execution, each execution is scheduled relative to
     *  the actual execution time of the previous execution.  If an execution
     *  is delayed for any reason (such as garbage collection or other
     *  background activity), subsequent executions will be delayed as well.
     *  In the long run, the frequency of execution will generally be slightly
     *  lower than the reciprocal of the specified period (assuming the system
     *  clock underlying Object.wait(long) is accurate).
     * 
     *  Fixed-delay execution is appropriate for recurring activities
     *  that require "smoothness."  In other words, it is appropriate for
     *  activities where it is more important to keep the frequency accurate
     *  in the short run than in the long run.  This includes most animation
     *  tasks, such as blinking a cursor at regular intervals.  It also includes
     *  tasks wherein regular activity is performed in response to human
     *  input, such as automatically repeating a character as long as a key
     *  is held down.
     * 
     * Parameters:task - task to be scheduled.firstTime - First time at which task is to be executed.period - time in milliseconds between successive task executions.
     * Throws:
     * IllegalArgumentException - if time.getTime() is negative.
     * IllegalStateException - if task was already scheduled or
     *          cancelled, timer was cancelled, or timer thread terminated.
     */
    public void schedule(TimerTask task,
                     Date firstTime,
                     long period);

    /**
     * Schedules the specified task for repeated fixed-rate execution,
     *  beginning after the specified delay.  Subsequent executions take place
     *  at approximately regular intervals, separated by the specified period.
     * 
     *  In fixed-rate execution, each execution is scheduled relative to the
     *  scheduled execution time of the initial execution.  If an execution is
     *  delayed for any reason (such as garbage collection or other background
     *  activity), two or more executions will occur in rapid succession to
     *  "catch up."  In the long run, the frequency of execution will be
     *  exactly the reciprocal of the specified period (assuming the system
     *  clock underlying Object.wait(long) is accurate).
     * 
     *  Fixed-rate execution is appropriate for recurring activities that
     *  are sensitive to absolute time, such as ringing a chime every
     *  hour on the hour, or running scheduled maintenance every day at a
     *  particular time.  It is also appropriate for for recurring activities
     *  where the total time to perform a fixed number of executions is
     *  important, such as a countdown timer that ticks once every second for
     *  ten seconds.  Finally, fixed-rate execution is appropriate for
     *  scheduling multiple repeating timer tasks that must remain synchronized
     *  with respect to one another.
     * 
     * Parameters:task - task to be scheduled.delay - delay in milliseconds before task is to be executed.period - time in milliseconds between successive task executions.
     * Throws:
     * IllegalArgumentException - if delay is negative, or
     *          delay + System.currentTimeMillis() is negative.
     * IllegalStateException - if task was already scheduled or
     *          cancelled, timer was cancelled, or timer thread terminated.
     */
    public void scheduleAtFixedRate(TimerTask task,
                                long delay,
                                long period);

    /**
     * Schedules the specified task for repeated fixed-rate execution,
     *  beginning at the specified time. Subsequent executions take place at
     *  approximately regular intervals, separated by the specified period.
     * 
     *  In fixed-rate execution, each execution is scheduled relative to the
     *  scheduled execution time of the initial execution.  If an execution is
     *  delayed for any reason (such as garbage collection or other background
     *  activity), two or more executions will occur in rapid succession to
     *  "catch up."  In the long run, the frequency of execution will be
     *  exactly the reciprocal of the specified period (assuming the system
     *  clock underlying Object.wait(long) is accurate).
     * 
     *  Fixed-rate execution is appropriate for recurring activities that
     *  are sensitive to absolute time, such as ringing a chime every
     *  hour on the hour, or running scheduled maintenance every day at a
     *  particular time.  It is also appropriate for for recurring activities
     *  where the total time to perform a fixed number of executions is
     *  important, such as a countdown timer that ticks once every second for
     *  ten seconds.  Finally, fixed-rate execution is appropriate for
     *  scheduling multiple repeating timer tasks that must remain synchronized
     *  with respect to one another.
     * 
     * Parameters:task - task to be scheduled.firstTime - First time at which task is to be executed.period - time in milliseconds between successive task executions.
     * Throws:
     * IllegalArgumentException - if time.getTime() is negative.
     * IllegalStateException - if task was already scheduled or
     *          cancelled, timer was cancelled, or timer thread terminated.
     */
    public void scheduleAtFixedRate(TimerTask task,
                                Date firstTime,
                                long period);

}
