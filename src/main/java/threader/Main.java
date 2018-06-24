package threader;

import threader.task.tasks.DirectoryScanTask;
import threader.task.tasks.MakeJsonTask;
import threader.task.tasks.QueueCheckTask;

public class Main {
    public static void main(String[] args) {
        new DirectoryScanTask("./dropbox",".*\\.json");
        new MakeJsonTask("./created/locations2.json",10);
        new MakeJsonTask("./created/locations3.json",100);
        new MakeJsonTask("./created/locations4.json",1000);
        new MakeJsonTask("./created/locations5.json",10000);

        new MakeJsonTask("./created/locations2b.json",10);
        new MakeJsonTask("./created/locations3b.json",100);
        new MakeJsonTask("./created/locations4b.json",1000);
        new MakeJsonTask("./created/locations5b.json",10000);

        new MakeJsonTask("./created/locations2c.json",10);
        new MakeJsonTask("./created/locations3c.json",100);
        new MakeJsonTask("./created/locations4c.json",1000);
        new MakeJsonTask("./created/locations5c.json",10000);

        new Thread(new QueueCheckTask(), "__THREAD_1_").start();
        new Thread(new QueueCheckTask(), "__THREAD_2_").start();
        new QueueCheckTask().run();
    }
}

