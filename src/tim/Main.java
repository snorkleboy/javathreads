package tim;

import tim.Task.TaskQueue;
import tim.Task.tasks.DirectoryScanTask;
import tim.Task.tasks.QueueCheckTask;

public class Main {

    public static void main(String[] args) {

        TaskQueue.queue.add(new DirectoryScanTask());
        new Threader().run();
        new QueueCheckTask().run();
    }
}

