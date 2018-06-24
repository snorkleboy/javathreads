package Threader;

import Threader.Task.TaskQueue;
import Threader.Task.tasks.DirectoryScanTask;
import Threader.Task.tasks.QueueCheckTask;

public class Main {

    public static void main(String[] args) {
        new DirectoryScanTask();
        new Thread(new QueueCheckTask(), "__THREAD_1_").start();
        new Thread(new QueueCheckTask(), "__THREAD_2_").start();
        new QueueCheckTask().run();
    }
}

