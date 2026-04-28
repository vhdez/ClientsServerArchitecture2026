import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

public class CommunicationOut implements Runnable {
    static ArrayList<Socket> allSockets = new ArrayList<>();

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
                for (Socket eachSocket : allSockets) {
                    OutputStream myOut = eachSocket.getOutputStream();
                    ObjectOutputStream myObjOut = new ObjectOutputStream(myOut);
                    myObjOut.writeObject(message);
                    myObjOut.flush();
                    System.out.println("CommunicationOut wrote: " + message);
                }
            } catch (Exception e) {
                System.out.println("CommunicationOut failed: " + e);
            }
        }

    }
}
