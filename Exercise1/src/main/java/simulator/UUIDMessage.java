package simulator;

import simulator.Message;

import java.util.UUID;


/**
 * A message with a unique identifier
 */
public class UUIDMessage extends Message{
    private UUID messageId;

    /**
     * Constructs a message event with the specified time, receiver, sender, and
     * a flag indicating whether the message is a send or receive event.
     *
     * @param time     the time at which the message event occurs.
     * @param receiver the unique identifier of the receiver or sender.
     * @param sender   the unique identifier of the sender or receiver.
     * @param isSend   a flag indicating whether the message is a send event (true) or receive event (false).
     */
    public UUIDMessage(int time, int receiver, int sender, boolean isSend, UUID messageId) {
        super(time, receiver, sender, isSend);
        this.messageId = messageId;

    }

    public UUID getMessageId() {
        return messageId;
    }
}
