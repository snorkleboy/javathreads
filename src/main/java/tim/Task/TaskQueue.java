package tim.Task;

import tim.Task.Task;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class TaskQueue {
    public final static Queue<Task> queue = new ConcurrentLinkedQueue<Task>();
}
