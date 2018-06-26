package threader.task;

import threader.Log;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

public class TaskQueue {
    public final static ArrayBlockingQueue<Task> queue = new ArrayBlockingQueue<Task>(20);
    private static ConcurrentLinkedQueue<Thread> waitlist = new ConcurrentLinkedQueue<Thread>();
//    private static ConcurrentLinkedQueue<Thread> threadlist = new ConcurrentLinkedQueue<Thread>();

    public static Log log = new Log();
    public static void spinUp(int numThreads, Boolean includeMain){
        int threadsTomake = includeMain? numThreads-1 : numThreads;
        Thread[] threadlist = new Thread[numThreads];
        for(int i=0; i<threadsTomake; i++){
            threadlist[i] = new Thread(()->{checkTasks();}, "__THREAD_"+i+"_");
            threadlist[i].start();
        }

        if (includeMain){
            checkTasks();
        }
    }
    public static Task poll() throws InterruptedException{
//        Task task =  queue.poll();
//        return task;
        return queue.poll(5l, TimeUnit.SECONDS);
    }
    public static Boolean hasTasks(){
        return queue.peek() != null;
    }
    public static void add(Task task){
        queue.add(task);
//        Thread thread = waitlist.poll();
//        System.out.println("POLL THREADS   " + thread==null  );
//        if(thread != null){
//            synchronized (thread){
//                thread.notify();
//            }
//        }
    }
    public static void checkTasks(){
        while(true){
            log.log(Thread.currentThread().getName(),Thread.currentThread().getName() + " poll queue");

            Task task = queue.poll();
            if (task != null){
                System.out.println(Thread.currentThread().getName()+" FOUNT TASK  " + task.getClass());

                try{
                    task.run();
                }catch(NullPointerException e){
                    System.out.println(e);
                    e.printStackTrace();
                }
            }else{
                Task.sleep(1500);
//                log.print();

//                System.out.println(Thread.currentThread().getName() + " poll queue     waiting:");
//                try{
//                    Thread thread = Thread.currentThread();
//                    synchronized (thread){
//                        waitlist.add(Thread.currentThread());
//                        log.log(thread.getName(),Thread.currentThread().getName() + " wait indef");
//                        System.out.println(thread.getName()+" wait indef");
//                        while(true){
//                            thread.wait();
//                        }
//                    }
//                }catch (InterruptedException e){
//                    System.out.println(Thread.currentThread().getName()+" AWOKEN");
//                    checkTasks();
//                }
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
