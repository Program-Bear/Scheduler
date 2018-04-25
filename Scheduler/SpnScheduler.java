package Scheduler;
import java.util.*;

import test.Process;

public class SpnScheduler implements Scheduler {
	Queue<Process> pro_que = new PriorityQueue<>(cmp);
	
	public SpnScheduler(ArrayList<Process> pro_array){
		for (int i = 0; i < pro_array.size(); i++) {
			enque(pro_array.get(i));
		}
	}
	
	@Override
	public String toString() {
		
		String temp  = "";
		int tot = pro_que.size();
		while(tot != 0) {
			Process tp = pro_que.poll();
			temp += tp.toString();
			tot -= 1;
		}
		return temp;
	}
	public static Comparator<Process> cmp = new Comparator<Process>() {
		@Override
		public int compare(Process p1, Process p2) {
			if (p1.get_remain() - p2.get_remain() < 0) {
				return -1;
			}else {
				return 1;
			}
		}
	};
	
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
		pro_que.add(temp);

	}

	@Override
	public double start_run(double total_time) {
		double run_time = 0;
		
		while(!pro_que.isEmpty() && total_time > 0) {
			Process now_pro = get_next();
			double remain_time = now_pro.get_remain();
			double real_time = remain_time;
			if (remain_time <= total_time) {
				now_pro.Run(remain_time);
				deque();
			}else {
				real_time = total_time;
				now_pro.Run(total_time);
				deque();
				enque(now_pro);
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
