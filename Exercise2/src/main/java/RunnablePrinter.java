public class RunnablePrinter implements Runnable, ThreadedSleeps {
    private final ProcessInput input;
    private int sleeptime = 500;

    public RunnablePrinter(ProcessInput input){
        this.input = input;
    }

    @Override
    public void run(){
        while (input.isRunning()){
            System.out.println(input.getStatus());
            try {
                Thread.sleep(sleeptime);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    @Override
    public void setSleeptime(int sleeptime) {
        this.sleeptime = sleeptime;
    }

    @Override
    public int getSleeptime() {
        return this.sleeptime;
    }

    @Override
    public void interrupt() {
        Thread.currentThread().interrupt();
    }
}
