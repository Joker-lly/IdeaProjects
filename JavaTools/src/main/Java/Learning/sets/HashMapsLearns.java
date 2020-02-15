package Learning.sets;

import java.util.HashMap;
import java.util.Map;

import static java.util.HashMap.*;

/**
 * 关于HashMap的学习
 *      有两个版本，1.7和1.8
 *
 */
public class HashMapsLearns {
    /**
     * 1.7的版本
     *      结构：数组加链表
     *      扩容阈值：数组长度的0.75
     *      初始长度：16
     *      扩容机制： ((size >= threshold) && (null != table[bucketIndex]))，意思是hashmap中元素数量
     *          大于于数组长度*0.75,并且，新插入的元素在数组中的坐标的位置不为空，若为空，则不进行扩容
     *      链表插入方式：为头插入法
     *
     * 1.8版本
     *      结构：数组、链表、红黑树
     *      扩容阈值：数组长度的0.75
     *      初始长度：16
     *      扩容机制  size>table.lenthh*0.75，这个是数组扩容
     *              -链表转换红黑树机制：以前看网上的误区，是总数量超过八个就会变成红黑树，其实当是同一链链表数量
     *              插入第九个的时候才会将该链表转换为红黑树
     *
     */
    Map map=new HashMap();
    //下面注释掉的是jdk1.7 hashmap扩容部分代码
    /*void addEntry(int hash, K key, V value, int bucketIndex) {
        if ((size >= threshold) && (null != table[bucketIndex])) {
            resize(2 * table.length);
            hash = (null != key) ? hash(key) : 0;
            bucketIndex = indexFor(hash, table.length);
        }

        createEntry(hash, key, value, bucketIndex);
    }*/

}
