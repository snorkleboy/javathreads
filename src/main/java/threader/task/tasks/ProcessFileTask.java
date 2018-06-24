package threader.task.tasks;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import threader.dataClasss.Location;
import threader.task.Task;
import threader.task.thens.Thenable;

import java.io.*;

public class ProcessFileTask extends Task {
    File fileToProcess;
    ProcessFileTask(File file){
        fileToProcess = file;
        queue.add(this);
    }

    public void run(){
        log.log(Thread.currentThread().getName(),Thread.currentThread().getName() + " FILETRIGGER" + fileToProcess.toString());
        try{
            BufferedReader in = new BufferedReader(new FileReader(fileToProcess.toString()));
            StringBuilder builder = new StringBuilder();
            while (in.ready()) {
                builder.append(in.readLine());
            }
//            System.out.println(builder.toString());
            try{
                Location[] locations = Location.ParseLocations(builder.toString());
                System.out.println(locations.toString());

            }catch (JsonMappingException e){
                System.out.println(e);
            }
            sleep(2000);
        }catch (FileNotFoundException e) {
            String error = "file from ScanDirectory task not found by ProcessFile";
            log.log (Thread.currentThread().getName(),error);
            System.out.println(error);
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }

    }

}
