/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.utcluj.ssatr.temalab3;

/**
 *
 * @author antoniu
 */

public class Masina {
    //atribute
    private String numar;
    private long intrare;

    public Masina(String numar, long intrare) {
        this.numar = numar;
        this.intrare = intrare;
    }

    public String getNumar() {
        return numar;
    }

    public long getIntrare() {
        return intrare;
    }
}
