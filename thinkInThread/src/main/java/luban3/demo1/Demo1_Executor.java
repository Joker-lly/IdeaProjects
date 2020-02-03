package luban3.demo1;

import java.util.concurrent.Executor;

/**
 * 认识Executor
 * JDK8在线文档地址:https://docs.oracle.com/javase/8/docs/api/index.html
 */
public class Demo1_Executor implements Executor{

	public static void main(String[] args) {

	}

	@Override
	public void execute(Runnable command) {
		command.run();
	}
}
