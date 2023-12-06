import java.util.UUID;

import simulator.UUIDMessage;

/**
 * Gives a `UUIDMessage` a time-to-live(TTL)
 */
public class RandomWalk extends UUIDMessage {

    /**
     * The current time-to-live(TTL)
     */
    private int ttl;

    /**
     * Constructs a message event with the specified time, receiver, sender, and
     * a flag indicating whether the message is a send or receive event.
     *
     * @param time     the time at which the message event occurs.
     * @param receiver the unique identifier of the receiver or sender.
     * @param sender   the unique identifier of the sender or receiver.
     * @param isSend   a flag indicating whether the message is a send event (true)
     *                 or receive event (false).
     * @param m        a count how many nodes should be visited
     */
    public RandomWalk(int time, int receiver, int sender, boolean isSend, UUID messageId, int m) {
        super(time, receiver, sender, isSend, messageId);
        this.ttl = m;
    }

    /**
     * Gets the current time-to-live(TTL)
     */
    public int getTTL() {
        return this.ttl;
    }
}