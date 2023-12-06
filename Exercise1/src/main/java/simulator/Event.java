package simulator;

/**
 * The `Event` class represents an event with a specific time.
 * Events can be compared based on their time for sorting purposes.
 * This class implements the Comparable interface to enable natural ordering of events.
 *
 */
public class Event implements Comparable<Event> {

    /**
     * The time at which the event occurs.
     */
    private final int time;

    /**
     * Constructs an event with the specified time.
     *
     * @param time the time at which the event occurs.
     */
    public Event(int time) {
        this.time = time;
    }

    /**
     * Compares this event with another event based on their times.
     *
     * @param other the event to be compared.
     * @return a negative integer, zero, or a positive integer if this event is
     *         less than, equal to, or greater than the specified event.
     */
    @Override
    public int compareTo(Event other){
        return Integer.compare(this.getTime(), other.getTime());
    }

    /**
     * Gets the time at which the event occurs.
     *
     * @return the time of the event.
     */
    public int getTime() {
        return time;
    }
}
