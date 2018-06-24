package tim;
import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
public class Main {

    public static void main(String[] args) {
        Queue<Task> queue = new ConcurrentLinkedQueue<Task>();
        queue.add(new Task());
        queue.add(new Task());
        queue.add(new Task());
        queue.add(new Task());



        Runnable lam =()-> {
            while(true){
                System.out.println("queue loop " + Thread.currentThread().getName() );
                Task task = queue.poll();
                if (task != null){
                    System.out.println("queue loop WORK " + Thread.currentThread().getName() );
                    task.run();
                }else{
                    try{
                        System.out.println("queue loop SLEEP " + Thread.currentThread().getName() );

                        Thread.sleep(5000);
                    }catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread t1 = new Thread(lam, "t1");
        Thread t2 = new Thread(lam, "t2");
        System.out.println("Starting Runnable threads");
        t1.start();
        t2.start();

        System.out.println("MyThreads has been started");

        //init queue(initialTasks)
        //init threads(QueuChecker)


    }
}

