import java.io.IOException;

public class ProcessInput {
    private int status;
    private boolean isRunning;

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
}
