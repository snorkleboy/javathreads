package tim;

import tim.tasks.DirectoryScanTask;

public class Main {

    public static void main(String[] args) {

        TaskQueue.queue.add(new DirectoryScanTask());
        new Threader().run();
    }
}

