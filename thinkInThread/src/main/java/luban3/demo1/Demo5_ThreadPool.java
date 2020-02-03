package luban3.demo1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


/**
 * 线程池的概念
 */
public class Demo5_ThreadPool {

	public static void main(String[] args) throws InterruptedException {
		ExecutorService service = Executors.newFixedThreadPool(5);
		for (int i = 0; i < 6; i++) {
			service.execute(new Runnable() {
				@Override
				public void run() {
					try {
						TimeUnit.MICROSECONDS.sleep(500);
					} catch (Exception e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName());
				}
			});
		}
		
		System.out.println(service);
		
		service.shutdownNow();
		System.out.println(service.isTerminated());
		System.out.println(service.isShutdown());
		System.out.println(service);
		
		TimeUnit.SECONDS.sleep(5);
		System.out.println(service.isTerminated());
		System.out.println(service.isShutdown());
		System.out.println(service);
	}
}
