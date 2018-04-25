package test;
import java.util.*;

public class Process {
	public int id;
	private double total_time;
	private double remain_time;

	Process(int _id, double _total_time){
		id = _id;
		total_time = _total_time;
		remain_time = total_time;
	}
	public double Run(double time) {
		System.out.println("Process: " + id + " Run for "+ time);
		
		remain_time -= time;
		if (remain_time <= 0) {
			System.out.println("Process: " + id + " Finish!");
			if (remain_time < 0) {
				System.out.println("Warning: Process " + id + " remain time < 0");
			}
		}
		return remain_time;
	}
	public double get_total() {
		return total_time;
	}
	public double get_remain() {
		return remain_time;
	}
	
	@Override
	public String toString() {
		return "Process: " + id + ": \n" + "Total time: " + 
	total_time + " Remain time: " + remain_time + "\n";
	}
}
