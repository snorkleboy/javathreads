package tim;

import tim.Task.TaskQueue;
import tim.Task.tasks.DirectoryScanTask;
import tim.Task.tasks.QueueCheckTask;

public class Main {

    public static void main(String[] args) {
        TaskQueue.queue.add(new DirectoryScanTask());
        new Thread(new QueueCheckTask(), "__THREAD_1_").start();
        new Thread(new QueueCheckTask(), "__THREAD_2_").start();
        new QueueCheckTask().run();
    }
}

