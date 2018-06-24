package tim.tasks;

import tim.Task;

import java.io.File;

public class DirectoryScanTask extends Task {
    public DirectoryScanTask(){
        setLam(()-> {
            String current = "";
            System.out.println("Working Directory = " +
                    System.getProperty("user.dir"));
            File[] files = new java.io.File( "./dropbox" ).listFiles();
            for (File file: files){
                System.out.println("FILE:" + file.toString());

                queue.add(new Task(()->{
                    System.out.println("new task");
                    System.out.println(file.toString());
                }));
            }

            System.out.println("Current dir:"+ "");
            String currentDir = System.getProperty("user.dir");
            System.out.println("Current dir using System:" +currentDir);
        });
    }
}
