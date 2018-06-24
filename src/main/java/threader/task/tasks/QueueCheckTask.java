package threader.task.tasks;

import threader.task.Task;

public class QueueCheckTask extends Task {
    public void run(){
        while(true){
            log.log(Thread.currentThread().getName(),Thread.currentThread().getName() + " poll queue");
            Task task = queue.poll();
            if (task != null){
                task.run();
            }else{
                sleep((int)Math.random()*1000+500);
            }
        }
    }

}
