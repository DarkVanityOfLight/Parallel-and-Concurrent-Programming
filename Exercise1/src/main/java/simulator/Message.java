package simulator;

public class Message extends Event{

    private int receiver;
    private int sender;
    private boolean isSend;

    public Message(int time, int receiver, int sender, boolean isSend) {
        super(time);
        this.receiver = receiver;
        this.sender = sender;
        this.isSend = isSend;
    }

    public boolean isSend() {
        return isSend;
    }

    public int getSender() {
        return sender;
    }

    public int getReceiver() {
        return receiver;
    }
}
