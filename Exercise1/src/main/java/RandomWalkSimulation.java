import GraphUtil.Graph;
import simulator.Event;
import simulator.Simulation;

import java.util.*;


public class RandomWalkSimulation extends Simulation {
    public static final int processingTime = 5;

    private HashMap<UUID, Stack<Integer>> messagePaths = new HashMap<>();
    private Graph graph;

    @Override
    public void handleEvent(Event e) throws IllegalArgumentException {
        if (e instanceof RandomWalk){
            handleRandomWalkMessage((RandomWalk) e);
        } else if (e instanceof RandomWalkResponseMessage) {
            try {
                handleRandomWalkResponseMessage((RandomWalkResponseMessage) e);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }else{
            throw new IllegalArgumentException();
        }
    }

    private void handleRandomWalkResponseMessage(RandomWalkResponseMessage e) throws Exception {
        if (e.isSend()){
            int transmissionTime = graph.getWeight(e.getSender(), e.getReceiver());
            // Create a recv event after send event + transmission time
            RandomWalkResponseMessage recvM = new RandomWalkResponseMessage(e.getTime() + transmissionTime,
                    e.getReceiver(), e.getSender(), false, e.getMessageId(), e.getEndpoint());
            this.eventQueue.add(recvM);

        }else{
            int thisNode = e.getReceiver();
            UUID id = e.getMessageId();
            Stack<Integer> thisMessagePath = this.messagePaths.get(id);
            if (thisMessagePath.isEmpty()){
                // TODO we are done what do
            }else{
                int next = thisMessagePath.pop();
                RandomWalkResponseMessage sendM = new RandomWalkResponseMessage(e.getTime() + processingTime, next,
                        thisNode, true, e.getMessageId(), e.getEndpoint());
                this.eventQueue.add(sendM);
            }
        }

    }

    private void handleRandomWalkMessage(RandomWalk e) {
    }
}
