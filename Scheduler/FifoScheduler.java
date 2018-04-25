package Scheduler;
import java.util.*;
import test.Process;
public class FifoScheduler implements Scheduler{
	Deque <Process> pro_que = new LinkedList<Process>();
	boolean proc_finish = false;
	public FifoScheduler(ArrayList<Process> pro_array){
		for (int i = 0; i < pro_array.size(); i++) {
			enque(pro_array.get(i));
		}
	}
	
	@Override
	public Process get_next() {
		return pro_que.peek();
	}
	@Override
	public Process deque() {
		// TODO Auto-generated method stub
		return pro_que.poll();
	}

	@Override
	public void enque(Process temp) {
		// TODO Auto-generated method stub
		pro_que.add(temp);
	}
	
	@Override
	public double start_run(double total_time) {
		proc_finish = false;
		double run_time = 0;
		while(!pro_que.isEmpty() && total_time > 0) {
			Process now_pro = get_next();
			double remain_time = now_pro.get_remain();
			double real_time = remain_time;
			if (remain_time <= total_time) {
				now_pro.Run(remain_time);
				proc_finish = true;
				deque();
			}else {
				real_time = total_time;
				now_pro.Run(real_time);
			}
			run_time += real_time;
			total_time -= real_time;
		}
		return run_time;
	}

	@Override
	public boolean finish() {
		return pro_que.isEmpty();
	}

}
