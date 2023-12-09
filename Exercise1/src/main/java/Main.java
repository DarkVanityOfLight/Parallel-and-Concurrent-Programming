import simulator.Simulation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    public static final Logger logger = LogManager.getLogger("Hi");
    public static void main(String[] args) {

        Simulation s = new RandomWalkSimulation();
        s.simulate();
    }
}