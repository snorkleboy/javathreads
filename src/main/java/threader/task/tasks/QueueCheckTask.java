package threader.task.tasks;

import threader.task.Task;

public class QueueCheckTask extends Task {
    public QueueCheckTask(Boolean enqueue){

    }
    public void run(){
        runStatic();
    }
    public static void runStatic(){
        while(true){
            log.log(Thread.currentThread().getName(),Thread.currentThread().getName() + " poll queue");
            Task task = queue.poll();
            if (task != null){
                try{
                    task.run();
                }catch(NullPointerException e){
                    System.out.println(e);

                    e.printStackTrace();
                }
            }else{
                sleep((int)Math.random()*1000+500);
            }
        }
    }

}
