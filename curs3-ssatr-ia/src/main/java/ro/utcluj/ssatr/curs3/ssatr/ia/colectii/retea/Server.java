/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.utcluj.ssatr.curs3.ssatr.ia.colectii.retea;


import java.net.*;
import java.io.*;
import java.util.StringTokenizer;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket ss =new ServerSocket(4050);
        
        while(true){
            System.out.println("Astept conexiune de la client...");
            Socket s = ss.accept(); //metoda blocanta
            System.out.println("Clientul s-a conectat!");
            //...... 
            BufferedReader fluxIn = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintWriter fluxOut = new PrintWriter(new OutputStreamWriter(s.getOutputStream()),true);
            //......
            String line = "";
            while(!line.equals("close connection")){
                line = fluxIn.readLine();
                if(line==null || line.equals("close connection"))
                    break;
                System.out.println("Am primit de la client operatia: "+line);
                line = "Rezultat="+calcul(line);
                System.out.println("Voi returna catre client rezultatul: "+line);
                fluxOut.println(line);
            }

            s.close();
        }
    }
    
    public static double calcul(String operatie) {
        String token = "";
        
        if(operatie.indexOf("+")!=-1) {
            token = "+";
        } else if (operatie.indexOf("-")!=-1) {
            token = "-";
        } else if (operatie.indexOf("*")!=-1) {
            token = "*";
        } else if (operatie.indexOf("/")!=-1) {
            token = "/";
        }
        
        StringTokenizer st = new StringTokenizer(operatie, token);
        
        int op1 = Integer.parseInt(st.nextToken().trim());
        int op2 = Integer.parseInt(st.nextToken().trim());
        double rez = 0;
        
        if (token.equals("+")) {
            rez = op1 + op2;
        } else if (token.equals("-")) {
            rez = op1 - op2;
        } else if (token.equals("*")) {
            rez = op1 * op2;
        } else if (token.equals("/")) {
            rez = op1 / op2;
        }
        
        return rez;
    }
}

// long milliseconde