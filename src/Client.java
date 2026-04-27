import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {
            // Connect to server running on SAME 127.0.0.1 computer
            Socket ourSocket = new Socket("127.0.0.1", 2000);
            OutputStream myOut = ourSocket.getOutputStream();
            PrintWriter myprintOut = new PrintWriter(myOut);

            Message message1 = new Message(1,1,"","Mr. H");
            Message message2 = new Message(1,2,"Wassup?","Mr. H");
            Message message3 = new Message(1,3,"","Mr. H");

            myprintOut.println(message1);
            myprintOut.flush();
        } catch (Exception ex) {
            System.out.println("Socket failed: " + ex);
        }
    }
}
