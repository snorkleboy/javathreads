package threader.task.tasks;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import threader.task.Task;

import java.io.*;
import java.util.Hashtable;
import java.util.regex.Pattern;

public class DirectoryScanTask extends Task {
    static Hashtable<Integer, String> foundFiles = new Hashtable<Integer,String>();

    public void run(){
        log.log(Thread.currentThread().getName(),Thread.currentThread().getName() + " search directory");

        File[] files = getFiles("./dropbox",".*\\.json");
        if (files.length > 0){
            queueProccessing(files);
        }
        log.print();
        new DirectoryScanTask();
    }
    protected File[] getFiles(String path, String regex){
        return new java.io.File(path  ).listFiles(new FileFilter(){
            @Override
            public boolean accept(File file) {
                final Pattern p = Pattern.compile(regex);
                return p.matcher(file.getName()).matches();
            }
        });
    }
    protected void queueProccessing(File[] files){
        for (File file: files){
            new ProcessFileTask(file);
        }
    }
}
