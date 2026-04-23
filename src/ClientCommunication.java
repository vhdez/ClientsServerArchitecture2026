import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientCommunication implements Runnable {
    Socket theSocket;

    public ClientCommunication(Socket theSocket) {
        this.theSocket = theSocket;
    }

    @Override
    public void run() {
        try {
            InputStream myInput = theSocket.getInputStream();
            InputStreamReader myInputReader = new InputStreamReader(myInput);
            BufferedReader myReader = new BufferedReader(myInputReader);
            //        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            int messageNum = 1;
            while (true) {
                String newMessage = myReader.readLine();
                if (newMessage != null) {
                    System.out.println(messageNum + " Client said: " + newMessage);
                    messageNum = messageNum + 1;
                } else {
                    System.out.println(messageNum + " Client said NOTHING");
                }
            }
        } catch (IOException ex) {
            System.out.println("Socket broke:" + ex);
        }
    }
}

