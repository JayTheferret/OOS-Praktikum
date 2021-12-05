import bank.*;
import bank.exceptions.AccountAlreadyExistsException;
import bank.exceptions.AccountDoesNotExistException;
import bank.exceptions.TransactionAlreadyExistException;
import bank.exceptions.TransactionDoesNotExistException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Praktikum4 {
    public static void main(String [] args) throws AccountAlreadyExistsException, TransactionAlreadyExistException, AccountDoesNotExistException, IOException {

        System.out.println("Praktikum 4");
        System.out.println("1 - erstelle Bank mit Accounts/Payments(manuel) -> serializieren");
        System.out.println("2 - erstelle Bank aus Files -> deserializieren");
        System.out.println("3 - Lösche Files in /files/");

        Scanner newinput = new Scanner(System.in);
        int input = 1;

        do{
            input = newinput.nextInt();
            switch(input){

                case 1:
                    System.out.println("erstelle Bank mit Accounts/Payments(manuel) -> serializieren:");
                    var GsonTest = new PrivateBank("GsonTestBank", 0.1,0.5);

                    var GPayment = new Payment("1.1", 100, "test");
                    var GPayment2 = new Payment("1.1", 100, "test2");
                    bank.Transfer Transferin = new bank.Transfer("22.11.2021",100,"Transferin","random","Account1");
                    var GTransfer = new IncomingTransfer(Transferin);

                    GsonTest.createAccount("Jay");
                    GsonTest.addTransaction("Jay", GPayment);
                    GsonTest.addTransaction("Jay", GPayment2);
                    GsonTest.addTransaction("Jay", GTransfer);

                    GsonTest.createAccount("SoXX");
                    GsonTest.addTransaction("SoXX", GPayment);
                    GsonTest.addTransaction("SoXX", GPayment2);
                    GsonTest.addTransaction("SoXX", GTransfer);

                    System.out.println(GsonTest);

                    break;

                case 2:
                    System.out.println("erstelle Bank aus Files -> deserializieren:");
                    var GsonTest2 = new PrivateBank("GsonTestBank", 0.1,0.5);
                    System.out.println(GsonTest2);

                    break;

                case 3:
                    File dir = new File("./files/");
                    for(File file : dir.listFiles()) {
                        System.out.println("deleting: " + file);
                        System.out.println(file.delete());
                        //break;
                    }
                    break;
            }
        }while(input != 0);
    }
}
