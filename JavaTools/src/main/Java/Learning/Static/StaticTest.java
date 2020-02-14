package Learning.Static;

/**
 * 探究static关键字的用法
 */
public class StaticTest {
    /**
     *1、用法
     *      (1)修饰变量，属于类变量，可以通过类名.变量名直接引用，
     *      (2)修饰方法，属于类方法，通过类名.方法名直接引用。
     *      (3)静态块，是用于初始化一个类的时候做操作用的，和静态变量、静态方法一样，静态块里面的代码只执行一次，且只在初始化类的时候执行
     *
     *
     */

    private  static int a=A();

    public static int A(){
        //通过改变A（） 方法的位置，可以看出，static资源的加载顺序是严格按照代码的顺序加载的
        System.out.println("小明");
        return 1;
    }

    static {
        System.out.println("你好");
    }

    static {
        //在静态块中可以为在static后定义的静态资源赋值，但不能访问
         c=3;
        //System.out.println(c);
    }

    private static int c;



    public static void main(String[] args) {
        new StaticTest();
        System.out.println(StaticTest.c);
    }
}
