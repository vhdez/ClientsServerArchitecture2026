import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.SortedMap;

public class City2 {
    public static void main(String[] args) {
        try {
            Socket ourSocket = new Socket("127.0.0.1", 67);
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
