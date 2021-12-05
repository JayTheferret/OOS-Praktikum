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
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Praktikum3 {
    public static void main(String [] args) throws AccountAlreadyExistsException, TransactionAlreadyExistException, AccountDoesNotExistException, IOException {

       var GsonTest = new PrivateBank("GsonTestBank", 0.1,0.5);

       /*var GPayment = new Payment("1.1", 100, "test");
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
       GsonTest.addTransaction("SoXX", GTransfer);*/

       System.out.println(GsonTest);
    }
}
