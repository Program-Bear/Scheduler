package Scheduler;
import test.Process;

public interface Scheduler {
	public Process get_next();
	public Process deque();
	public void enque(Process temp);
	public double start_run(double total_time);
	public boolean finish();
}
