package Learning.Static;
//import static 使用案例，引入后就可以使用该类中的所有静态资源，在jdk1.5之后的一个特性
//import static com.lly.need.StaticNeeds.*;
/**
 * 探究static关键字的用法
 */
public class StaticTest {
    /**
     *1、用法
     *      (1)修饰变量，属于类变量，可以通过类名.变量名直接引用，
     *      (2)修饰方法，属于类方法，通过类名.方法名直接引用。
     *      (3)静态块，是用于初始化一个类的时候做操作用的，和静态变量、静态方法一样，静态块里面的代码只执行一次，且只在初始化类的时候执行
     *      (4)import static(非常冷门 )，引用其他类中的静态变量，不用加类名；
     *      (5)修饰类：修饰类的话就是修饰内部类
     * 2、加载顺序
     *       静态代码块是严格按照父类静态代码块->子类静态代码块的顺序加载的，且只加载一次;
     *       static资源的加载顺序是严格按照代码的顺序加载的;
     *       在静态块中可以为在static后定义的静态资源赋值，但不能访问
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
      //  System.out.println(UserName);
        System.out.println(StaticTest.c);
    }
}
