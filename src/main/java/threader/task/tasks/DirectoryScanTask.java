package threader.task.tasks;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import threader.task.Task;
import threader.util.FileHelper;

import java.io.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.regex.Pattern;

public class DirectoryScanTask extends Task {
    static Hashtable<File, Boolean> foundFiles = new Hashtable<File,Boolean>();
    String path;
    String regex;
    public DirectoryScanTask(){
        System.out.println("DIRECTORY SCAN TASK CONSTRUCT");
        path = "./dropbox";
        regex = ".*\\.json";
    }
    public DirectoryScanTask(String searchDirectory, String regexin){
        System.out.println("DIRECTORY SCAN TASK CONSTRUCT");

        path = searchDirectory;
        regex= regexin;
    }
    public void run(){
        log.log(Thread.currentThread().getName(),Thread.currentThread().getName() + " search directory");
        log.print();
        File[] files = getFiles();
        if (files.length > 0){
            log.setBatchResultsPrintSize(files.length);
            queueProccessing(files);
        }
        sleep(1000);
        new DirectoryScanTask(path, regex);
    }
    protected File[] getFiles(){
        //make sure directory exists
        File[] files = FileHelper.getDirectory(path).listFiles(new FileFilter(){
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
