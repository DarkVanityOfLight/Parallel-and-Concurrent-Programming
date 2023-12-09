import GraphUtil.Graph;
import simulator.Event;
import simulator.Simulation;


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

        // Start a random walk from every node
        for(int node = 0; node < this.graph.getNumberNodes() - 1; node++){
            // Generate a message id
            UUID id = UUID.randomUUID();

            // Create a new path stack for our message
            Stack<Integer> s = new Stack<>();
            this.messagePaths.put(id, s);
            s.push(node);

            int startTime = rng.nextInt(5000);
            RandomWalkMessage message = new RandomWalkMessage(startTime, this.getRandomNeighbor(node), node, true, id, 5);
            this.eventQueue.add(message);
            Main.logger.info("Starting Random walk {} at {}.", id, startTime);
        }

    }

    @Override
    public void handleEvent(Event e) throws IllegalArgumentException {
        Main.logger.debug("Current time is {}", this.getCurrentTime());
        Main.logger.debug("Handling Event {} at {}.", ((UUIDMessage) e).getMessageId(), this.getCurrentTime());
        // Handle Message depending on Event type
        if (e instanceof RandomWalkMessage) {
            handleRandomWalkMessage((RandomWalkMessage) e);
        } else if (e instanceof RandomWalkResponseMessage) {
            handleRandomWalkResponseMessage((RandomWalkResponseMessage) e);
        }else{
            throw new IllegalArgumentException();
        }
    }

    /**
     * Handle a single RandomWalkResponse message
     * @param e the RandomWalkResponseMessage to handle
     */
    private void handleRandomWalkResponseMessage(RandomWalkResponseMessage e) {
        if (e.isSend()){
            Main.logger.info("Handling RandomWalkResponse send Event {} from {} to {} at {}",
                    e.getMessageId(), e.getSender(), e.getReceiver(), e.getTime());

            // Create a receive message after the transmission delay

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

            // If our stack is empty we are at the start node
            if (thisMessagePath.isEmpty()){
                System.out.printf("Random walk from %d to %d is completed at %d\n", thisNode, e.getEndpoint(), this.getCurrentTime());
            }else{
                // Get the next node to send to
                int next = thisMessagePath.pop();
                RandomWalkResponseMessage sendM = new RandomWalkResponseMessage(e.getTime() + processingTime, next,
                        thisNode, true, e.getMessageId(), e.getEndpoint());
                this.eventQueue.add(sendM);
            }
        }

    }

    private void handleRandomWalkMessage(RandomWalkMessage e) {
        if (e.isSend()) {
            Main.logger.info("Handling RandomWalkMessage send Event {} from {} to {} at {}",
                    e.getMessageId(), e.getSender(), e.getReceiver(), e.getTime());

            // Create a receive event after the transmission time

            int transmissionTime = graph.getWeight(e.getSender(), e.getReceiver());

            RandomWalkMessage sendMessage = new RandomWalkMessage(e.getTime() + transmissionTime, e.getReceiver(), e.getSender(),
                    false, e.getMessageId(), e.getTTL());
            this.eventQueue.add(sendMessage);

        } else {
            Main.logger.info("Handling RandomWalkMessage receive Event {} from {} to {} at {}",
                    e.getMessageId(), e.getSender(), e.getReceiver(), e.getTime());

            // Handle receiving a message
            if (e.getTTL() != 0) {
                // TTL is not yet zero get a new neighbor and the walk to him
                int thisNode = e.getReceiver();

                // Add this node to the path so we know where to walk on our way back
                Stack<Integer> messagePath = this.messagePaths.get(e.getMessageId());
                messagePath.push(thisNode);

                RandomWalkMessage newMessage = new RandomWalkMessage(e.getTime() + processingTime,
                        getRandomNeighbor(e.getReceiver()), e.getReceiver(),
                        true, e.getMessageId(), e.getTTL() - 1);
                this.eventQueue.add(newMessage);

            } else {
                //TTL is zero send a response message back the same path
                int thisNode = e.getReceiver();
                UUID id = e.getMessageId();
                Stack<Integer> thisMessagePath = this.messagePaths.get(id);

                // Get the last node on our stack
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
