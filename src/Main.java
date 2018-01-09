import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(7777);
        Socket socket = server.accept();
        InputStream is = socket.getInputStream();
        new ClientToServer(socket).start();
        new ServerToClient(socket).start();
    }
}

class ClientToServer extends Thread {
    private Socket socket;
    ClientToServer(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        while (true) {
            InputStream is = null;
            try {
                is = socket.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            assert is != null;
            Scanner in = new Scanner(is);
            String s = in.nextLine();
            System.out.println(s);
        }
    }
}

class ServerToClient extends Thread {
    private Socket socket;

    ServerToClient(Socket socket) {
        this.socket = socket;
    }
    public void run() {
            Scanner in = new Scanner(System.in);
            while (true) {
                String s = in.nextLine() + "\n\r";
                OutputStream os = null;
                try {
                    os = socket.getOutputStream();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    assert os != null;
                    os.write(s.getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
    }
}
