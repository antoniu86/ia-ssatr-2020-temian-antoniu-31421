/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.utcluj.ssatr.bank.sim;

/**
 *
 * @author antoniu
 */
public class Test {
    public static void main(String[] args) {
        AccountsManager accounts = new AccountsManager();
        
        BankAccount a1 = new BankAccount("tony", 100);
        BankAccount a2 = new BankAccount("dan", 10);
        BankAccount a3 = new BankAccount("vlad", 60);
        
        accounts.addAccount(a1);
        accounts.addAccount(a2);
        accounts.addAccount(a3);
        
        accounts.list();
        
        accounts.transfer("tony", "dan", 130);
        
        accounts.list();
        
        accounts.transfer("tony", "dan", 30);
        
        accounts.list();
    }
}
