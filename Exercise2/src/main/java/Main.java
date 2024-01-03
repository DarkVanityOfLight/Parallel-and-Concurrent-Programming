import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ProcessInput p = new ProcessInput();

        /* Uncomment to use the Runnable
        RunnablePrinter rp = new RunnablePrinter(p);
        Thread pr = new Thread(rp);
        */

        // Printer using thread
        // Comment when using Runnable
        ThreadPrinter pr = new ThreadPrinter(p);

        p.setPrinter(pr);

        pr.start();
        p.process();


        // IMPORTANT:
        // THIS IS A MINIGAME, YOU NEED TO HIT YOU INPUT
        // BEFORE System.out.println IS CALLED
        // OR ELSE IT WILL INTERFERE WITH System.in.read
    }
}