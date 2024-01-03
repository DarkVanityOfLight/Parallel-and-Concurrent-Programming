import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {

        //===============
        //||Exercise 1 ||
        //===============

        // IMPORTANT:
        // THIS IS A MINIGAME, YOU NEED TO HIT YOU INPUT
        // BEFORE System.out.println IS CALLED
        // OR ELSE IT WILL INTERFERE WITH System.in.read

        ProcessInput p = new ProcessInput();

        /* Uncomment to use the Runnable
        RunnablePrinter rp = new RunnablePrinter(p);
        Thread pr = new Thread(rp);
        */

        // Printer using thread
        // Comment when using Runnable
        ThreadPrinter pr = new ThreadPrinter(p);

        p.setPrinter(pr);

        //pr.start();
        //p.process();

        //===============
        //||Exercise 2 ||
        //===============

        AddCallable addCallable = new AddCallable(1001);
        MultiplyCallable multiplyCallable = new MultiplyCallable(1001, 501);

        try(ScheduledThreadPoolExecutor ex = new ScheduledThreadPoolExecutor(2)){
            Future<Integer> addResult = ex.submit(addCallable);
            Future<Integer> multiplyResult = ex.submit(multiplyCallable);

            System.out.println("Addition result: " + addResult.get());
            System.out.println("Multiplication result: " + multiplyResult.get());
        }



        List<AddCallable> addCallableList = new ArrayList<>();
        addCallableList.add(new AddCallable(50));
        addCallableList.add(new AddCallable(60));
        addCallableList.add(new AddCallable(70));
        addCallableList.add(new AddCallable(80));


        try(ExecutorService executor = Executors.newFixedThreadPool(4)){
            List<Future<Integer>> futures = executor.invokeAll(addCallableList);
            for (int i = 0; i < futures.size(); i++) {
                int value = futures.get(i).get();
                System.out.println("Ergebnis für Wert " + (i + 1) + ": " + value);
            }
        }

        addCallableList = new ArrayList<>();
        for(int i = 0; i<4; i++){
            addCallableList.add(new AddCallable(50));
        }

        try(ExecutorService executorService = Executors.newFixedThreadPool(4)){
            Integer result = executorService.invokeAny(addCallableList);
            System.out.println("First to return returned: " + result);
        }


    }
}