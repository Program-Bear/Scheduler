package test;
import java.util.ArrayList;
import java.util.Random;

public class ProcessStore {
	public ArrayList<Process> temp_proc = new ArrayList<Process>();
	final double short_num_pro = 0.8;
	Random rand = new Random();
	
	ProcessStore(int num){
		gen_proc(num);
	}
	
	public void gen_proc(int num) {
		for(int i = 0; i < num; i++) {
			double total_time = rand.nextDouble() * 20 + 1;
			if (rand.nextDouble() * 1 < short_num_pro) {
				total_time = rand.nextDouble() * 2 + 1;
			}
			temp_proc.add(new Process(i, total_time));
		}
	}
	public void print_proc() {
		for (int i = 0; i < temp_proc.size(); i++) {
			System.out.println(temp_proc.get(i));
		}
	}
	public ArrayList<Process> create_proc(int num, int start){
		ArrayList<Process> array = new ArrayList<Process>();
		for (int i = 0; i < num; i++) {
			int length = 20;
			if (rand.nextDouble() * 1 < short_num_pro) {
				length = 2;
			}
			Process new_proc = new Process(start + i, rand.nextDouble() * length);
			System.out.println("Create new proc: " + new_proc.toString());
			array.add(new_proc);
		}
		return array;
	}
}
