package simulator;

import java.util.PriorityQueue;

/**
 * The abstract `Simulation` class provides a framework for simulating events
 * in a discrete-event simulation. It includes functionality for initializing
 * the simulation, running the simulation, and handling events.
 * Subclasses must implement the specific logic for handling events.
 */
public abstract class Simulation {

    /**
     * The current simulated time in the simulation.
     */
    private int currentTime;

    /**
     * The priority queue to store and manage events in chronological order.
     */
    private PriorityQueue<Event> eventQueue;

    /**
     * Default constructor for the simulation.
     */
    public Simulation() {}

    /**
     * Initiates the simulation by initializing the event queue.
     */
    public void initialize() {
        this.eventQueue = new PriorityQueue<Event>();
    }

    /**
     * Executes the simulation by initializing it and running it.
     */
    public void simulate() {
        this.initialize();
        this.run();
    }

    /**
     * Run the simulation from time 0
     */
    public void run() {
        this.currentTime = 0;
        while (!this.eventQueue.isEmpty()) {
            // Get the next event, since it's a priority queue, it will be the next earliest one
            Event e = this.eventQueue.poll();
            // Set the current time to the time of the next earliest event
            this.currentTime = e.getTime();
            this.handleEvent(e);
        }
    }

    /**
     * Abstract method that must be implemented by subclasses to handle specific event logic.
     *
     * @param e The event to be handled.
     */
    public abstract void handleEvent(Event e);
}
