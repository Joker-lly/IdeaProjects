package Learning.keywords.FInal;

/**
 * final 关键字的一些特性
 * //待完善
 *
 */
public class FinalTest {
    /**
     * final 字面意思是：最终的
     *      (1)修饰变量
     *          -如果变量是基本类型，那么该常量只能赋值一次，
     *          -如果变量是引用类型，那么变量的指引地址则不能再进行改动，但指向的对象的内部是可以进行变动的
     *
     *          -如果是局部变量，在未访问前，可以不为其赋值，不报错，一旦访问，就要赋值，
     *          -如果是成员变量,要进行初始化，1)定义时初始化，2）构造函数初始化
     *      (2)修饰方法，则该方法不可以被重写，但可以被继承
     *      (3)修饰类，则表示该类为最终类，不可以被继承
     *
     */


    public String abc(){
        //在方法内定义一个局部变量，若是在定义时不进行赋值也是不会报错，一旦要用到的话，就必须对其进行赋值
         final  int b;
        return "Hello World";
    }


    public static void main(String[] args) {
        System.out.println(new FinalTest().abc());

    }

}
