import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.Scanner;

public class Server {
    public static void main(String[] args)  {
        try {
            ServerSocket myServerSocket = new ServerSocket(67);
            System.out.println("Server sent the ship out!");
            Socket ourSocket = myServerSocket.accept();
            InputStream myInput = ourSocket.getInputStream();
            InputStreamReader myInputReader = new InputStreamReader(myInput);
            BufferedReader myReader = new BufferedReader(myInputReader);
            //        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String firstMessageEVER = myReader.readLine();
            System.out.println("Another city said: " + firstMessageEVER);
            String firstMessageEVER2 = myReader.readLine();
            System.out.println("Another city said: " + firstMessageEVER2);

        } catch (IOException ex) {
            System.out.println("Socket broke:"  + ex);
        }
    }
}