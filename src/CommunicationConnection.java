import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class CommunicationConnection {
    String name;
    Socket socket;
    ObjectInputStream inStream;
    ObjectOutputStream outStream;

    public CommunicationConnection(String name, Socket socket, ObjectInputStream inStream, ObjectOutputStream outStream) {
        this.name = name;
        this.socket = socket;
        this.inStream = inStream;
        this.outStream = outStream;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public ObjectInputStream getInStream() {
        return inStream;
    }

    public void setInStream(ObjectInputStream inStream) {
        this.inStream = inStream;
    }

    public ObjectOutputStream getOutStream() {
        return outStream;
    }

    public void setOutStream(ObjectOutputStream outStream) {
        this.outStream = outStream;
    }

    @Override
    public String toString() {
        return "CommunicationConnection{" +
                "name='" + name + '\'' +
                ", socket=" + socket +
                ", inStream=" + inStream +
                ", outStream=" + outStream +
                '}';
    }
}
