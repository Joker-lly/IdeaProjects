package Learning.arithmetic;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * 算法初识
 *    时间复杂度：有限次数的还是 O(1)
 *    数据结构
 *      List
 *          数组有下标，链表没有下标但有指针
 *          数组查询快，增删慢
 *          链表，查询慢，增删快
 *      Set  用来去重
 *          HashSet 就是去重，但输入输出的顺序不yiyang
 *          TreeSet 排序，底层是红黑树
 *          LinkedHashSet   记录了插入顺序
*      Map：
 *
 *     Queue：非常非常好的一个数据结构，先进先出
 *            等待队列
 *            排队场景
 *            阻塞 take（）
 *            优先队列：排队场景中的vip
 *
 *
 */
public class First {
    /**
     * 情景一，
     *      给你一堆数，找出其中一的2的幂次方数

     */
    public static boolean isTwoN(Integer n){
        boolean falg=false;
      if(n>0){
          int i = n & (n - 1);
          if(i==0)
              falg=true;
      }
        return falg;
    }
    public void timefuza(){
        //时间复杂度
        int n=0;//O(1)
        int a=n+1;
        for(int i=0;i<100;i++){}//这种也是O（1），如果i<m,m未知，则是O(m)

        for (int i = 0; i <n ; i++) {//O(n^2)
            for(int j=0;j<n;j++){
                //O(n)
            }
        }
        for (int i = 0; i <n ; i++) {//O(n^2)
            for(int j=0;j<=n;j++){

            }
        }
        for (int i = 0; i <n ; i++) {//log(n)
                while (a<n){
                    a=a*2;
                }
        }
    }


    public static void main(String[] args) {
        //ArrayBlockingQueue<String> queue=new ArrayBlockingQueue<String>();

        System.out.println(isTwoN(7));

    }

}
