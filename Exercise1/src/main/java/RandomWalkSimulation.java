import GraphUtil.Graph;
import simulator.Event;
import simulator.Simulation;
import GraphUtil.Graph;


import GraphUtil.Edge;
import simulator.UUIDMessage;

import java.util.HashMap;
import java.util.Random;
import java.util.Stack;
import java.util.UUID;

public class RandomWalkSimulation extends Simulation {
    public static final int processingTime = 5;

    private HashMap<UUID, Stack<Integer>> messagePaths = new HashMap<>();
    private Graph graph;

    @Override
    public void initialize(){
        super.initialize();

        this.graph = new Graph("src/main/resources/test-graph.txt");
        Random rng = new Random();
        for(int node = 0; node < this.graph.getNumberNodes() - 1; node++){
            UUID id = UUID.randomUUID();
            Stack<Integer> s = new Stack<>();
            this.messagePaths.put(id, s);
            s.push(node);
            int startTime = rng.nextInt(5000);
            RandomWalk message = new RandomWalk(startTime, this.getRandomNeighbor(node), node, true, id, 5);
            this.eventQueue.add(message);
            Main.logger.info("Starting Random walk {} at {}.", id, startTime);
        }

    }

    @Override
    public void handleEvent(Event e) throws IllegalArgumentException {
        Main.logger.debug("Current time is {}", this.getCurrentTime());
        Main.logger.debug("Handling Event {} at {}.", ((UUIDMessage) e).getMessageId(), this.getCurrentTime());
        if (e instanceof RandomWalk) {
            handleRandomWalkMessage((RandomWalk) e);
        } else if (e instanceof RandomWalkResponseMessage) {
            handleRandomWalkResponseMessage((RandomWalkResponseMessage) e);
        }else{
            throw new IllegalArgumentException();
        }
    }

    private void handleRandomWalkResponseMessage(RandomWalkResponseMessage e) {
        if (e.isSend()){
            Main.logger.info("Handling RandomWalkResponse send Event {} from {} to {} at {}",
                    e.getMessageId(), e.getSender(), e.getReceiver(), e.getTime());

            int transmissionTime = graph.getWeight(e.getSender(), e.getReceiver());
            // Create a recv event after send event + transmission time
            RandomWalkResponseMessage recvM = new RandomWalkResponseMessage(e.getTime() + transmissionTime,
                    e.getReceiver(), e.getSender(), false, e.getMessageId(), e.getEndpoint());

            this.eventQueue.add(recvM);

        }else{
            Main.logger.info("Handling RandomWalkResponse receive Event {} from {} to {} at {}",
                    e.getMessageId(), e.getSender(), e.getReceiver(), e.getTime());

            int thisNode = e.getReceiver();
            UUID id = e.getMessageId();
            Stack<Integer> thisMessagePath = this.messagePaths.get(id);
            if (thisMessagePath.isEmpty()){
                System.out.printf("Random walk from %d to %d is completed at %d\n", thisNode, e.getEndpoint(), this.getCurrentTime());
            }else{
                int next = thisMessagePath.pop();
                RandomWalkResponseMessage sendM = new RandomWalkResponseMessage(e.getTime() + processingTime, next,
                        thisNode, true, e.getMessageId(), e.getEndpoint());
                this.eventQueue.add(sendM);
            }
        }

    }

    private void handleRandomWalkMessage(RandomWalk e) {
        if (e.isSend()) {
            Main.logger.info("Handling RandomWalk send Event {} from {} to {} at {}",
                    e.getMessageId(), e.getSender(), e.getReceiver(), e.getTime());
            int transmissionTime = graph.getWeight(e.getSender(), e.getReceiver());

            RandomWalk sendMessage = new RandomWalk(e.getTime() + transmissionTime, e.getReceiver(), e.getSender(),
                    false, e.getMessageId(), e.getTTL());
            this.eventQueue.add(sendMessage);
        } else {
            Main.logger.info("Handling RandomWalk receive Event {} from {} to {} at {}",
                    e.getMessageId(), e.getSender(), e.getReceiver(), e.getTime());

            // Handle receiving a message
            if (e.getTTL() != 0) {
                int thisNode = e.getReceiver();
                Stack<Integer> messagePath = this.messagePaths.get(e.getMessageId());

                messagePath.push(thisNode);

                RandomWalk newMessage = new RandomWalk(e.getTime() + processingTime,
                        getRandomNeighbor(e.getReceiver()), e.getReceiver(),
                        true, e.getMessageId(), e.getTTL() - 1);
                this.eventQueue.add(newMessage);
            } else {
                int thisNode = e.getReceiver();
                UUID id = e.getMessageId();
                Stack<Integer> thisMessagePath = this.messagePaths.get(id);
                int next = thisMessagePath.pop();

                RandomWalkResponseMessage sendM = new RandomWalkResponseMessage(e.getTime() + processingTime, next,
                        thisNode, true, e.getMessageId(), thisNode);
                this.eventQueue.add(sendM);
            }
        }
    }

    private int getRandomNeighbor(int currentNode) {
        Edge[] neighbors = graph.getNeighbors(currentNode);

        int randomNeighbor = new Random().nextInt(neighbors.length);
        return neighbors[randomNeighbor].getNeighbor();

    }
}
