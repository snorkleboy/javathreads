package Threader.Task.tasks;

import Threader.Task.Task;

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
            queue.add(new Task(()->{
                log.log(Thread.currentThread().getName(),Thread.currentThread().getName() + " FILETRIGGER" + file.toString());
                try{
                    BufferedReader in = new BufferedReader(new FileReader(file.toString()));
                    StringBuilder builder = new StringBuilder();
                    while (in.ready()) {
                        builder.append(in.readLine());
                    }
                    System.out.println(builder);
                    Thread.sleep(500);
                }catch (FileNotFoundException e) {
                    String error = "file from ScanDirectory Task not found by ProcessFile";
                    log.log (Thread.currentThread().getName(),error);
                    System.out.println(error);
                    e.printStackTrace();
                }catch (IOException e) {
                    e.printStackTrace();
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }));
        }
    }
}
