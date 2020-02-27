package Learning.io.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class BioServer {

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            serverSocket=new ServerSocket(9999);
            TimeServerHandlerExecutorPool timeServerHandlerExecutorPool=new TimeServerHandlerExecutorPool(50,1000);
            while (true){
                Socket socket = serverSocket.accept();  //，等待客户端连接 阻塞
                System.out.println("客户端"+socket.getRemoteSocketAddress().toString()+"来连接了");
//                new Thread(new BioServerHandler(socket)).start();
//                socket.getInputStream().read(); 此方法也是阻塞的，读取数据阻塞，比如，如果有个客户端一直不发，则一直等待
                timeServerHandlerExecutorPool.execute(new BioServerHandler(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(serverSocket!=null){
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
