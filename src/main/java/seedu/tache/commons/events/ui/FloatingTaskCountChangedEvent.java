package seedu.tache.commons.events.ui;

import seedu.tache.commons.events.BaseEvent;

/**
 * Indicates that the task list type is changed.
 */
public class FloatingTaskCountChangedEvent extends BaseEvent {

    public final String message;

    public FloatingTaskCountChangedEvent(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

}
