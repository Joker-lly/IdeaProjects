package luban3.demo1;

public class Demo {

	/**
	 * 回顾
	 * linkedBq
	 * array
	 * delayqueue priority
	 * transferqueue
	 * synbq
	 */

	public static void main(String[] args) {
		System.out.println(B.str1);
	}
}
class A{
	public A(){
		System.out.println("A构造器执行了");
	}
	static String str ="a";
	static {
		System.out.println("A");
	}
}
class  B extends A{
	public B(){
		System.out.println("B构造器执行了");
	}
	static {
		System.out.println("B");
	}
	static String str1="b";

}

