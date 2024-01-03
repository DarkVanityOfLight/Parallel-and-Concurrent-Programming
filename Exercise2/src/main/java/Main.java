import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ProcessInput p = new ProcessInput();

        // Uncomment to use the Runnable
        /*
        RunnablePrinter pr = new RunnablePrinter(p);
        Thread t = new Thread(pr);
        t.start();
        */

        // Printer using thread
        ThreadPrinter pr = new ThreadPrinter(p);
        pr.start();


        p.process();
    }
}