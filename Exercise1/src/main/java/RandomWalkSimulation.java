import simulator.Event;
import simulator.Simulation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;

public class RandomWalkSimulation extends Simulation {
    private HashMap<UUID, ArrayList<Integer>> messagePaths = new HashMap<>();

    @Override
    public void handleEvent(Event e) throws IllegalArgumentException {
        if (e instanceof RandomWalk){
            handleRandomWalkMessage((RandomWalk) e);
        } else if (e instanceof RandomWalkResponseMessage) {
            handleRandomWalkResponseMessage((RandomWalkResponseMessage) e);
        }else{
            throw new IllegalArgumentException();
        }
    }

    private void handleRandomWalkResponseMessage(RandomWalkResponseMessage e) {
    }

    private void handleRandomWalkMessage(RandomWalk e) {
    }
}
