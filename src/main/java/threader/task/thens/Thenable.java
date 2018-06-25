package threader.task.thens;

public interface Thenable<input>{
    public Object run(input input);
    public void then(Thenable lambda);
}
