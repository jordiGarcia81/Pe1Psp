package es.florida;


import java.io.*;
import java.util.LinkedList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MemberMonitor implements Runnable{
    File file = new File("miembros.txt");
    private Lock lock = new ReentrantLock();
    private int numLineas=0;

    public MemberMonitor() {
    }

    @Override
    public void run() {

        while(true){
            try{
                Thread.sleep(3000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }

            leerMiembros();

        }
    }


    public void leerMiembros() {
        //System.out.println("hilo1");

        FileReader fr = null;
        BufferedReader br = null;

        try {

            fr = new FileReader(file);
            br = new BufferedReader(fr);

            String linea;
            int auxLineas=0;
            String lineaAux="";
            while ((linea = br.readLine()) != null){
                System.out.println(linea);
                auxLineas++;
                lineaAux=linea;
            }
            if(auxLineas > 0 && numLineas!=auxLineas){
                numLineas=auxLineas;

                lock.lock(); //

                MailSender mailSender = new MailSender(lineaAux);
                Thread  mailSenderThread = new Thread(mailSender);
                mailSenderThread.start();

                lock.unlock(); //

            }


            fr.close();
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
