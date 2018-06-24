package threader.task;

import threader.Log;

import java.util.Queue;



public class Task implements Runnable{
    protected static Queue<Task> queue = TaskQueue.queue;
    protected static Log log = new Log();

    protected Runnable lam;
    public long time;

    protected Task(){
        queue.add(this);
    }
    public Task(Runnable lambda){
        lam = lambda;
        time = System.currentTimeMillis();
        queue.add(this);
    }
    public Task(Runnable lambda, long timeTo){
        lam = lambda;
        time = System.currentTimeMillis() + timeTo;
        queue.add(this);
    }
    public void run(){
        lam.run();
    }
    protected void sleep(int time){
        try{
            Thread.sleep(time);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}

