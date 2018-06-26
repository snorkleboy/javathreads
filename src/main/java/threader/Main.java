package threader;


import threader.task.Task;
import threader.task.TaskQueue;
import threader.task.tasks.DirectoryScanTask;
import threader.task.tasks.MakeJsonTask;

public class Main {
    public static void main(String[] args) {
        Task[] initial = {
                new DirectoryScanTask("./dropbox",".*\\.json")
        };
        TaskQueue.spinUp(3, true,initial);
    }
}
