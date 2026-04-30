import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

public class Server {
    public static AlexQueue theQueue = new AlexQueue();
    static ArrayList<ObjectOutputStream> allConnections = new ArrayList<>();

    public static void main(String[] args)  {
        ServerConnector myServerConnector =  new ServerConnector();
        Thread myServerConnectorThread = new Thread(myServerConnector);
        myServerConnectorThread.start();

        CommunicationOut myCommunicationOut = new CommunicationOut();
        Thread myCommunicationOutThread = new Thread(myCommunicationOut);
        myCommunicationOutThread.start();
    }
}