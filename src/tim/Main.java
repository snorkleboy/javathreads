package tim;

import tim.Task.TaskQueue;
import tim.Task.tasks.DirectoryScanTask;

public class Main {

    public static void main(String[] args) {

        TaskQueue.queue.add(new DirectoryScanTask());
        new Threader().run();
    }
}

