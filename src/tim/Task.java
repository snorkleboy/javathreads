package tim;

import java.util.Queue;

interface Lambda{
    Object run(Object thing);
    void run();
}

public class Task implements Runnable{
    protected static Queue<Task> queue = TaskQueue.queue;
    protected Runnable lam;
    public Task(){
    }
    public Task(Lambda lambda){
    }
    public Task(Runnable lambda){
        setLam(lambda);
    }
    protected void setLam(Runnable lambda){
        lam = lambda;
    }
    public void run(){
        lam.run();
    }
}
