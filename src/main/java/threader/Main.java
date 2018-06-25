package threader;


import threader.task.TaskQueue;
import threader.task.tasks.DirectoryScanTask;

public class Main {
    public static void main(String[] args) {
        new TaskQueue(3, true);
        new DirectoryScanTask("./dropbox",".*\\.json");
    }
}
