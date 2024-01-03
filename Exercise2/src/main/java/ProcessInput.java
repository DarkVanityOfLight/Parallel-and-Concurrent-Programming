import java.io.IOException;

public class ProcessInput {
    private int status;

    public ProcessInput(){
        status = 0;
    }

    public void process() throws IOException {
        boolean flag = true;

        while(flag){
           int input = System.in.read();
           if(input == 'q') {
               flag = false;
           }else if (Character.isDigit(input)){
               status = Character.getNumericValue(input);
           }else {
               continue;
           }
        }
    }
}
