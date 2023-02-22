package model.eventlogging;

import java.util.Calendar;
import java.util.Date;

public class Event {
    private static final int HASH_CONSTANT = 13;
    private String description;
    private Date dateLogged;

    //Creates an event with the given description and the current date and time
    public Event(String description) {
        dateLogged = Calendar.getInstance().getTime();
        this.description = description;
    }

    //EFFECTS: get the date and time of this event
    public Date getDate() {
        return dateLogged;
    }

    //EFFECTS: get the description of this event
    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        if (other.getClass() != this.getClass()) {
            return false;
        }

        Event otherEvent = (Event) other;

        return (this.dateLogged.equals(otherEvent.dateLogged)
                && this.description.equals(otherEvent.description));
    }

    @Override
    public int hashCode() {
        return (HASH_CONSTANT * dateLogged.hashCode() + description.hashCode());
    }

    @Override
    public String toString() {
        return dateLogged.toString() + "\n" + description;
    }
}
