package es.florida;

import java.io.*;
import java.util.Random;

public class MemberCreator implements Runnable  {
    File file = new File("miembros.txt");


    public MemberCreator() {
    }

    @Override
    public void run() {


            CrearMiembros();

    }

    private String generar(){
        String[] nombres={"Jordi","Juan","Carlos","Jesus","Damia","Pablo","Rafa","Noe","Cristian","Raquel","Cristina","Javier","Enrique"};
        String[] apellidos={"Garcia","Romero","Simon","Perez","Ruiz","Navarro","Gonzalez","Rodriguez","Martinez","Martin","Moreno","Alonso"};
        String [] correo={"@hotmail.com","@gmail.com","@floridauniversitaria.es"};
        Random random=new Random();
        int posNombre=random.nextInt(nombres.length);
        int posApellido=random.nextInt(apellidos.length);
        int posCorreo = random.nextInt(correo.length);
        //System.out.println( "Nombre: +nombres[posNombre],+" "+Apellidos: +apellidos[posApellido]+" "+apellidos[posApellido]+" "+email: "+nombres[posNombre]+" "+apellidos[posApellido]+" "+correo[posCorreo]);

        String currentMail = nombres[posNombre]+apellidos[posApellido]+correo[posCorreo];
        String output = currentMail;
        return "\n"+output;
    }
    public void CrearMiembros() {


        try {
            BufferedWriter fileBufferedWriter = null;
            if(file.exists()){
                fileBufferedWriter = new BufferedWriter(new FileWriter(file, true));
                fileBufferedWriter.write(generar());
            } else {
                fileBufferedWriter = new BufferedWriter(new FileWriter(file));
                fileBufferedWriter.write(generar());

            }
            fileBufferedWriter.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

