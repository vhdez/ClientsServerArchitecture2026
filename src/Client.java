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
            myprintOut.println("Hi it's me!");
            myprintOut.println("Hi honey, I'm home!");
            myprintOut.flush();
        } catch (Exception ex) {
            System.out.println("Socket failed: " + ex);
        }
    }
}
