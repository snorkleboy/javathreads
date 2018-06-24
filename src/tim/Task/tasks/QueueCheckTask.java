package tim.Task.tasks;

import tim.Task.Task;

public class QueueCheckTask extends Task {
    public QueueCheckTask(){
        setLam(()-> {
            while(true){
                log.log(Thread.currentThread().getName(),"poll queue");
                Task task = queue.poll();
                if (task != null){
                    task.run();
                }else{
                    try{
                        Thread.sleep(5000);
                    }catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

}
