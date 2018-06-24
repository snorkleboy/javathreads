package tim;

import tim.Task.tasks.QueueCheckTask;

public class Threader {
    public void run(){
        Thread t1 = new Thread(new QueueCheckTask(), "__THREAD_1_");
        Thread t2 = new Thread(new QueueCheckTask(), "__THREAD_2_");
        System.out.println("Starting Runnable threads");
        t1.start();
        t2.start();
        System.out.println("MyThreads has been started");
    }
}
