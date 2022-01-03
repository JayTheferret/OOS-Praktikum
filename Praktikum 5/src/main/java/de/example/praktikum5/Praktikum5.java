package de.example.praktikum5;

import de.example.praktikum5.bank.*;
import de.example.praktikum5.bank.exceptions.AccountAlreadyExistsException;
import de.example.praktikum5.bank.exceptions.AccountDoesNotExistException;
import de.example.praktikum5.bank.exceptions.TransactionAlreadyExistException;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.util.Scanner;

public class Praktikum5 extends Application {

    public void start(Stage stage) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(Praktikum5.class.getResource("/Mainview.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        stage.setTitle("PrivateBank");
        stage.setScene(scene);
        stage.show();
    }

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
                    de.example.praktikum5.bank.Transfer Transferin = new de.example.praktikum5.bank.Transfer("22.11.2021",100,"Transferin","random","Account1");
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

                case 4:
                    var DeleteTest = new PrivateBank("DeleteTest",0.5,0.5);
                    System.out.println("Deleting Account Jay");
                    DeleteTest.deleteAccount("Jay");
                    System.out.println(DeleteTest);
                    break;

                case 5:
                    var ListTest = new PrivateBank("DeleteTest",0.5,0.5);
                    System.out.println(ListTest.getAllAccounts());
            }

        }while(input != 0);

        launch(args);
    }
}
