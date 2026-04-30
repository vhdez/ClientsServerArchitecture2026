import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerConnector implements Runnable {
    @Override
    public void run() {
        try {
            ServerSocket myServerSocket = new ServerSocket(2000);
            while (true) {
                System.out.println("Server (first program) at 10.69.62.35 port 2000");
                Socket newSocket = myServerSocket.accept();
                CommunicationIn newClient = new CommunicationIn(newSocket);
                Thread perClientThread = new Thread(newClient);
                perClientThread.start();
            }
        } catch (IOException ex) {
            System.out.println("ServerConnector broke: " + ex );
        }
    }
}
