package tim.Task;

import tim.Threader;

import java.sql.Time;
import java.util.Queue;



public class Task implements Runnable{
    protected static Queue<Task> queue = TaskQueue.queue;
    protected static Log log = new Log();
    protected Runnable lam;
    public long time;
    public Task(){
        time = System.currentTimeMillis();
    }

    public Task(Runnable lambda){
        setLam(lambda);
        time = System.currentTimeMillis();
    }
    public Task(Runnable lambda, long timeTo){
        setLam(lambda);
        time = System.currentTimeMillis() + timeTo;
    }
    protected void setLam(Runnable lambda){
        lam = lambda;
    }
    public void run(){
        log.log(Thread.currentThread().getName(),"started");
        lam.run();
    }
}

