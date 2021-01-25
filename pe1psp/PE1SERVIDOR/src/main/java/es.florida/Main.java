package es.florida;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    private static final int SERVER_PORT = 9876;


    public static void main(String[] args) throws IOException {

        ExecutorService executorService = Executors.newFixedThreadPool(6);
        ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
        Socket clientConection;
        clientConection = serverSocket.accept();

           DataInputStream dataInput =  new DataInputStream(clientConection.getInputStream());
           DataOutputStream dataOutput = new DataOutputStream(clientConection.getOutputStream());

            System.out.println("Client connected on port" + clientConection.getPort());

            String accion = "";

            try {
                accion = dataInput.readUTF();

                System.out.println("accion: "+accion);
                if (accion.equals("operaciones")) {
                   System.out.println("1.Crear Usuario");
                    System.out.println("2.Eliminar Usuario");
                    System.out.println("3.Notificar a usuarios");
                    System.out.println("4.Bloquear Servidor");
                    System.out.println("5.Desbloquear Servidor");
                    System.out.println("6.Desconectar");
                    dataOutput.writeUTF("1.Crear Usuario 2.Eliminar Usuario 3.Notificar a usuarios 4.Bloquear Servidor 5.Desbloquear Servidor 6.Desconectar");



                }
                if (accion.equals("crear")) {
                    MemberCreator memberCreator = new MemberCreator();
                    Thread memberCreatorThread = new Thread(memberCreator);
                    memberCreatorThread.start();
                    dataOutput.writeUTF("1.Crear Usuario");

                }if (accion.equalsIgnoreCase("eliminar")) {
                    dataOutput.writeUTF("2.Eliminar Usuario");

                }if (accion.equalsIgnoreCase("notificar")) {
                    MemberMonitor memberMonitor = new MemberMonitor();
                    Thread membermonitorThread = new Thread(memberMonitor);
                    membermonitorThread.start();
                    ProcessBuilder builder = new ProcessBuilder("notificar", "c:");
                    System.out.println("3.Notificar a usuarios");
                    builder.start();
                }if (accion.equalsIgnoreCase("bloquear")) {
                    LockServer lockServer = new LockServer();
                    Thread lockServerThread = new Thread((Runnable) lockServer);
                    lockServerThread.start();
                    ProcessBuilder builder = new ProcessBuilder("bloquear", "c:");
                    System.out.println("4. Bloquear Servidor");
                    builder.start();
                }if (accion.equalsIgnoreCase("desbloquear")) {
                    ProcessBuilder builder = new ProcessBuilder("desbloquear", "c:");
                    System.out.println("5.Desbloquear Servidor");
                    builder.start();
                }if (accion.equalsIgnoreCase("desconectar")) {
                    serverSocket.close();
                    ProcessBuilder builder = new ProcessBuilder("desconectar", "c:");
                    System.out.println("6.Desconectar");
                    builder.start();
                }
            } catch (IOException ex) {
                System.out.println(ex);
            }

    }
}





