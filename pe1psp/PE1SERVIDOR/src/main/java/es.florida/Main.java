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

      /*  ServerSocket server = new ServerSocket(9876);
        Socket connection = server.accept();
        System.out.println("client connected on port " + connection.getPort());
        OutputStream outputStream = connection.getOutputStream();
        InputStream inputStream = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        PrintWriter printer = new PrintWriter(new OutputStreamWriter(outputStream));
        String line;
        while ((line = reader.readLine()) != null) {

            if (line.equalsIgnoreCase("operaciones")) {
                ProcessBuilder builder = new ProcessBuilder("operaciones", "c:");
                System.out.println("Operaciones");
                builder.start();
            } if (line.equalsIgnoreCase("crear")) {
                ProcessBuilder builder = new ProcessBuilder("crear", "c:");
                System.out.println("1.Crear Usuario");
                builder.start();
            }if (line.equalsIgnoreCase("eliminar")) {
                ProcessBuilder builder = new ProcessBuilder("eliminar", "c:");
                System.out.println("2.Eliminar Usuario");
                builder.start();
            }if (line.equalsIgnoreCase("crear")) {
                ProcessBuilder builder = new ProcessBuilder("crear", "c:");
                System.out.println("3.Crear Usuario");
                builder.start();
            }if (line.equalsIgnoreCase("notificar")) {
                ProcessBuilder builder = new ProcessBuilder("notificar", "c:");
                System.out.println("4.Notificar a usuarios");
                builder.start();
            }if (line.equalsIgnoreCase("bloquear")) {
                ProcessBuilder builder = new ProcessBuilder("bloquear", "c:");
                System.out.println("5. Bloquear Servidor");
                builder.start();
            }if (line.equalsIgnoreCase("desbloquear")) {
                ProcessBuilder builder = new ProcessBuilder("desbloquear", "c:");
                System.out.println("6.Desbloquear Servidor");
                builder.start();
            }if (line.equalsIgnoreCase("desconectar")) {
                ProcessBuilder builder = new ProcessBuilder("desconectar", "c:");
                System.out.println("7.Desconectar");
                builder.start();
            }
        }
    }
}*/
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
        Socket clientConection;
        clientConection = serverSocket.accept();

           DataInputStream dataInput =  new DataInputStream(clientConection.getInputStream());
           DataOutputStream dataOutput = new DataOutputStream(clientConection.getOutputStream());





       // while (true) {

            System.out.println("Client connected on port" + clientConection.getPort());
           // executorService.execute(new ServiceWorker(clientConection));
            //Server server = new Server();
            //Thread thread = new Thread( server);


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
                    DeleteMember deleteMember = new DeleteMember();
                    Thread deleteMemberThread = new Thread((Runnable) deleteMember);
                    dataOutput.writeUTF("2.Eliminar Usuario");

                }if (accion.equalsIgnoreCase("notificar")) {
                    MemberMonitor memberMonitor = new MemberMonitor();
                    Thread membermonitorThread = new Thread(memberMonitor);
                    membermonitorThread.start();
                    ProcessBuilder builder = new ProcessBuilder("notificar", "c:");
                    System.out.println("3.Notificar a usuarios");
                    builder.start();
                }if (accion.equalsIgnoreCase("bloquear")) {
                    ProcessBuilder builder = new ProcessBuilder("bloquear", "c:");
                    System.out.println("4. Bloquear Servidor");
                    builder.start();
                }if (accion.equalsIgnoreCase("desbloquear")) {
                    ProcessBuilder builder = new ProcessBuilder("desbloquear", "c:");
                    System.out.println("5.Desbloquear Servidor");
                    builder.start();
                }if (accion.equalsIgnoreCase("desconectar")) {
                    ProcessBuilder builder = new ProcessBuilder("desconectar", "c:");
                    System.out.println("6.Desconectar");
                    builder.start();
                }
            } catch (IOException ex) {
                System.out.println(ex);
            }


       // }

       /* private static class ServiceWorker implements Runnable {
            private Socket connection;

            public ServiceWorker(Socket clientConection) {
                this.connection = clientConection;
            }

            @Override
            public void run() {
                System.out.println("Cliente activo: " + Thread.currentThread().getName());

            }
        }*/
    }
}





