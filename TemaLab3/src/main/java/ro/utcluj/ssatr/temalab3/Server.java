/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.utcluj.ssatr.temalab3;

import java.net.*;
import java.io.*;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author antoniu
 */

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket ss =new ServerSocket(4050);
        
        while(true){
            System.out.println("Astept conexiune de la client...");
            Socket s = ss.accept();
            System.out.println("Clientul s-a conectat!");
            
            BufferedReader fluxIn = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintWriter fluxOut = new PrintWriter(new OutputStreamWriter(s.getOutputStream()),true);
            
            ArrayList<Masina> parcare = new ArrayList<>();
            String line = "";
            
            while(!line.equals("close connection")) {
                line = fluxIn.readLine();
                
                System.out.println("Masina: " + line);
                
                if (line == null || line.equals("close connection"))
                    break;
                
                // verificam daca exista sau nu
                
                boolean parcata = false;
                
                System.out.println("line: " + line);
                
                for (Masina masina:parcare) {
                    if (masina.getNumar().equals(line.trim())) {
                        parcata = true;
                        
                        long secunde = (System.currentTimeMillis() - masina.getIntrare()) / 1000;
                        
                        System.out.println("este deja parcata de " + secunde + " seconde - costa " + secunde + " lei");
                        
                        fluxOut.println("Iesire " + masina.getNumar() + " Cost " + secunde + " RON");
                        
                        parcare.remove(masina);
                        
                        break;
                    }
                }
                
               if (!parcata) {
                   Masina masina = new Masina(line, System.currentTimeMillis());
                   
                   parcare.add(masina);
                   
                   Timestamp intrare = new Timestamp(masina.getIntrare());
                   DateFormat formatter = new SimpleDateFormat("HH:mm");
                   
                   fluxOut.println("Intare " + line + " Ora " + formatter.format(intrare));
               }
            }

            s.close();
        }
    }
}