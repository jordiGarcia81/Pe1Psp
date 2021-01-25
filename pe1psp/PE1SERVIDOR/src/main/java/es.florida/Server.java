package es.florida;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.IOException;

public class Server implements Runnable{
    private static final int SERVER_PORT = 9876;
    private static ExecutorService executorService = Executors.newFixedThreadPool(3);

    @Override
    public void run() {
        ServerSocket server = null;
        try {
            server = new ServerSocket(SERVER_PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Socket client = null;
        while (true) {
            try {
                client = server.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Client connected on port" + client.getPort());
        }
    }
}