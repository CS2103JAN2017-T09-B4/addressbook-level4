//@@author A0139925U
package seedu.tache.model.recurstate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import seedu.tache.model.task.DateTime;

public class RecurState {
    public enum RecurInterval { NONE, DAY, WEEK, MONTH, YEAR };

    private boolean isRecurring;
    private RecurInterval interval;
    private List<Date> recurCompletedList;
    private String recurDisplayDate;

    public RecurState() {
        this.isRecurring = false;
        this.interval = RecurInterval.NONE;
        this.recurCompletedList = new ArrayList<Date>();
        this.recurDisplayDate = "";
    }

    public RecurState(boolean isRecurring, RecurInterval interval, List<Date> recurCompletedList) {
        this.isRecurring = isRecurring;
        this.interval = interval;
        this.recurCompletedList = recurCompletedList;
        this.recurDisplayDate = "";
    }

    public boolean getRecurringStatus() {
        return isRecurring;
    }

    public void setRecurringStatus(boolean isRecurring) {
        this.isRecurring = isRecurring;
    }

    public RecurInterval getRecurInterval() {
        return interval;
    }

    public void setRecurInterval(RecurInterval interval) {
        this.interval = interval;
    }

    public boolean isMasterRecurring() {
        return this.isRecurring && this.recurDisplayDate.equals("");
    }

    public boolean isGhostRecurring() {
        return this.isRecurring && !this.recurDisplayDate.equals("");
    }

    public List<Date> getRecurCompletedList() {
        return this.recurCompletedList;
    }

    public void setRecurCompletedList(List<Date> recurCompletedList) {
        this.recurCompletedList = recurCompletedList;
    }

    public List<Date> getUncompletedRecurList(DateTime startDateTime, DateTime endDateTime) {
        List<Date> uncompletedRecurList = new ArrayList<Date>();
        if (isRecurring) {
            Calendar calendarCurrent = Calendar.getInstance();
            calendarCurrent.setTime(new Date(startDateTime.getAmericanDateOnly()
                                        + " " + startDateTime.getTimeOnly()));

            Calendar calendarEnd = Calendar.getInstance();
            calendarEnd.setTime(new Date(endDateTime.getAmericanDateOnly()
                                        + " " + endDateTime.getTimeOnly()));
            calendarEnd.add(Calendar.SECOND, 1);

            //Populate 'Ghost' Task
            while (calendarCurrent.getTime().before(calendarEnd.getTime())) {

                if (!isRecurCompleted(calendarCurrent.getTime())) {
                    uncompletedRecurList.add(calendarCurrent.getTime());
                }

                if (interval.equals(RecurInterval.DAY)) {
                    calendarCurrent.add(Calendar.DATE, 1);
                } else if (interval.equals(RecurInterval.WEEK)) {
                    calendarCurrent.add(Calendar.WEEK_OF_YEAR, 1);
                } else if (interval.equals(RecurInterval.MONTH)) {
                    calendarCurrent.add(Calendar.MONTH, 1);
                } else if (interval.equals(RecurInterval.YEAR)) {
                    calendarCurrent.add(Calendar.YEAR, 1);
                }
            }
        }
        return uncompletedRecurList;

    }

    public boolean isRecurCompleted(Date recurCompleted) {
        DateFormat outputFormatter = new SimpleDateFormat("MM/dd/yyyy");
        for (int i = 0; i < getRecurCompletedList().size(); i++) {
            if (outputFormatter.format(getRecurCompletedList().get(i))
                                .equals(outputFormatter.format(recurCompleted))) {
                return true;
            }
        }
        return false;
    }

    public String getRecurDisplayDate() {
        return this.recurDisplayDate;
    }

    public void setRecurDisplayDate(String recurDisplayDate) {
        this.recurDisplayDate = recurDisplayDate;
    }
}
