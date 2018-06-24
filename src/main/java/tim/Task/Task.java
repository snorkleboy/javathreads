package tim.Task;

import tim.Log;

import java.util.ArrayList;
import java.util.Queue;



public class Task implements Runnable{
    protected static Queue<Task> queue = TaskQueue.queue;
    protected static Log log = new Log();

    protected Runnable lam;
    public long time;

    protected Task(){}
    public Task(Runnable lambda){
        lam = lambda;
        time = System.currentTimeMillis();
    }
    public Task(Runnable lambda, long timeTo){
        lam = lambda;
        time = System.currentTimeMillis() + timeTo;
    }
    public void run(){
        System.out.println("RUN TASK!!!");
        lam.run();
    }


}

