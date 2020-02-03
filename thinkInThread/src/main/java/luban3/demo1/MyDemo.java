package luban3.demo1;

import java.util.concurrent.*;

public class MyDemo {
    public static void main(String[] args) {
        try {
                ExecutorService service= Executors.newFixedThreadPool(3);
                FutureTask<String> task=new FutureTask<>(new Callable<String>() {
                    @Override
                    public String call() throws Exception {
                        System.out.println("烧开水");
                        Thread.sleep(3000);
                        return "ok";
                    }
                });
                FutureTask<String> cook=new FutureTask<>(new Callable<String>() {
                    @Override
                    public String call() throws Exception {
                        System.out.println("煮饭");
                        Thread.sleep(3000);
                        return "ok";
                    }
                });
                service.submit(task);
                service.submit(cook);

            Thread.sleep(2000);
           // task.get(2000, TimeUnit.MILLISECONDS);
            if (task.get(5000, TimeUnit.SECONDS) == "ok"
                    && cook.get(5000, TimeUnit.SECONDS) == "ok") {
                System.out.println("开饭了...");
            }
            service.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
