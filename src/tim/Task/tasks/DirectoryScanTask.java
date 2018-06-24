package tim.Task.tasks;

import tim.Task.Task;

import java.io.File;
import java.io.FileFilter;
import java.util.Hashtable;
import java.util.regex.Pattern;

public class DirectoryScanTask extends Task {
    Hashtable<Integer, String> foundFiles = new Hashtable<Integer,String>();
    public DirectoryScanTask(){
        setLam(()-> {
            log.log(Thread.currentThread().getName(),Thread.currentThread().getName() + "search directory");
            File[] files = new java.io.File( "./dropbox" ).listFiles(new FileFilter(){
                @Override
                public boolean accept(File file) {
                    final Pattern p = Pattern.compile(".*\\.json");
                    return p.matcher(file.getName()).matches();
                }
            });

            for (File file: files){
                System.out.println("FILE:" + file.toString());
                queue.add(new Task(()->{
                    log.log(Thread.currentThread().getName(),Thread.currentThread().getName() + "NEW TASK WORKING  -triggered by" + file.toString());
                    try{
                        Thread.sleep(3000);
                    }catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }));
            }

            log.print();
            queue.add(new DirectoryScanTask());

        });
    }
}
