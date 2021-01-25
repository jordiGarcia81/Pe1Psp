package es.florida;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class LockServer{
    private static final int SERVER_PORT = 9876;
    private static final String ENCRYPT_PASSWORD="Abc123456%";
    public static void main (String args[]) throws IOException {

        ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
        Socket clientConection;
        clientConection = serverSocket.accept();

        try{ serverSocket  = new ServerSocket(SERVER_PORT);
        }catch(IOException e){
            System.out.println("Error al conectar con el servidor");
        }


        System.out.println("Servidor bloqueado: " +serverSocket+ " " + serverSocket.getInetAddress());

        while(true){
            try { clientConection = serverSocket.accept();
            } catch (IOException e){ System.out.println("Error al conectar con cliente ");
            }

        }

    }
}
