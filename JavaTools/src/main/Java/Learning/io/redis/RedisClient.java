package Learning.io.redis;

import redis.clients.jedis.Jedis;

public class RedisClient {

    private LubanSocket lubanSocket;

    public RedisClient(String ip,int prot) {
        this.lubanSocket=new LubanSocket(ip,prot);
    }

    public String set(String key, String value){
        lubanSocket.send(commandStrUtil(Resp.command.SET,key.getBytes(),value.getBytes()));
        return lubanSocket.read();
    }


    public void close(){
        lubanSocket.close();
    }


    public String get(String key){
        lubanSocket.send(commandStrUtil(Resp.command.GET,key.getBytes()));
        return lubanSocket.read();
    }


    public String incr(String key){
        lubanSocket.send(commandStrUtil(Resp.command.INCR,key.getBytes()));
        return lubanSocket.read();
    }


    public String commandStrUtil(Resp.command command, byte[]... bytes){
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append(Resp.star).append(1+bytes.length).append(Resp.crlf);
        stringBuilder.append(Resp.lengthStart).append(command.toString().getBytes().length).append(Resp.crlf);
        stringBuilder.append(command.toString()).append(Resp.crlf);
        for (byte[] aByte : bytes) {
            stringBuilder.append(Resp.lengthStart).append(aByte.length).append(Resp.crlf);
            stringBuilder.append(new String(aByte)).append(Resp.crlf);
        }
        return stringBuilder.toString();
    }


    public static void main(String[] args) {
//        RedisClient redisClient=new RedisClient("192.168.204.188",6379);
        Jedis redisClient=new Jedis("127.0.0.1",9999);
        System.out.println(redisClient.set("taibai", "123456"));
        System.out.println(redisClient.get("taibai"));
        System.out.println(redisClient.incr("lock"));
        redisClient.close();
    }
}
