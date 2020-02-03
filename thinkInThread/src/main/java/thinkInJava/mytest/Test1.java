package thinkInJava.mytest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

public class Test1 {
    final Integer integer=66;
    public static int findMostInArray(int[] a){
        int[] count=new int[101];
        for(int i=0;i<a.length;i++){
            count[a[i]]++;
        }
        int maxCount=count[0];
        int maxNumber=a[0];
        for(int i=0;i<100;i++){
            maxCount=count[i]>maxCount?count[i]:maxCount;
        }
        for(int i=0;i<100;i++){
            if (count[i]==maxCount) {
                maxNumber=i;
            }
        }
        return maxNumber;
    }


    public static void main(String[] args) {



        String str= "adc";

//        StringBuilder sb=new StringBuilder("adb");

//        long start = System.currentTimeMillis();
//
//
//        long end = System.currentTimeMillis();
//
//        System.out.println(end-start);
        StringBuffer stringBuffer=new StringBuffer();

        System.out.println(str.codePointBefore(1));

        ArrayList arrayList=new ArrayList();


        LinkedList linkedList=new LinkedList();

        Hashtable hashtable=new Hashtable();

        HashMap map=new HashMap();
        ConcurrentHashMap maps=new ConcurrentHashMap();

        ArrayList arraylist=new ArrayList<>();


        String a="xiaoming2";
        final String b="xiaoming";
        String c="xiaoming";
        String d=b+2;
        String e=c+2;


        System.out.println(a==d);
        System.out.println(a==e);


    }
}
