/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.utcluj.ssatr.curs3.ssatr.ia.colectii.retea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.*;
/**
 *
 * @author mihai.hulea
 */
public class Client {
    public static void main(String[] args) throws IOException {
        System.out.println("Ma conectez la server.");
        Socket s = new Socket("127.0.0.1", 4050);
        System.out.println("Conexiune realizata!");
         //...... 
        BufferedReader fluxIn = new BufferedReader(new InputStreamReader(s.getInputStream()));
        PrintWriter fluxOut = new PrintWriter(new OutputStreamWriter(s.getOutputStream()),true);
        
        fluxOut.println("10 + 11");
        String response1 = fluxIn.readLine();
        System.out.println(response1);
        
        fluxOut.println("10 - 11");
        String response2 = fluxIn.readLine();
        System.out.println(response2);
        
        fluxOut.println("10 * 11");
        String response3 = fluxIn.readLine();
        System.out.println(response3);
        
        fluxOut.println("10 / 11");
        String response4 = fluxIn.readLine();
        System.out.println(response4);
        
        fluxOut.println("close connection");
        String response5 = fluxIn.readLine();
        System.out.println(response5);
        
        
        s.close();
    }
}
