package luban3.demo1;

		import java.util.concurrent.*;

/**
 * 认识future(将来)
 */
public class Demo6_Future {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		FutureTask<Integer> task = new FutureTask<>(()->{//new callable
			TimeUnit.MILLISECONDS.sleep(500);
			return 1000;
		});

		new Thread(task).start();

		System.out.println(task.get());

		ExecutorService service = Executors.newFixedThreadPool(5);
		Future<Integer> f = service.submit(()->{
			TimeUnit.MILLISECONDS.sleep(5000);
			return 1;
		});
		//阻塞，其实也就是意味着是同步的
		System.out.println(f.get());

		System.out.println("11111");
		System.out.println(f.isDone());
	}

}
