package threader.task;

import threader.Log;

import java.util.Queue;



public class Task implements Runnable{
    protected static TaskQueue queue = new TaskQueue();
    protected static Log log = new Log();

    protected Runnable lam;
    public int time = (int)System.currentTimeMillis();
    public Task(){
        queue.add(this);
    }
    public Task(Runnable lambda){
        lam = lambda;
        queue.add(this);
    }
    public Task(Runnable lambda, int timeTo){
        lam = lambda;
        time = (int)System.currentTimeMillis() + timeTo;
        queue.add(this);
    }
    public void run(){
        lam.run();
    }
    protected static void sleep(int time){
        try{
            Thread.sleep(time);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    protected String getThreadName(){
        return Thread.currentThread().getName();
    }


}

