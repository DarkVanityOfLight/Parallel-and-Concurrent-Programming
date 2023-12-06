package simulator;

import java.util.UUID;

public class RandomWalk extends UUIDMessage {

    private int ttl;

    public RandomWalk(int time, int receiver, int sender, boolean isSend, UUID messageId, int m) {
        super(time, receiver, sender, isSend, messageId);
        this.ttl = m;
    }

    public int getTTL() {
        return this.ttl;
    }
}