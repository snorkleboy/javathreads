package threader.task.tasks;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import threader.task.Task;

import java.io.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.regex.Pattern;

public class DirectoryScanTask extends Task {
    static Hashtable<File, Boolean> foundFiles = new Hashtable<File,Boolean>();
    public DirectoryScanTask(){
        queue.add(this);
    }
    public void run(){
        log.log(Thread.currentThread().getName(),Thread.currentThread().getName() + " search directory");

        File[] files = getFiles("./dropbox",".*\\.json");
        if (files.length > 0){
            queueProccessing(files);
        }
        sleep(2000);
        log.print();

        new DirectoryScanTask();
    }
    protected File[] getFiles(String path, String regex){
        File[] files =  new java.io.File(path  ).listFiles(new FileFilter(){
            @Override
            public boolean accept(File file) {
                final Pattern p = Pattern.compile(regex);
                return p.matcher(file.getName()).matches();
            }
        });
        ArrayList<File> newfiles = new ArrayList<File>();
        for (File file : files){
            if (!foundFiles.containsKey(file)){
                foundFiles.put(file,true);
                newfiles.add(file);
            }
        }
        return newfiles.toArray(new File[newfiles.size()]);
    }
    protected void queueProccessing(File[] files){
        for (File file: files){
            new ProcessFileTask(file);
        }
    }
}
