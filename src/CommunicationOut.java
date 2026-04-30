import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

public class CommunicationOut implements Runnable {
    public void run() {
        // GET
        while (true) {
            Message message = Server.theQueue.get();
            while (message == null) {
                message = Server.theQueue.get();
            }
            System.out.println("CommunicationOut got: " + message);
            // WRITE TO SOCKET
            try {
                for (ObjectOutputStream eachConnection : Server.allConnections) {
                    //OutputStream myOut = eachSocket.getOutputStream();
                    //ObjectOutputStream myObjOut = new ObjectOutputStream(myOut);
                    eachConnection.writeObject(message);
                    eachConnection.flush();
                    System.out.println("CommunicationOut wrote: " + message);
                }
            } catch (IOException e) {
                System.out.println("CommunicationOut failed: " + e);
            }
        }

    }
}
