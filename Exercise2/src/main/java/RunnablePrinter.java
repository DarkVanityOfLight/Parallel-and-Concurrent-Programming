public class RunnablePrinter implements Runnable, ThreadedSleeps {
    private final ProcessInput input;
    private int sleeptime;

    public RunnablePrinter(ProcessInput input, int sleeptime){
        this.input = input;
        this.sleeptime = sleeptime;
    }

    @Override
    public void run(){
        while (input.isRunning() && !Thread.currentThread().isInterrupted()){
            System.out.println(input.getStatus());
            try {
                //noinspection BusyWait
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
