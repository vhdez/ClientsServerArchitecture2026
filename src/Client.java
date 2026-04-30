import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static AlexQueue theQueue = new AlexQueue();

    public static void main(String[] args) {
        try {
            // Connect to server running on SAME 127.0.0.1 computer
            Socket ourSocket = new Socket("127.0.0.1", 2000);

            OutputStream myOut = ourSocket.getOutputStream();
            ObjectOutputStream myObjOut = new ObjectOutputStream(myOut);

            CommunicationConnection newConnection = new CommunicationConnection(null, ourSocket,null,myObjOut);

            CommunicationIn myCommunicationIn = new CommunicationIn(newConnection);
            Thread communicationInThread = new Thread(myCommunicationIn);
            communicationInThread.start();

            Message message1 = new Message(1,1,"","Mr. H", "SERVER");
            myObjOut.writeObject(message1);
            myObjOut.flush();

            boolean stayConnected = true;
            while (stayConnected) {
                Scanner inputTextScanner = new Scanner(System.in);
                System.out.print("Type your message: ");
                String theText = inputTextScanner.nextLine();
                if (theText.equalsIgnoreCase("STOP")) {
                    stayConnected = false;
                }
                Message newMessage = new Message(1, 2, theText, "Mr. H", "?");
                myObjOut.writeObject(newMessage);
                myObjOut.flush();
            }

            Message message3 = new Message(1,3,"","Mr. H", "SERVER");
            myObjOut.writeObject(message3);
            myObjOut.flush();

        } catch (Exception ex) {
            System.out.println("Socket failed: " + ex);
        }
    }
}
