package tim.Task.tasks;


import tim.Log;
import tim.Task.Task;
import tim.Task.TaskQueue;

import javax.lang.model.type.NullType;
import java.util.ArrayList;
import java.util.Queue;


interface Thenable<input>{
    public Object run(input input);
}
public class ThenTask<input>{
    protected static Queue<tim.Task.Task> queue = TaskQueue.queue;
    protected static Log log = new Log();
    protected Thenable<input> lam;

    public ThenTask(Thenable lambda){
        setLam(lambda);
    }
    public ThenTask(Thenable lambda, long timeTo){
        setLam(lambda);
    }
    protected void setLam(Thenable lambda){
        lam = lambda;
    }
    public void run(){
        Object previousOut = lam.run(null);

        for(int i =0; i<thenTasks.size();i++){
            previousOut = thenTasks.get(i).run(previousOut);
        }
    }

    ArrayList<Thenable> thenTasks = new ArrayList<Thenable>();
    public ThenTask then(Thenable lambda){
        thenTasks.add(lambda);
        return this;
    }
}
