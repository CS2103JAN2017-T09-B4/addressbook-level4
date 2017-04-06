package seedu.tache.testutil;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import seedu.tache.model.tag.UniqueTagList;
import seedu.tache.model.task.DateTime;
import seedu.tache.model.task.Name;
import seedu.tache.model.task.ReadOnlyTask;
import seedu.tache.model.task.Task;
import seedu.tache.model.task.Task.RecurInterval;

/**
 * A mutable task object. For testing only.
 */
public class TestTask implements ReadOnlyTask {

    private Name name;
    private Optional<DateTime> startDateTime;
    private Optional<DateTime> endDateTime;
    private UniqueTagList tags;
    private boolean isTimed;
    private boolean isActive;
    private boolean isRecurring;
    private RecurInterval interval;
    private List<Date> recurCompletedList;

    public TestTask() {
        tags = new UniqueTagList();
        this.startDateTime = Optional.empty();
        this.endDateTime = Optional.empty();
        this.interval = RecurInterval.NONE;
        this.isTimed = false;
        this.isActive = true;
        this.isRecurring = false;
    }

    //@@author A0142255M
    /**
     * Creates a copy of {@code taskToCopy}.
     */
    public TestTask(TestTask taskToCopy) {
        this.name = taskToCopy.getName();
        this.tags = taskToCopy.getTags();
        this.startDateTime = taskToCopy.getStartDateTime();
        this.endDateTime = taskToCopy.getEndDateTime();
        this.interval = taskToCopy.getRecurInterval();
        this.isTimed = taskToCopy.getTimedStatus();
        this.isActive = taskToCopy.getActiveStatus();
        this.isRecurring = taskToCopy.getRecurringStatus();
    }
    //@@author

    public void setName(Name name) {
        this.name = name;
    }

    public void setTags(UniqueTagList tags) {
        this.tags = tags;
    }

    public void setStartDateTime(DateTime startDateTime) {
        this.startDateTime = Optional.of(startDateTime);
    }

    public void setEndDateTime(DateTime endDateTime) {
        this.endDateTime = Optional.of(endDateTime);
    }

    @Override
    public Name getName() {
        return name;
    }

    @Override
    public Optional<DateTime> getStartDateTime() {
        return startDateTime;
    }

    @Override
    public Optional<DateTime> getEndDateTime() {
        return endDateTime;
    }

    @Override
    public UniqueTagList getTags() {
        return tags;
    }

    @Override
    public String toString() {
        return getAsText();
    }

    //@@author A0142255M
    public String getAddCommand() {
        StringBuilder sb = new StringBuilder();
        sb.append("add " + this.getName().fullName);
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        if (this.getStartDateTime().isPresent()) {
            sb.append(" from " + sdf.format(this.getStartDateTime().get().getDate()));
        }
        if (this.getEndDateTime().isPresent()) {
            sb.append(" to " + sdf.format(this.getEndDateTime().get().getDate()));
        }
        if (this.getTags().iterator().hasNext()) {
            sb.append(" t/ ");
        }
        this.getTags().asObservableList().stream().forEach(s -> sb.append(s.tagName + " "));
        return sb.toString();
    }

    @Override
    public boolean getTimedStatus() {
        return isTimed;
    }

    //@@author

    @Override
    public boolean getActiveStatus() {
        return isActive;
    }

    @Override
    public boolean getRecurringStatus() {
        return isRecurring;
    }

    @Override
    public RecurInterval getRecurInterval() {
        return interval;
    }

    @Override
    public boolean isWithinDate(Date date) {
        return false;
    }

    @Override
    public List<Date> getRecurCompletedList() {
        return recurCompletedList;
    }

    @Override
    public List<Task> getUncompletedRecurList(Date endingDateRange) {
        return null;
    }

    @Override
    public String getRecurDisplayDate() {
        return "";
    }

    @Override
    public boolean isMasterRecurring() {
        return false;
    }

    public static Comparator<TestTask> taskDateComparator = new Comparator<TestTask>() {

        public int compare(TestTask task1, TestTask task2) {
            Date lastComparableDate = new Date(0);
            int result = 0;
            //ascending order
            if (!task1.getEndDateTime().isPresent() && task2.getEndDateTime().isPresent()) {
                result = lastComparableDate.compareTo(task2.getEndDateTime().get().getDate());
                lastComparableDate = task2.getEndDateTime().get().getDate();
            }
            if (task1.getEndDateTime().isPresent() && !task2.getEndDateTime().isPresent()) {
                result = task1.getEndDateTime().get().getDate().compareTo(lastComparableDate);
                lastComparableDate = task1.getEndDateTime().get().getDate();
            }
            if (task1.getEndDateTime().isPresent() && task2.getEndDateTime().isPresent()) {
                return task1.getEndDateTime().get().getDate().compareTo(task2.getEndDateTime().get().getDate());
            }
            return (result);
        }

    };

}
