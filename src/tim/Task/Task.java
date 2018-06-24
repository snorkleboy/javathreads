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
        lam.run();
    }

    ArrayList<Task> thenTasks = new ArrayList<Task>();
    public Task then(Runnable lambda){
        thenTasks.add(new Task(lambda));
        return this;
    }
    public Task then(Task task){
        thenTasks.add(task);
        return this;
    }
}

