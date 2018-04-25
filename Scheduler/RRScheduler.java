package Scheduler;
import java.util.*;

import test.Process;

public class RRScheduler implements Scheduler{
	Deque<Process> pro_que = new ArrayDeque<Process>();
	double slice = 2.0;
	public boolean proc_finish = true;
	public RRScheduler() {
		
	}
	public RRScheduler(ArrayList<Process> pro_array, double s){
		for (int i = 0; i < pro_array.size(); i++) {
			enque(pro_array.get(i));
		}
		slice = s;
	}
	@Override
	public Process get_next() {
		// TODO Auto-generated method stub
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
		proc_finish = true;
		double run_time = 0;
		while(total_time > 0 && !pro_que.isEmpty()) {
			Process now_pro = pro_que.peek();
			System.out.print("RR Scheduler pick out Process " + now_pro.id + " to Run");
			double remain_time = now_pro.get_remain();
			double real_time = remain_time;
			if (total_time < remain_time) {
				if (total_time < slice) {
					now_pro.Run(total_time);
					real_time = total_time;
				}else {
					now_pro.Run(slice);
					real_time = slice;
					deque();
					enque(now_pro);
				}
			}else {
				if (remain_time < slice) {
					now_pro.Run(remain_time);
					deque();
					real_time = remain_time;
				}else {
					now_pro.Run(slice);
					deque();
					enque(now_pro);
					real_time = slice;
				}
			}
			total_time -= real_time;
			run_time += real_time;
		}
		return run_time;
	}
	@Override
	public boolean finish() {
		return pro_que.isEmpty();
	}
	
	
}
