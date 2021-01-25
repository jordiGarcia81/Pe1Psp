package es.florida;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;


public class App {
    public static final int PORT_HTTP = 9876;

    public static void  main(String[]args) throws IOException {

        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("127.0.0.1",PORT_HTTP));//cambiar la ip y puerto
        DataInputStream dataInput =  new DataInputStream(socket.getInputStream());
        DataOutputStream dataOutput = new DataOutputStream(socket.getOutputStream());

   // Scanner scanner= new Scanner();
        dataOutput.writeUTF("operaciones");
        String respuesta="";
        respuesta = dataInput.readUTF();
        System.out.println(" Servidor devuelve accion: " + respuesta);



        //printer.println("HELP");
        //printer.println("HELP");

    dataInput.close();
    dataOutput.close();
        socket.close();

    }
}

