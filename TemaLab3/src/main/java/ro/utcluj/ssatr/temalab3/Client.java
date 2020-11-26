/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.utcluj.ssatr.temalab3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.*;

/**
 *
 * @author antoniu
 */

public class Client {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Ma conectez la server.");
        Socket s = new Socket("127.0.0.1", 4050);
        System.out.println("Conexiune realizata!");
        
        BufferedReader fluxIn = new BufferedReader(new InputStreamReader(s.getInputStream()));
        PrintWriter fluxOut = new PrintWriter(new OutputStreamWriter(s.getOutputStream()),true);
        
        fluxOut.println("cj-01-abc");
        String response1 = fluxIn.readLine();
        System.out.println(response1);
        
        Thread.sleep(5000);
        
        fluxOut.println("cj-01-abc");
        String response2 = fluxIn.readLine();
        System.out.println(response2);
        
        Thread.sleep(2000);
        
        fluxOut.println("close connection");
        String response_close = fluxIn.readLine();
        System.out.println(response_close);
        
        s.close();
    }
}
