package Threader.Task.tasks;

import Threader.Task.Task;

public class QueueCheckTask extends Task {
    //onyl task that doesnt put itself on the queue
    public QueueCheckTask(){

    }
    public void run(){
        while(true){
            log.log(Thread.currentThread().getName(),Thread.currentThread().getName() + " poll queue");
            Task task = queue.poll();
            if (task != null){
                task.run();
            }else{
                sleep(1000);
            }
        }
    }

}
