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
public class AccountsManager {
    BankAccount[] accounts = new BankAccount[10];
    
    void addAccount(BankAccount a) {
        for (int i = 0; i < accounts.length; i++){ 
           if (accounts[i] == null){
               accounts[i] = a;
               System.out.println("New account added.");
               return;
           }           
       }
        
       System.out.println("No empty position.");
    }
    
    int getTotalBalance() {
       int total = 0;
        
       for (BankAccount ba: accounts){
           if (ba != null){
               total += ba.getBalance();
           }           
       }
                
       return total;
    }
    
    void list() {
        for (BankAccount ba: accounts){
            if (ba != null){
                System.out.println("Account owner "+ba.getOwner()+" has "+ba.getBalance()+" balance.");
            }           
        }
    }
    
    boolean transfer(String fromOwnerName, String toOwnerName, int amount) {
        BankAccount[] owners = new BankAccount[2];
        
        for (BankAccount ba: accounts){
            if(ba!=null && ba.getOwner().equals(fromOwnerName)) {
                owners[0] = ba;
            }
        
            if(ba!=null && ba.getOwner().equals(toOwnerName)) {
                owners[1] = ba;
            }
        }
        
        if (owners[0] == null || owners[1] == null) {
            return false;
        }
        
        if (owners[0].getBalance() >= amount) {
            owners[0].setBalance(owners[0].getBalance() - amount);
            owners[1].setBalance(owners[1].getBalance() + amount);
            return true;
        }
        
        return false;
    }
    
    BankAccount[] getAccounts() {
        return accounts;
    }
}
