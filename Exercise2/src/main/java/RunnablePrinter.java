public class RunnablePrinter implements Runnable {
    private final ProcessInput input;
    private int sleeptime = 500;

    public RunnablePrinter(ProcessInput input){
        this.input = input;
    }

    @Override
    public void run() {
        while(input.isRunning()){
            System.out.println(input.getStatus());

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
