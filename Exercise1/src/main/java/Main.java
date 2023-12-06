import GraphUtil.Graph;
import GraphUtil.Edge;
import simulator.Simulation;

public class Main {
    public static void main(String[] args) {

        Simulation s = new RandomWalkSimulation();
        s.simulate();
    }
}