package thinkInJava.lock;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public class TestLock {
    public static void main(String[] args) {
        ReentrantLock reentrantLock=new ReentrantLock(true);

        new Thread(new Thread(() -> {
            reentrantLock.lock();
            System.out.println("sd");
            reentrantLock.unlock();

        }));
        Map<Integer,String> map=new HashMap<>();
        for(int i=0;i<1000;i++){
            map.put(i,"1111");
        }
        Iterator<Map.Entry<Integer, String>> it=map.entrySet().iterator();
        Set<Map.Entry<Integer, String>> entrySet = map.entrySet();

        long start = System.currentTimeMillis();
      /*  for (Map.Entry<Integer, String> entry:entrySet){
            String value = entry.getValue();
            System.out.println(value);
        }*/
        while (it.hasNext()){
            Map.Entry<Integer, String> next = it.next();
            System.out.println(next.getValue());
        }
        long end = System.currentTimeMillis();
        System.out.println(end-start);

    }
}
