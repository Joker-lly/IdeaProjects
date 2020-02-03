package thinkInJava.suanfa;

public class StringTest {
    public static void main(String[] args) {
        String str="23,,45,d,d,s,,d,,";
       // System.out.println(str.charAt(str.length() - 1));
        if(str.substring(str.length() - 2, str.length() ).equals(",,")){
            str=str.substring(0,str.length()-1)+" ,";

        }


        String[] split = str.split(",");
        System.out.println(split);

        int a=321;
        String b=String.valueOf(a);//
        Integer sum=0;
        Integer number=0;
        for (int i = 0; i <b.length() ; i++) {
            //循环加位数
            number++;
            //取指定的位置的字符
            String c = b.substring(i,i+1);
            sum+=Integer.valueOf(c);//先将字符c转换成数字再相加

        }

        System.out.println(number +":" +sum);




    }
}
