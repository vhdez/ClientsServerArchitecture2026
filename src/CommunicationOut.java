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
            // WRITE TO SOCKET
            for (CommunicationConnection eachConnection : Server.allConnections) {
                try {
                    // check if message.to matches socket's name
                    if (message.to.equalsIgnoreCase("ALL") || message.to.equalsIgnoreCase(eachConnection.getName())) {
                        eachConnection.getOutStream().writeObject(message);
                        eachConnection.getOutStream().flush();
                        System.out.println("CommunicationOut to " + eachConnection.getName() + ": " + message);
                    }
                } catch (IOException e) {
                    System.out.println("CommunicationOut to " + eachConnection.getName() + " failed: " + e);
                }
            }
        }
    }
}
