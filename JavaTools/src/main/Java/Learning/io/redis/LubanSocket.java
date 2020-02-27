package Learning.io.redis;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class LubanSocket {

    private Socket socket;
    private InputStream inputStream;
    private OutputStream outputStream;

    public LubanSocket(String ip,int prot) {
        try {
            if(!isCon()){
                socket=new Socket(ip,prot);
                inputStream=socket.getInputStream();
                outputStream=socket.getOutputStream();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void send(String str){
        System.out.println(str);
        try {
            outputStream.write(str.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String read(){
        byte[] b=new byte[1024];
        int count=0;
        try {
            count= inputStream.read(b);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(b,0,count);
    }


    public boolean isCon(){
        return socket!=null && !socket.isClosed() && socket.isConnected();
    }

    public void close(){
        if(outputStream!=null){
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(inputStream!=null){
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(socket!=null){
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
