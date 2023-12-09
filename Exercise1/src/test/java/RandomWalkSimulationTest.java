import GraphUtil.Graph;
import org.junit.jupiter.api.Test;
import simulator.Event;
import simulator.Simulation;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

// WARNING:
// Don't bother checking this mess,
// this is only to output a single walk starting from 0
// at time 0
class RandomWalkSimulationTest {

    @Test
    void testSingleWalk() throws NoSuchFieldException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {

        RandomWalkSimulation s = new RandomWalkSimulation();

        Field g = RandomWalkSimulation.class.getDeclaredField("graph");
        g.setAccessible(true);

        Field mp = RandomWalkSimulation.class.getDeclaredField("messagePaths");
        mp.setAccessible(true);

        Method grn = RandomWalkSimulation.class.getDeclaredMethod("getRandomNeighbor", int.class);
        grn.setAccessible(true);

        g.set(s, new Graph("src/main/resources/test-graph.txt"));

        UUID id = UUID.randomUUID();

        RandomWalk r = new RandomWalk(0, (Integer) grn.invoke(s, 0), 0, true, id, 5);

        HashMap<UUID, Stack<Integer>> messagePathsValue = (HashMap<UUID, Stack<Integer>>) mp.get(s);
        messagePathsValue.put(id, new Stack<>());

        Field eq = Simulation.class.getDeclaredField("eventQueue");
        eq.setAccessible(true);

        eq.set(s, new PriorityQueue<Event>());
        PriorityQueue<Event> eqOb = (PriorityQueue<Event>) eq.get(s);

        eqOb.add(r);

        Method runMeth = Simulation.class.getDeclaredMethod("run");
        runMeth.setAccessible(true);
        runMeth.invoke(s);

    }
}