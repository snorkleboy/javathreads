package threader.task.tasks;

import com.fasterxml.jackson.databind.JsonMappingException;
import threader.dataClasss.Data;
import threader.dataClasss.Location;
import threader.task.Task;

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
                Data[] dataArr = Location.extractData(locations);
                for (Data data : dataArr){
                    System.out.println(data.toString());
                }


            }catch (JsonMappingException e){
                System.out.println("JSON MAP ERROR -" +fileToProcess.toString() + "    " + e.getMessage());
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
