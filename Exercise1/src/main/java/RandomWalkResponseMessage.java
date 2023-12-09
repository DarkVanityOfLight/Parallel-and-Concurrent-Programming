import simulator.UUIDMessage;

import java.util.UUID;

public class RandomWalkResponseMessage extends UUIDMessage {
    private int endpoint;


    /**
     * Constructs a message event with the specified time, receiver, sender, and
     * a flag indicating whether the message is a send or receive event.
     *
     * @param time      the time at which the message event occurs.
     * @param receiver  the unique identifier of the receiver or sender.
     * @param sender    the unique identifier of the sender or receiver.
     * @param isSend    a flag indicating whether the message is a send event (true) or receive event (false).
     * @param messageId
     */
    public RandomWalkResponseMessage(int time, int receiver, int sender, boolean isSend, UUID messageId, int endpoint) {
        super(time, receiver, sender, isSend, messageId);
        this.endpoint = endpoint;
    }

    /**
     * Construct a RandomWalkResponse, from a previous response message
     * this will always set isSend to true, get the current node(the sender) to the old receiver
     * of the original message.
     * @param time
     * @param receiver
     * @param message
     */
    public RandomWalkResponseMessage(int time, int receiver, RandomWalkResponseMessage message){
        super(time, receiver, message.getReceiver(), true, message.getMessageId());
        this.endpoint = message.getEndpoint();
    }


    /**
     * Get the endpoint of the original RandomWalkMessage address
     * @return the endpoint
     */
    public int getEndpoint() {
        return endpoint;
    }
}
