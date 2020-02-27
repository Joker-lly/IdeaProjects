package Learning.io.lubanNio;

public class LubanJedis {
    //简单的实现
    LubanSocket lubanSocket=new LubanSocket("192.168.204.188",6379);

    public String set(String key, String value){
        lubanSocket.send(commandUtil(Resp.command.SET,key.getBytes(),value.getBytes()));
        return lubanSocket.read();
    }


    public String get(String key){
        lubanSocket.send(commandUtil(Resp.command.GET,key.getBytes()));
        return lubanSocket.read();
    }


    public String incr(String key){
        lubanSocket.send(commandUtil(Resp.command.INCR,key.getBytes()));
        return lubanSocket.read();
    }



    public static String commandUtil(Resp.command command,byte[]... bytes){
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append(Resp.star).append(1+bytes.length).append(Resp.line);
        stringBuilder.append(Resp.StringLength).append(command.toString().length()).append(Resp.line);
        stringBuilder.append(command.toString()).append(Resp.line);
        for (byte[] aByte : bytes) {
            stringBuilder.append(Resp.StringLength).append(aByte.length).append(Resp.line);
            stringBuilder.append(new String(aByte)).append(Resp.line);
        }
        return stringBuilder.toString();
    }


    public static void main(String[] args) {
//        LubanJedis lubanJedis=new LubanJedis();
//        System.out.println(lubanJedis.set("taibai2", "123456"));
//        System.out.println(lubanJedis.set("taibai1", "123456"));
////        System.out.println(lubanJedis.get("taibai"));
//        System.out.println(lubanJedis.incr("lock"));
        System.out.println(Resp.command.GET);
    }

}
