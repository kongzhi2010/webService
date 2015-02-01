package husd.wsi.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by shengdong on 2015/1/5.
 */
public class IoEchoServer {

    public void startServer(int port) throws IOException {
        final ServerSocket serverSocket = new ServerSocket(port);
        try {
            while (true) {
                final Socket clientSocket = serverSocket.accept();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            BufferedReader bufferReader = new BufferedReader(new InputStreamReader(
                                    clientSocket.getInputStream()
                            ));
                            PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream(),true);
                            while (true){
                                printWriter.write(bufferReader.readLine());
                                printWriter.flush();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }finally {
                            try {
                                clientSocket.close();
                            } catch (IOException e) {
                            }
                        }
                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
    }
}
