package thinkInJava.suanfa;

import java.io.*;

public class QuicklySort {
    public static void qSort(int[] data,int left,int right){
        int ll=left;
        int rr=right;
        int base=data[left];
    }
    public static void main(String[] args) {
        int max = Math.max(1, 3);
        System.out.println(5&4);

        String str="E:\\zilaio\\test.txt";
        File file=new File(str);
        System.out.println(file.exists());

        StringBuilder builder=new StringBuilder();
        InputStreamReader inputStreamReader = null;
        try {
            inputStreamReader = new InputStreamReader(new FileInputStream(file), "utf-8");
            BufferedReader bufferReader = new BufferedReader(inputStreamReader);
            String lineStr = null;
            while((lineStr = bufferReader.readLine()) != null) {
                builder.append(lineStr);
            }

            bufferReader.close();
            inputStreamReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String strtemp=builder.toString();
        String[] split = strtemp.split("</TEST>");
        System.out.println(builder.toString());
    }
}
