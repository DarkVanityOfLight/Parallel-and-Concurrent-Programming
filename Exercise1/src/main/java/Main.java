import simulator.Simulation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    public static final Logger logger = LogManager.getLogger("Hi");
    public static void main(String[] args) {

        // Provide the wanted Time to Live m as a parameter
        Simulation s = new RandomWalkSimulation(5);
        s.simulate();
    }
}