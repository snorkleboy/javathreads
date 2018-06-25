package threader.task;

import threader.Log;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class TaskQueue {
    public final static Queue<Task> queue = new ConcurrentLinkedQueue<Task>();
    private static ConcurrentLinkedQueue<Thread> threadlist = new ConcurrentLinkedQueue<Thread>();
    public static Log log = new Log();
    public static void spinUp(int numThreads, Boolean includeMain){
        int threadsTomake = includeMain? numThreads-1 : numThreads;
        System.out.println(threadsTomake);
        for(int i=0; i<threadsTomake; i++){
            threadlist.add(
                    new Thread(()->{
                        checkTasks();
                    }, "__THREAD_"+i+"_")
            );
        }
        for(Thread thread : threadlist){
            thread.start();
        }

        if (includeMain){
            checkTasks();
        }
    }

    public static Task poll(){
        Task task =  queue.poll();
        if(queue.size() == 0){
            Log.printResults();
        }
        return task;
    }
    public static Boolean hasTasks(){
        return queue.peek() != null;
    }


    public static void add(Task task){
        queue.add(task);
    }
    public static void checkTasks(){
        while(true){
            log.log(Thread.currentThread().getName(),Thread.currentThread().getName() + " poll queue");
            Task task = queue.poll();
            if (task != null){
                try{
                    task.run();
                }catch(NullPointerException e){
                    System.out.println(e);

                    e.printStackTrace();
                }
            }else{
                Task.sleep((int)Math.random()*1000+500);
            }
        }
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
