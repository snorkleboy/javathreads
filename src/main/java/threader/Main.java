package threader;


import threader.task.TaskQueue;
import threader.task.tasks.DirectoryScanTask;

public class Main {
    public static void main(String[] args) {
        new DirectoryScanTask("./dropbox",".*\\.json");
        TaskQueue.spinUp(3, true);
    }
}
