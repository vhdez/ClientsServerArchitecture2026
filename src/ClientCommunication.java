import java.io.*;
import java.net.Socket;

public class ClientCommunication implements Runnable {
    Socket theSocket;

    public ClientCommunication(Socket theSocket) {
        this.theSocket = theSocket;
    }

    @Override
    public void run() {
        try {
            ObjectInputStream myObjInput = new ObjectInputStream(theSocket.getInputStream());
            ObjectOutputStream myObjOutput = new ObjectOutputStream(theSocket.getOutputStream());

            int messageNum = 1;
            while (true) {
                Message newMessage = (Message)myObjInput.readObject();
                if (newMessage != null) {
                    System.out.println(messageNum + " Client said: " + newMessage);
                    messageNum = messageNum + 1;
                    if (newMessage.mode == 1) {
                        Message response = new Message(1,1,"Welcome " + newMessage.from, "SERVER", newMessage.from);
                        myObjOutput.writeObject(response);
                    }
                } else {
                    System.out.println(messageNum + " Client said NOTHING");
                }
            }
        } catch (IOException ex) {
            System.out.println("Socket broke:" + ex);
        } catch (ClassNotFoundException ex) {
            System.out.println("readObject failed:" + ex);
        }

    }
}

