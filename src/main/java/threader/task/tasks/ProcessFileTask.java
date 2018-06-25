package threader.task.tasks;

import com.fasterxml.jackson.databind.JsonMappingException;
import threader.dataClasss.Data;
import threader.dataClasss.Location;
import threader.task.Task;
import threader.util.FileHelper;

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
            StringBuffer builder = new StringBuffer();
            while (in.ready()) {
                builder.append(in.readLine());
            }
            in.close();
//            System.out.println(builder.toString());
            try{
                Location[] locations = Location.ParseLocations(builder.toString());
                Data[] dataArr = Location.extractData(locations);
                StringBuffer resultBuilder = new StringBuffer();
                for(Data data : dataArr){
                    resultBuilder.append(data.toString() + "\n\n");
                }
                String path = "./processed/" +fileToProcess.getName().replace(".json",".processed");
                FileHelper.WriteFile(path, resultBuilder.toString());
                fileToProcess.renameTo(FileHelper.getFile("./processed/"+fileToProcess.getName()));
                log.logResults(fileToProcess.toString(),dataArr);


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
