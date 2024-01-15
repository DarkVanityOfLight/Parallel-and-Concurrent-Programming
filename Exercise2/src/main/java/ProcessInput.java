import java.io.IOException;

public class ProcessInput {
    private int status;
    private boolean isRunning;

    private ThreadedSleeps printer;

    public ProcessInput(){
        status = 0;
    }

    public void process() throws IOException {
        isRunning = true;
        while(isRunning){
           int input = System.in.read();
           if(input == 'q') {
               isRunning = false;
           }else if (Character.isDigit(input)){
               status = Character.getNumericValue(input);

               if(printer.getSleeptime() <= 1300){
                   printer.setSleeptime(printer.getSleeptime() + 200);
               }else {
                   printer.interrupt();
                   this.isRunning = false;
                   break;
               }

           }else {
               continue;
           }
        }
    }

    public boolean isRunning() {
        return isRunning;
    }

    public int getStatus() {
        return status;
    }

    public void setPrinter(ThreadedSleeps printer) {
        this.printer = printer;
    }
}
