package Threader.Task.tasks;

import Threader.Task.Task;

import java.io.File;

public class ProcessFileTask extends Task {
    File fileToProcess;
    ProcessFileTask(File file){
        fileToProcess = file;
    }
    public void run(){
        log.log(Thread.currentThread().getName(),"processing file: " + fileToProcess.toString());
    }
}
