package luban3.demo1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 线程池的概念
 */
public class Demo7_ParalleComputing {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		long start = System.currentTimeMillis();
		List<Integer> results = getPrime(1, 200000);
		long end = System.currentTimeMillis();
		System.out.println(end - start);

		final int cupCoreNum = 8;

		ExecutorService service = Executors.newFixedThreadPool(cupCoreNum);
		
		MyTask myTask1 = new MyTask(1, 50000);
		MyTask myTask2 = new MyTask(50001, 100000);
		MyTask myTask3 = new MyTask(100001, 150000);
		MyTask myTask4 = new MyTask(150001, 200000);

		Future<List<Integer>> f1 = service.submit(myTask1);
		Future<List<Integer>> f2 = service.submit(myTask2);
		Future<List<Integer>> f3 = service.submit(myTask3);
		Future<List<Integer>> f4 = service.submit(myTask4);
		
		start = System.currentTimeMillis();
		f1.get();
		f2.get();
		f3.get();
		f4.get();
		end = System.currentTimeMillis();
		System.out.println(end - start);
		service.shutdown();
	}
	
	static class MyTask implements Callable<List<Integer>> {
		int startPos, endPos;
		
		public MyTask(int s, int e) {
			this.startPos = s;
			this.endPos = e;
		}
		@Override
		public List<Integer> call() throws Exception {
			List<Integer> r = getPrime(startPos, endPos);
			return r;
		}
	}
	
	static boolean isPrime(int num){
		for (int i = 2; i <= Math.sqrt(num); i++) {
			if (num % i == 0) {
				return false;
			}
		}
		return true;
	}
	
	static List<Integer> getPrime(int start, int end){
		List<Integer> results = new ArrayList<Integer>();
		for (int i = start; i <= end; i++) {
			if (isPrime(i) && i !=1) {
				results.add(i);
			}
		}
		return results;
	}
	
}
