import simulator.Event;
import simulator.Simulation;
import GraphUtil.Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.UUID;

import GraphUtil.Edge;

public class RandomWalkSimulation extends Simulation {
    private HashMap<UUID, ArrayList<Integer>> messagePaths = new HashMap<>();
    private Graph graph;

    @Override
    public void handleEvent(Event e) throws IllegalArgumentException {
        if (e instanceof RandomWalk) {
            handleRandomWalkMessage((RandomWalk) e);
        } else if (e instanceof RandomWalkResponseMessage) {
            handleRandomWalkResponseMessage((RandomWalkResponseMessage) e);
        } else {
            throw new IllegalArgumentException();
        }
    }

    private void handleRandomWalkResponseMessage(RandomWalkResponseMessage e) {
    }

    private void handleRandomWalkMessage(RandomWalk e) {
        if (e.isSend()) {
            int transmissionTime = graph.getWeight(e.getSender(), e.getReceiver());

            RandomWalk sendMessage = new RandomWalk(e.getTime() + transmissionTime, e.getReceiver(), e.getSender(),
                    false, e.getMessageId(), e.getTTL());
            this.eventQueue.add(sendMessage);
        } else {
            if (e.getTTL() != 0) {
                RandomWalk newMessage = new RandomWalk(e.getTime() + 5,
                        getRandomNeighbor(e.getReceiver()), e.getReceiver(),
                        true, e.getMessageId(), e.getTTL());
                this.eventQueue.add(newMessage);
            } else {

            }
        }
    }

    private int getRandomNeighbor(int currentNode) {
        Edge[] neighbors = graph.getNeighbors(currentNode);

        int randomNeighbor = new Random().nextInt(neighbors.length);
        return neighbors[randomNeighbor].getNeighbor();

    }
}
