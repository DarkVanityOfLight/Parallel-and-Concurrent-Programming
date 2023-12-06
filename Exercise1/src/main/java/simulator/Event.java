package simulator;

public class Event implements Comparable<Event> {
    private final int time;

    public Event(int time) {
        this.time = time;
    }

    public int compareTo(Event other){
        return Integer.compare(this.getTime(), other.getTime());
    }

    public int getTime() {
        return time;
    }
}
