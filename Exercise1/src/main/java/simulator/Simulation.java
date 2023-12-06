package simulator;

import java.util.PriorityQueue;
import java.util.Queue;

public abstract class Simulation {
    private int curTime;
    private PriorityQueue<Event> eventQueue;

    public Simulation(){};

    public void simulate(){
        this.initialize();
        this.run();
    }

    public void initialize(){
        this.eventQueue = new PriorityQueue<Event>();

    }

    public void run(){
        this.curTime = 0;
        while(!this.eventQueue.isEmpty()){
            // Get next event, since priority queue it will be the next earliest one
            Event e = this.eventQueue.poll();
            // Set the current time to the time of the next earliest event
            this.curTime = e.getTime();
            this.handleEvent(e);
        }
    }

    public abstract void handleEvent(Event e);
}
