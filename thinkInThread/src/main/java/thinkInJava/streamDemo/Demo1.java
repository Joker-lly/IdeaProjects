package thinkInJava.streamDemo;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class Demo1 {
    public static void main(String[] args) {

        System.out.println(UUIDUtil.uuid());
        String user="name";
        user+="123";
        System.out.println(user);
        StringBuffer bf=new StringBuffer();
        StringBuilder builder=new StringBuilder();
        String sd[]={"1","2"};
//        ArrayList lists=new ArrayList(Arrays.asList(sd));
        Set set=new HashSet();

        Apple apples[]=new Apple[10];
        for (int i=0;i<10;i++){
            Apple apple=new Apple();
            apple.setId(i);
            apple.setWeight((int) (1+Math.random()*1000));
            apples[i]=apple;
        }
        List<Apple> apples1 = Arrays.asList(apples);

        List linkedlist=new LinkedList();

        List<Apple> list=new ArrayList();
       // Random ra=new Random()
        for (int i=0;i<10000;i++){
            Apple apple=new Apple();
            apple.setId(i);
            apple.setWeight((int) (1+Math.random()*1000));
            list.add(apple);
        }

        /*System.out.println(list.get(0).getWeight());
        Optional<Apple> first = list.stream().findFirst();
        System.out.println(first.get().getWeight());
        long start = System.currentTimeMillis();
        list.stream().forEach(apple -> System.out.println(apple.getWeight()));
        for (int j = 0; j < list.size(); j++) {
            System.out.println(list.get(j).getWeight());
        }
        long end = System.currentTimeMillis();
        long time = end - start;
        System.out.println("stream 用时"+time);*/
       list.stream().distinct().sorted(Comparator.comparing(Apple::getWeight)).forEach(apple ->{
            System.out.println(apple.getWeight());
        });
       /* Apple a1=new Apple();
        Apple a2=new Apple();
        a1.setWeight(12);
        a2.setWeight(12);
        System.out.println(a1.equals(a2));*/


    }
}
