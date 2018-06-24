package tim.tasks;

import tim.Task;
import tim.TaskQueue;

import java.util.Queue;

public class QueueCheckTask extends Task {
    public QueueCheckTask(){
        setLam(()-> {
            while(true){
                System.out.println("queue loop " + Thread.currentThread().getName() );
                Task task = queue.poll();
                if (task != null){
                    System.out.println("queue loop WORK " + Thread.currentThread().getName() );
                    task.run();
                }else{
                    try{
                        System.out.println("queue loop SLEEP " + Thread.currentThread().getName() );

                        Thread.sleep(5000);
                    }catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

}
