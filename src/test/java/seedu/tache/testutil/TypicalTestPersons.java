package seedu.tache.testutil;

import seedu.tache.commons.exceptions.IllegalValueException;
import seedu.tache.model.TaskManager;
import seedu.tache.model.person.Task;
import seedu.tache.model.person.UniqueTaskList;

/**
 *
 */
public class TypicalTestPersons {

    public TestPerson alice, benson, carl, daniel, elle, fiona, george, hoon, ida;

    public TypicalTestPersons() {
        try {
            alice = new PersonBuilder().withName("Alice Pauline")
                    .withTags("friends").build();
            benson = new PersonBuilder().withName("Benson Meier")
                    .withTags("owesMoney", "friends").build();
            carl = new PersonBuilder().withName("Carl Kurz").build();
            daniel = new PersonBuilder().withName("Daniel Meier").build();
            elle = new PersonBuilder().withName("Elle Meyer").build();
            fiona = new PersonBuilder().withName("Fiona Kunz").build();
            george = new PersonBuilder().withName("George Best").build();

            // Manually added
            hoon = new PersonBuilder().withName("Hoon Meier").build();
            ida = new PersonBuilder().withName("Ida Mueller").build();
        } catch (IllegalValueException e) {
            e.printStackTrace();
            assert false : "not possible";
        }
    }

    public static void loadAddressBookWithSampleData(TaskManager ab) {
        for (TestPerson person : new TypicalTestPersons().getTypicalPersons()) {
            try {
                ab.addTask(new Task(person));
            } catch (UniqueTaskList.DuplicateTaskException e) {
                assert false : "not possible";
            }
        }
    }

    public TestPerson[] getTypicalPersons() {
        return new TestPerson[]{alice, benson, carl, daniel, elle, fiona, george};
    }

    public TaskManager getTypicalAddressBook() {
        TaskManager ab = new TaskManager();
        loadAddressBookWithSampleData(ab);
        return ab;
    }
}