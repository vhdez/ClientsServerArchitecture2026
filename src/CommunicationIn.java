import java.io.*;
import java.net.Socket;

public class CommunicationIn implements Runnable {
    Socket theSocket;

    public CommunicationIn(Socket theSocket) {
        this.theSocket = theSocket;
    }

    @Override
    public void run() {
        try {
            ObjectInputStream myObjInput = new ObjectInputStream(theSocket.getInputStream());
            // InputStream MUST be created BEFORE OutputStream!!!!!!
            ObjectOutputStream myObjOutput = new ObjectOutputStream(theSocket.getOutputStream());
            Server.allConnections.add(myObjOutput);

            int messageNum = 1;
            boolean stayConnected = true;
            while (stayConnected) {
                Message newMessage = (Message)myObjInput.readObject();
                if (newMessage != null) {
                    System.out.println(messageNum + " Client said: " + newMessage);
                    messageNum = messageNum + 1;
                    if (newMessage.mode == 1) {
                        // START
                        newMessage = new Message(1,1,"SERVER Hello: " + newMessage.from, "SERVER", newMessage.from);
                    } else if (newMessage.mode == 2) {
                        // COMMUNICATE
                        newMessage = newMessage;
                    } else if (newMessage.mode == 3) {
                        // STOP
                        newMessage = new Message(1,3,"SERVER Goodbye: " + newMessage.from, "SERVER", newMessage.from);
                        stayConnected = false;
                    }
                    boolean putSuccess  = Server.theQueue.put(newMessage);
                    while (!putSuccess) {
                        putSuccess  = Server.theQueue.put(newMessage);
                    }
                } else {
                    System.out.println(messageNum + " Client said NOTHING");
                }
            }
        } catch (IOException ex) {
            //ex.printStackTrace();
            System.out.println("CommunicationIn: Socket broke " + ex);
        } catch (ClassNotFoundException ex) {
            System.out.println("CommunicationIn: readObject failed:" + ex);
        //} catch (InterruptedException ex) {
        //    System.out.println("Thread interrupted:" + ex);
        }
        System.out.println("Socket stopped!");
    }
}

