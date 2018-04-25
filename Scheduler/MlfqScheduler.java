package Scheduler;
import java.util.*;
import test.Process;
public class MlfqScheduler implements Scheduler{
	FifoScheduler s1;
	FifoScheduler s2;
	RRScheduler s3;
	double s1t,s2t,s3t;
	Random rand = new Random();
	MlfqScheduler(){
		
	}
	
	public MlfqScheduler(ArrayList<Process> proc_array, double t1, double t2, double t3, double tr3){
		s1 = new FifoScheduler(proc_array);
		s2 = new FifoScheduler(new ArrayList<Process>());
		s3 = new RRScheduler(new ArrayList<Process>(), tr3);
		s1t = t1;
		s2t = t2;
		s3t = t3;
	}
	
	@Override
	public Process get_next() {
		
		return null;
	}
	@Override
	public Process deque() {
	
		return null;
	}
	@Override
	public void enque(Process temp) {
		//System.out.println("Enque of "+ temp.id);
		s1.enque(temp);
		
	}
	public void enque_list(ArrayList<Process> store) {
		//System.out.println("Enque list of"+ store.size());
		for (int i = 0; i < store.size(); i++) {
			enque(store.get(i));
		}
	}
	@Override
	public double start_run(double total_time) {
		double run_time = 0;
		while(!finish() && total_time > 0) {
			if (total_time < s1t) break;
			double s1_time = s1.start_run(s1t);
			//assert(s1_time == s1t);
			run_time += s1_time;
			total_time -= s1_time;
			
			if (!s1.proc_finish) {
				Process temp = s1.pro_que.poll();
				if (temp != null) {
					System.out.println("Move Process: " + temp.id + " from Level 1 to Level 2");
					s2.pro_que.add(temp);
				}
			}
			
			if (total_time < s2t) break;
			double s2_time = s2.start_run(s2t);
			//assert(s2_time == s2t);
			run_time += s2_time;
			total_time -= s2_time;
			
			if (!s2.proc_finish) {
				Process temp = s2.pro_que.poll();
				if (temp != null) {
					System.out.println("Move Process: " + temp.id + " from Level 2 to Level 3");
					s3.pro_que.add(temp);
				}
			}
			
			if (rand.nextInt() % 2 == 0) {
				double s3_time = s3.start_run(s3t);
				run_time += s3_time;
				total_time -= s3_time;
			}
			
			
		}
		
		return 0;
	}

	@Override
	public boolean finish() {
		return s1.finish() && s2.finish() && s3.finish();
	}
	
}
