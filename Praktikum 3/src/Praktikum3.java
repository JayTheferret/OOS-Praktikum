import bank.Payment;
import bank.Transfer;
import bank.exceptions.AccountAlreadyExistsException;
import bank.exceptions.AccountDoesNotExistException;
import bank.exceptions.TransactionAlreadyExistException;
import bank.exceptions.TransactionDoesNotExistException;

import java.util.Arrays;
import java.util.Scanner;

public class Praktikum3 {
    public static void main(String [] args){
        System.out.println("Praktikum 3");
        System.out.println(" 1 - print Private Bank (print(toString))");
        System.out.println(" 2 - test equals");
        System.out.println("-------------------------------------------");
        System.out.println(" 3 - create 2 accounts(with only Name)in Bank1");
        System.out.println(" 4 - create account(including Transactions) in Bank1");
        System.out.println(" 5 - Account already exists throw");
        System.out.println(" 6 - Add transaction c to Account1)");
        System.out.println(" 7 - transaction already exists throw");
        System.out.println(" 8 - Account does not exist throw");
        System.out.println(" 9 - remove Transaction c from Account1");
        System.out.println("10 - Transaction does not exist throw");
        System.out.println("11 - Account contains Transaction");
        System.out.println("-------------------------------------------");
        System.out.println("12 - getAccountBalance (Payment 10, incomingTransfer 100, outgoingTransfer 1000");
        System.out.println("13 - getAccountBalance (Payment 10, incomingTransfer 100, outgoingTransfer 1000 (PrivateBankAlt)");
        System.out.println("-------------------------------------------");
        System.out.println("14 - getTransactions");
        System.out.println("15 - Transactions sorted ascending");
        System.out.println("16 - Transactions sorted descending");
        System.out.println("17 - only positive Transactions");
        System.out.println("18 - only negative Transactions");
        System.out.println("0 - Stop");

        Scanner newinput = new Scanner(System.in);
        int input = 1;

        do{
            bank.PrivateBank a = new bank.PrivateBank("TestBank1",0.1,0.5);
            bank.PrivateBank b = new bank.PrivateBank("TestBank2",0.2,0.4);
            bank.PrivateBankAlt alt = new bank.PrivateBankAlt("TestBankAlt",0.1,0.5);

            bank.Payment c = new bank.Payment("22.11.2021",1000,"c");
            bank.Payment d = new bank.Payment("22.11.2021",2000,"d");

            bank.Payment Payment = new bank.Payment("22.11.2021",10,"Payment");
            bank.Transfer Transferin = new bank.Transfer("22.11.2021",100,"Transferin","random","Account1");
            bank.Transfer Transferout = new bank.Transfer("22.11.2021",1000,"Transferout", "Account1","random");
            bank.IncomingTransfer incomingTransfer = new bank.IncomingTransfer(Transferin);
            bank.OutgoingTransfer outgoingTransfer = new bank.OutgoingTransfer(Transferout);

            input = newinput.nextInt();
            try {
                switch(input){

                    case 1:
                        System.out.println("print Private Bank (print(toString)):");
                        System.out.println(a.toString());
                        break;

                    case 2:
                        System.out.println("test equals:");
                        System.out.println("Bank1 equals Bank1: " + a.equals(a));
                        System.out.println("Bank1 equals Bank2: " + a.equals(b));
                        break;

                    case 3:
                        System.out.println("create 2 accounts(with only Name)in Bank1:");
                        a.createAccount("Account1");
                        a.createAccount("Account2");
                        System.out.println(a.toString());
                        break;

                    case 4:
                        System.out.println("create account(including Transactions) in Bank1:");
                        a.createAccount("Account1", Arrays.asList(c,d));
                        System.out.println(a.toString());
                        break;

                    case 5:
                        System.out.println("Account already exists throw:");
                        a.createAccount("Account1");
                        a.createAccount("Account1");
                        break;

                    case 6:
                        System.out.println("Add transaction c to Account1):");
                        a.createAccount("Account1");
                        a.addTransaction("Account1",c);
                        System.out.println(a.toString());
                        break;

                    case 7:
                        System.out.println("transaction already exists throw:");
                        a.createAccount("Account1");
                        a.addTransaction("Account1",c);
                        a.addTransaction("Account1",c);
                        break;

                    case 8:
                        System.out.println("8 - Account does not exist throw:");
                        a.addTransaction("Account1",c);
                        break;

                    case 9:
                        System.out.println("remove Transaction c from Account1:");
                        a.createAccount("Account1");
                        a.addTransaction("Account1",c);
                        System.out.println("before removal: \n" + a.toString());
                        a.removeTransaction("Account1",c);
                        System.out.println("after removal: \n" + a.toString());
                        break;

                    case 10:
                        System.out.println("Transaction does not exist throw:");
                        a.createAccount("Account1");
                        a.removeTransaction("Account1",c);
                        break;

                    case 11:
                        System.out.println("11 - Account1 contains Transaction c");
                        a.createAccount("Account1");
                        a.addTransaction("Account1",c);
                        System.out.println(a.toString() + a.containsTransaction("Account1",c));
                        break;

                    case 12:
                        System.out.println("12 - getAccountBalance (Payment 10, incomingTransfer 100, outgoingTransfer 1000");
                        a.createAccount("Account1");
                        a.addTransaction("Account1", Payment);
                        a.addTransaction("Account1", incomingTransfer);
                        a.addTransaction("Account1", outgoingTransfer);
                        System.out.println(a.toString() + "\n" + "AccountBalance: " + a.getAccountBalance("Account1"));
                        break;

                    case 13:
                        System.out.println("getAccountBalance (Payment 10, incomingTransfer 100, outgoingTransfer 1000 (PrivateBankAlt):");
                        alt.createAccount("Account1");
                        alt.addTransaction("Account1", Payment);
                        alt.addTransaction("Account1", Transferin);
                        alt.addTransaction("Account1", Transferout);
                        System.out.println(alt.toString() + "\n" + "AccountBalance: " + alt.getAccountBalanceAlt("Account1"));
                        break;

                    case 14:
                        System.out.println("getTransactions: ");
                        a.createAccount("Account1");
                        a.addTransaction("Account1", Payment);
                        a.addTransaction("Account1", incomingTransfer);
                        a.addTransaction("Account1", outgoingTransfer);

                        System.out.println(a.getTransactions("Account1"));
                        break;

                    case 15:
                        System.out.println("Transactions sorted ascending:");
                        a.createAccount("Account1");
                        a.addTransaction("Account1", Payment);
                        a.addTransaction("Account1", incomingTransfer);
                        a.addTransaction("Account1", outgoingTransfer);

                        System.out.println(a.getTransactionsSorted("Account1",true));
                        break;

                    case 16:
                        System.out.println("16 - Transactions sorted descending:");
                        a.createAccount("Account1");
                        a.addTransaction("Account1", Payment);
                        a.addTransaction("Account1", incomingTransfer);
                        a.addTransaction("Account1", outgoingTransfer);

                        System.out.println(a.getTransactionsSorted("Account1",false));
                        break;

                    case 17:
                        System.out.println("only positive Transactions:");
                        a.createAccount("Account1");
                        a.addTransaction("Account1", Payment);
                        a.addTransaction("Account1", incomingTransfer);
                        a.addTransaction("Account1", outgoingTransfer);

                        System.out.println(a.getTransactionsByType("Account1",true));
                        break;

                    case 18:
                        System.out.println("only negative Transactions:");
                        a.createAccount("Account1");
                        a.addTransaction("Account1", Payment);
                        a.addTransaction("Account1", incomingTransfer);
                        a.addTransaction("Account1", outgoingTransfer);

                        System.out.println(a.getTransactionsByType("Account1",false));
                        break;

                }
            } catch (   AccountAlreadyExistsException |
                        AccountDoesNotExistException |
                        TransactionDoesNotExistException |
                        TransactionAlreadyExistException e) {
                e.printStackTrace();
            }

        }while(input != 0);
    }
}
