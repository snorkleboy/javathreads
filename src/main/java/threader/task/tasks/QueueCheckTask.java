package threader.task.tasks;

import threader.task.Task;

public class QueueCheckTask extends Task {
    public void run(){
        while(true){
            log.log(Thread.currentThread().getName(),Thread.currentThread().getName() + " poll queue");
            Task task = queue.poll();
            if (task != null){
                try{
                    task.run();
                }catch(NullPointerException e){
                    System.out.println(e);

                    System.out.println(e.getStackTrace().toString());
                }
            }else{
                sleep((int)Math.random()*1000+500);
            }
        }
    }

}
