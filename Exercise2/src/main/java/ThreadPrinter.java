public class ThreadPrinter extends Thread{
    private ProcessInput input;
    private int sleeptime = 500;

    public ThreadPrinter(ProcessInput input){
        this.input = input;
    }

    @Override
    public void run(){
        while (input.isRunning()){
            System.out.println(input.getStatus());
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
