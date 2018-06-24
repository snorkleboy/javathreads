package threader.task;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class TaskQueue {
    public final static Queue<Task> queue = new ConcurrentLinkedQueue<Task>();

    public static Task poll(){
        Task task;
        synchronized (queue){
            task = queue.poll();
            if (task != null){
            }
        }
        return task;
    }

    public static void add(Task task){
        queue.add(task);
    }

    public static void print (){
        System.out.println("+++++++++");
        System.out.println("task queue");
        for (Task taske : queue){
            System.out.println(taske.getClass());
        }
        System.out.println("++++++++");
    }
}
