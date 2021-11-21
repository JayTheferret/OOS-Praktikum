package bank;

import bank.exceptions.AccountAlreadyExistsException;
import bank.exceptions.AccountDoesNotExistException;
import bank.exceptions.TransactionAlreadyExistException;
import bank.exceptions.TransactionDoesNotExistException;

import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * class for a bank(alt version). Provides multiple methods to handle the interaction between
 *  * accounts and transactions.
 *  and sorts lists of Transactions
 *  implements the interface bank
 */
public class PrivateBankAlt implements Bank {

   private  String name;
   private double incomingInterest;
   private double outgoingInterest;
   private Map<String, List<Transaction>> accountsToTransactions = new HashMap<>();

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public double getIncomingInterest() {
        return incomingInterest;
    }
    public void setIncomingInterest(double incomingInterest) {
        this.incomingInterest = incomingInterest;
    }

    public double getOutgoingInterest() {
        return outgoingInterest;
    }
    public void setOutgoingInterest(double outgoingInterest) {
        this.outgoingInterest = outgoingInterest;
    }

    public PrivateBankAlt(String name, double incomingInterest, double outgoingInterest) {
        this.name = name;
        this.incomingInterest = incomingInterest;
        this.outgoingInterest = outgoingInterest;
    }

    public PrivateBankAlt(PrivateBankAlt a){
        this(a.name,a.incomingInterest,a.outgoingInterest);
    }

    @Override
    public String toString() {
        return ("PrivateBank: \n"
        + "Name: " + this.name + "\n"
        + "incomingInterest: " + this.incomingInterest + "\n"
        + "outgoingInterest: " + this.outgoingInterest + "\n"
        + "Transactions: " + this.accountsToTransactions.toString()); //?
    }

    @Override
    public boolean equals(Object obj){
        if(obj == this){
            return (true);
        }
        if(obj == null || this.getClass() != obj.getClass()){
            return (false);
        }

        PrivateBankAlt pbank = (PrivateBankAlt)obj;
        return(this.name == pbank.name &&
                this.incomingInterest == pbank.incomingInterest &&
                this.outgoingInterest == pbank.outgoingInterest &&
                this.accountsToTransactions.equals(pbank.accountsToTransactions)); //nutzt pro gespeicherter transaction equals von transaction
     }

    public void createAccount(String account) throws AccountAlreadyExistsException {
            createAccount(account, new ArrayList<>());
    }

    public void createAccount(String account, List<Transaction> transactions) throws AccountAlreadyExistsException {
            if(accountsToTransactions.containsKey(account)){
                throw new AccountAlreadyExistsException();
            }
            accountsToTransactions.put(account, transactions);
    }

    public void addTransaction(String account, Transaction transaction) throws TransactionAlreadyExistException, AccountDoesNotExistException {
            if(accountsToTransactions.get(account).contains(transaction)){
                throw new TransactionAlreadyExistException();
            }
            else if(!accountsToTransactions.containsKey(account)){
               throw new AccountDoesNotExistException();
            }

            if(transaction instanceof Payment){
                ((Payment) transaction).setIncomingInterest(this.getIncomingInterest());
                ((Payment) transaction).setOutgoingInterest(this.getOutgoingInterest());
            }
            accountsToTransactions.get(account).add(transaction);
    }

    public void removeTransaction(String account, Transaction transaction) throws TransactionDoesNotExistException {
         if(!accountsToTransactions.containsValue(transaction)){
            throw new TransactionDoesNotExistException();
         }

         accountsToTransactions.get(account).remove(transaction);
    }

    public boolean containsTransaction(String account, Transaction transaction) {
        return(accountsToTransactions.get(account).contains(transaction));
    }//-------

    public double getAccountBalance(String account) {

        double balance = 0;

        for(int i = 0; i < accountsToTransactions.get(account).size(); i++){
            balance += accountsToTransactions.get(account).get(i).calculate();
        }
        return balance;

        //============================

        /*
        double balance = 0;

        for(Transaction transaction : accountsToTransactions.get(account)) {
            balance += transaction.calculate();
        }

        return balance;
        */
    }

    public  double getAccountBalanceAlt(String account){

        /*double balance = 0;

        for(int i = 0; i < accountsToTransactions.get(account).size(); i++){

            if(accountsToTransactions.get(account).get(i) instanceof Transfer){
                if(account.equals(((Transfer) accountsToTransactions.get(account).get(i)).getSender())){
                    balance -= accountsToTransactions.get(account).get(i).calculate();
                }
                else if(!account.equals(((Transfer) accountsToTransactions.get(account).get(i)).getSender())){
                    balance += accountsToTransactions.get(account).get(i).calculate();
                }
            }
            else {//bei payment objekten und incoming
               balance += accountsToTransactions.get(account).get(i).calculate();
            }
        }*/

        double balance = 0.0;

        for (Transaction transaction : accountsToTransactions.get(account)) {

            if (transaction instanceof Transfer) {
                Transfer transfer = (Transfer) transaction;

                if (account.equals(transfer.getSender())) {
                    balance -= transfer.calculate();
                } else {
                    balance += transfer.calculate();
                }

            } else {
                balance += transaction.calculate();
            }
        }

        return balance;
    }

    public List<Transaction> getTransactions(String account) {

        return accountsToTransactions.get(account);
    }

    public List<Transaction> getTransactionsSorted(String account, boolean asc) {
        /*
        if(asc){
            accountsToTransactions.get(account).sort(Comparator.comparing(transaction -> transaction.calculate()));
        } else {
            accountsToTransactions.get(account).sort(Comparator.comparing(Transaction::calculate).reversed()); //macht das gleiche reversed
        }
        return accountsToTransactions.get(account); */

        //==============

        List<Transaction> newList = new ArrayList<>(accountsToTransactions.get(account));

         if(asc){
            newList.sort(Comparator.comparing(transaction -> transaction.calculate()));
        } else {
            newList.sort(Comparator.comparing(Transaction::calculate).reversed()); //macht das gleiche reversed
        }
        return newList;
    }

    public List<Transaction> getTransactionsByType(String account, boolean positive) {
        //List<Transaction> newList = new ArrayList<>(accountsToTransactions.get(account));
        List<Transaction> posList = new ArrayList<>();
        List<Transaction> negList = new ArrayList<>();

        for(int i = 0; i < accountsToTransactions.get(account).size(); i++){
                if(accountsToTransactions.get(account).get(i).calculate() >= 0){
                    posList.add(accountsToTransactions.get(account).get(i));
                }
                else{
                    negList.add(accountsToTransactions.get(account).get(i));
                }
            }
        if(positive) return posList;
        else return negList;

        // ====================
       /*
        List<Transaction> list = new ArrayList<>();

        for(Transaction transaction : accountsToTransactions.get(account)) {
            if ((transaction.calculate() >= 0) == positive) {
                list.add(transaction);
            }
        }
        return list;
        */

    }
}