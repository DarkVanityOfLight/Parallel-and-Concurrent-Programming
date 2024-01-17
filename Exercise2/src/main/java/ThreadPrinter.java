public class ThreadPrinter extends Thread implements ThreadedSleeps {
    private final ProcessInput input;
    private int sleeptime;

    public ThreadPrinter(ProcessInput input, int sleeptime){
        this.input = input;
        this.sleeptime = sleeptime;
    }

    @Override
    public void run(){
        while (input.isRunning() && !this.isInterrupted()){
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
        return sleeptime;
    }
}
