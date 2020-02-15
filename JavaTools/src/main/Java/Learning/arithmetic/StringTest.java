package Learning.arithmetic;

public class StringTest {
    public static void main(String[] args) {
        String str="23,,45,d,d,s,,d,,";
       // System.out.println(str.charAt(str.length() - 1));
        if(str.substring(str.length() - 2, str.length() ).equals(",,")){
            str=str.substring(0,str.length()-1)+" ,";

        }


        String[] split = str.split(",");
        System.out.println(split);

    }
}
