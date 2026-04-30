import java.io.IOException;
import java.io.ObjectInputStream;
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

                ObjectInputStream myObjInput = new ObjectInputStream(newSocket.getInputStream());
                // InputStream MUST be created BEFORE OutputStream!!!!!!
                ObjectOutputStream myObjOutput = new ObjectOutputStream(newSocket.getOutputStream());
                CommunicationConnection newConnection = new CommunicationConnection(null,newSocket,myObjInput,myObjOutput);
                Server.allConnections.add(newConnection);

                CommunicationIn newClient = new CommunicationIn(newConnection);
                Thread perClientThread = new Thread(newClient);
                perClientThread.start();
            }
        } catch (IOException ex) {
            System.out.println("ServerConnector broke: " + ex );
        }
    }
}
