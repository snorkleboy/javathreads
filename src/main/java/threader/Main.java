package threader;
import threader.task.tasks.DirectoryScanTask;
import threader.task.tasks.MakeJsonTask;
import threader.task.tasks.QueueCheckTask;

public class Main {
    public static void main(String[] args) {
        new DirectoryScanTask("./dropbox",".*\\.json");
        new Thread(new QueueCheckTask(false), "__THREAD_1_").start();
        new Thread(new QueueCheckTask(false), "__THREAD_2_").start();
        QueueCheckTask.runStatic();;
    }
}

