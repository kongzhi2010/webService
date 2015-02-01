package husd.wsi.service.communication;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by shengdong on 2015/1/20.
 */
public class EchoServer {

//    private BlockingQueue linkedBlockingQueue = new LinkedBlockingDeque<ServerThread>();

//    private ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(20, 30, (long) 60000, TimeUnit.SECONDS, linkedBlockingQueue);

    public void serve(int port) throws IOException {
        final ServerSocket serverSocket = new ServerSocket(port);
        try {
            while (true) {
                System.out.println("server start:");
                final Socket socket = serverSocket.accept();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            try {
                                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                                PrintWriter writer = new PrintWriter(socket.getOutputStream());
                                while(true){
                                    writer.print(1235);
                                    writer.flush();
                                    Thread.currentThread().sleep(5000);
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        } finally {
                            try {
                                socket.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
            }
        } finally {

        }
    }

    public static void main(String[] args) throws IOException {
        EchoServer server = new EchoServer();
        server.serve(8089);
    }
}
