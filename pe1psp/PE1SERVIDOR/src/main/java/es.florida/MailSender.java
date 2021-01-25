package es.florida;


import org.apache.commons.mail.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;


public class MailSender implements Runnable {


    public static final int MAXIMUM_THREADS = 40;
    File file = new File("miembros.txt");

    private String ultimoCorreo;

    public MailSender(String ultimoCorreo) {
        this.ultimoCorreo = ultimoCorreo;
    }

    public MailSender() {

    }

    @Override
    public void run() {
        FileReader fr = null;
        BufferedReader br = null;
        String linea = "";
        Collection<Callable<String>> objectMail = new ArrayList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(40);
        executorService.shutdown();
        try {
            if(!executorService.awaitTermination(5, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        try {

            fr = new FileReader(file);
            br = new BufferedReader(fr);

            while ((linea = br.readLine()) != null) {
                String finalLinea = linea;
                objectMail.add(() -> sendMail(finalLinea, ultimoCorreo));
            }
            fr.close();
            br.close();

            List<Future<String>> result = executorService.invokeAll(objectMail);

            for (Future s_mail : result) {

                if(s_mail.isDone()){
                    //Flag
                }

            }


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private static String sendMail(String mail, String ultimoCorreo) throws EmailException {
        Email email = new SimpleEmail();
        LinkedList<String> mails = new LinkedList<>();
        mails.add(mail);

        try{
            email.setHostName("localHost");
            email.setSmtpPort(1025);
            email.setAuthenticator(new DefaultAuthenticator("",""));
            email.setSSLOnConnect(false);
            email.setFrom(ultimoCorreo);
            email.setSubject("Nuevo Miembro");
            email.setMsg("El nuevo miembro es: " + ultimoCorreo);
            email.addTo(mails);
            email.send();

            return "1";
        } catch(Exception e){
            return  "0";
        }

    }
}


