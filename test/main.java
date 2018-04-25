package test;
import java.util.*;

import Scheduler.FifoScheduler;
import Scheduler.MlfqScheduler;
import Scheduler.RRScheduler;
import Scheduler.SpnScheduler;
import Scheduler.Scheduler;

public class main {
	final static int max_proc = 10;
	final static Random rand = new Random();
	final static double short_prob = 0.8;
	
	public static void main(String argv[]) {
		Scanner sb = new Scanner(System.in);
		
		System.out.println("请输入进程总数: ");
		int num = Integer.parseInt(sb.nextLine());
		if (num > max_proc) {
			System.out.println("进程数超过最大限制(最大限制为: " + max_proc + ")");
			return;
		}
		
		ProcessStore proc = new ProcessStore(num);
		proc.print_proc();
		
		System.out.println("请输入调度策略: ");
		int policy = Integer.parseInt(sb.nextLine());
		if (policy == 0) { //FIFO Scheduler
			FifoScheduler fs = new FifoScheduler(proc.temp_proc);
			fs.start_run(Double.MAX_VALUE);
		}
		if (policy == 1) { //SPN Scheduler
			SpnScheduler ss = new SpnScheduler(proc.temp_proc);
			ss.start_run(Double.MAX_VALUE);
		}
		if (policy == 2) { //RR Scheduler
			RRScheduler rs = new RRScheduler(proc.temp_proc,2.0);
			rs.start_run(Double.MAX_VALUE);
		}
		if (policy == 3) { //MLFQ Scheduler
			int proc_num = num;
			MlfqScheduler ms = new MlfqScheduler(proc.temp_proc,2.0,5.0,10.0,2.0);
			while(proc_num < max_proc) {
				int create_num = rand.nextInt(max_proc-proc_num+1);
				System.out.println("Generate: " + create_num + " Process" );
				
				ArrayList<Process> new_procs = proc.create_proc(create_num, proc_num);
				System.out.println("Create num: " + create_num + " new_procs size: " + new_procs.size());
				assert(create_num == new_procs.size());
				
				proc_num += create_num;
				
				ms.enque_list(new_procs);
				
				ms.start_run(100.0);
				
			}
		}
	}
}
