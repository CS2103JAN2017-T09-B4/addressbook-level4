package seedu.tache.model.task;

import seedu.tache.commons.exceptions.IllegalValueException;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Date;

import org.ocpsoft.prettytime.PrettyTime;
import org.ocpsoft.prettytime.nlp.PrettyTimeParser;


public class DateTime {

    public static final String MESSAGE_DATE_CONSTRAINTS =
            "Task date should only contain <CONSTRAINT>";

    public final Date date;

    /**
     * Validates given date.
     *
     * @throws IllegalValueException if given date string is invalid.
     */
    public DateTime(String date) {
        assert date != null;
        String trimmedStartDate = date.trim();
        List<Date> temp = new PrettyTimeParser().parse(trimmedStartDate);
        
        /*if (!isValidDate(trimmedStartDate)) {
            throw new IllegalValueException(MESSAGE_TIME_CONSTRAINTS);
        }*/
        this.date = temp.get(0);
    }

    /**
     * Returns true if a given string is a valid task date.
     */
    /*public static boolean isValidDate(String test) {
        return test.matches(DATE_VALIDATION_REGEX);
    }*/

    @Override
    public String toString() {
        return new PrettyTime().format(date);
    }
    
    public String getDateOnly(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(date);
    }
    
    public String getTimeOnly(){
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(date);
    }
    
    public Date changeDate(int day, int month, int year){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.YEAR, year);
        return cal.getTime();
    }

   /* @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Date // instanceof handles nulls
                && this.time.equals(((Date) other).time)); // state check
    }*/

    /*@Override
    public int hashCode() {
        return (startDate.hashCode() && endDate.hashCode());
    }*/

}
