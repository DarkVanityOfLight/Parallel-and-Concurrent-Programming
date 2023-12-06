package simulator;

/**
 * The `Message` class represents a message event that extends the basic functionality
 * of the `Event` class. It includes information about the sender, receiver, and whether
 * the message is a send or receive event.
 */
public class Message extends Event {

    /**
     * The unique identifier of the receiver or sender of the message.
     */
    private int receiver;

    /**
     * The unique identifier of the sender or receiver of the message.
     */
    private int sender;

    /**
     * A flag indicating whether the message is a send event (true) or a receive event (false).
     */
    private boolean isSend;

    /**
     * Constructs a message event with the specified time, receiver, sender, and
     * a flag indicating whether the message is a send or receive event.
     *
     * @param time     the time at which the message event occurs.
     * @param receiver the unique identifier of the receiver or sender.
     * @param sender   the unique identifier of the sender or receiver.
     * @param isSend   a flag indicating whether the message is a send event (true) or receive event (false).
     */
    public Message(int time, int receiver, int sender, boolean isSend) {
        super(time);
        this.receiver = receiver;
        this.sender = sender;
        this.isSend = isSend;
    }

    /**
     * Checks if the message is a send event.
     *
     * @return true if the message is a send event, false if it's a receive event.
     */
    public boolean isSend() {
        return isSend;
    }

    /**
     * Gets the unique identifier of the sender or receiver.
     *
     * @return the sender's or receiver's unique identifier.
     */
    public int getSender() {
        return sender;
    }

    /**
     * Gets the unique identifier of the receiver or sender.
     *
     * @return the receiver's or sender's unique identifier.
     */
    public int getReceiver() {
        return receiver;
    }
}
