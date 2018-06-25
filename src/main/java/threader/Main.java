package threader;
import threader.task.tasks.DirectoryScanTask;
import threader.task.tasks.QueueCheckTask;

public class Main {
    public static void main(String[] args) {
        new DirectoryScanTask("./dropbox",".*\\.json");
        new Thread(new QueueCheckTask(false), "__THREAD_1_").start();
        new Thread(new QueueCheckTask(false), "__THREAD_2_").start();
        new QueueCheckTask(false).run();
    }
}

