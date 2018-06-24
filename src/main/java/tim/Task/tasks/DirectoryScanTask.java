package tim.Task.tasks;

import com.fasterxml.jackson.databind.util.JSONPObject;
import tim.Task.Task;

import java.io.*;
import java.util.Hashtable;
import java.util.regex.Pattern;

public class DirectoryScanTask extends Task {
    Hashtable<Integer, String> foundFiles = new Hashtable<Integer,String>();
    public void run(){
        log.log(Thread.currentThread().getName(),Thread.currentThread().getName() + " search directory");
        File[] files = new java.io.File( "./dropbox" ).listFiles(new FileFilter(){
            @Override
            public boolean accept(File file) {
                final Pattern p = Pattern.compile(".*\\.json");
                return p.matcher(file.getName()).matches();
            }
        });

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
                    e.printStackTrace();
                }catch (IOException e) {
                    e.printStackTrace();
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }));
        }

        log.print();
        queue.add(new DirectoryScanTask());

    }
}
