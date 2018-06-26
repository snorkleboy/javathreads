package threader.task;

import threader.Log;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class TaskQueue {
    public final static Queue<Task> queue = new ConcurrentLinkedQueue<Task>();
    private static ConcurrentLinkedQueue<Thread> threadlist = new ConcurrentLinkedQueue<Thread>();
    private static ConcurrentLinkedQueue<Thread> waitlist = new ConcurrentLinkedQueue<Thread>();

    public static Log log = new Log();
    public static void spinUp(int numThreads, Boolean includeMain, Task[] intialTasks){
        int threadsTomake = includeMain? numThreads-1 : numThreads;
        for(Task task: intialTasks){
            queue.add(task);
        }
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
        return task;
    }
    public static Boolean hasTasks(){
        return queue.peek() != null;
    }
    public static void add(Task task){
        queue.add(task);
        Thread thread = waitlist.poll() ;
        if(thread != null){
            synchronized (thread){
                thread.notify();
            }
        }

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
                System.out.println(Thread.currentThread().getName() + " poll queue     waiting:");
                try{
                    Thread thread = Thread.currentThread();
                    synchronized (thread){
                        waitlist.add(Thread.currentThread());
                        log.log(thread.getName(),Thread.currentThread().getName() + " wait indef");
                        while(true){
                            System.out.println(thread.getName()+" wait indef");
                            thread.wait();
                        }
                    }
                }catch (InterruptedException e){
                    System.out.println(Thread.currentThread().getName()+" AWOKEN");
                    checkTasks();
                }
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
