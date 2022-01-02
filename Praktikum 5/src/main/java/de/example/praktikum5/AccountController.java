package de.example.praktikum5;

import de.example.praktikum5.bank.*;
import de.example.praktikum5.bank.exceptions.AccountDoesNotExistException;
import de.example.praktikum5.bank.exceptions.TransactionAlreadyExistException;
import de.example.praktikum5.bank.exceptions.TransactionDoesNotExistException;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class AccountController {

    String account;
    PrivateBank bank;

    @FXML ListView<Transaction> list;
    @FXML private Label accountName;
    @FXML private Label balance;

   @FXML public void initialize(){
    }

    public  void refresh() {
        accountName.setText("Accountname: " + account);
        balance.setText("Kontostand: " + bank.getAccountBalance(account) );
        list.getItems().clear();
        list.getItems().addAll(bank.getTransactions(account));

    }
    @FXML
    public void deleteTrans() throws TransactionDoesNotExistException, IOException {
        var trans = list.getSelectionModel().getSelectedItem();

        if(trans == null){
            return;
        }

        Alert dialog = new Alert(Alert.AlertType.CONFIRMATION);


        dialog.setHeaderText("Do you really wanna delete the Transaction ?");

        ButtonType yesButton = new ButtonType("yes");
        ButtonType noButton = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

        dialog.getButtonTypes().setAll(yesButton,noButton);

        var answer = dialog.showAndWait();

        if(answer.get()== ButtonType.OK){
            System.out.println("Lösche Transaktion " + trans );
            bank.removeTransaction(account,(Transaction) trans);
            refresh();
        }
    }

    @FXML
    public void back(ActionEvent actionEvent) throws IOException {
        System.out.println("back Button pressed");

        /*Stage stage = (Stage) list.getScene().getWindow();
        var loader = new FXMLLoader(Praktikum5.class.getResource("/MainController.fxml"));
        Scene scene = new Scene(loader.load());

        AccountController ac = loader.getController();

        stage.setScene(scene);*/

        FXMLLoader fxmlLoader = new FXMLLoader(Praktikum5.class.getResource("/Mainview.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        Stage stage = (Stage) list.getScene().getWindow();
        stage.setTitle("PrivateBank");
        stage.setScene(scene);
        stage.show();
    }

    public void setInformation(String key, PrivateBank fbank){
        account = key;
        bank = fbank;
        refresh();
    }

    @FXML public void sortasc(){
        list.getItems().clear();
        list.getItems().addAll(bank.getTransactionsSorted(account, true));
    }

    @FXML public void sortdec(){
        list.getItems().clear();
        list.getItems().addAll(bank.getTransactionsSorted(account, false));
    }

    @FXML public void getpos(){
        list.getItems().clear();
        list.getItems().addAll(bank.getTransactionsByType(account,true));
    }

    @FXML public void getneg(){
        list.getItems().clear();
        list.getItems().addAll(bank.getTransactionsByType(account,false));
    }

    public void createTrans(ActionEvent actionEvent) throws InterruptedException, TransactionAlreadyExistException, AccountDoesNotExistException, IOException {
        System.out.println("create a new Transaction");

        var dialog = new Dialog<>();
        dialog.setTitle("new Transaction");
        dialog.getDialogPane().getScene().getWindow().sizeToScene();

        var addT = new ButtonType("add", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addT,ButtonType.CANCEL);

        var choices = new ArrayList<>();
        choices.add("Transfer");
        choices.add("Payment");

        var TType = new ChoiceBox<>();
        TType.getItems().addAll(choices);
        //TType.setValue("Transfer");

        var general = new GridPane();

        general.add(new Label("Transaction Type: "),0,0);
        general.add(TType,1,0);

        var date = new TextField();
        var amount = new TextField();
        var description = new TextField();

        //extra fields needed for Transfer
        var sender = new TextField();
        var reciever = new TextField();

        TType.addEventFilter(ActionEvent.ACTION,event->{
            System.out.println(TType.getSelectionModel().getSelectedItem() + " toggled");
            if(TType.getSelectionModel().getSelectedItem() == "Transfer"){
                var transfer = new GridPane();
                transfer.add(new Label("Transaction Type: "),0,0);
                transfer.add(TType,1,0);

                transfer.add(new Label("date: "),0,1);
                transfer.add(date,1,1);

                transfer.add(new Label("amount: "),0,2);
                transfer.add(amount,1,2);

                transfer.add(new Label("description: "),0,3);
                transfer.add(description,1,3);

                transfer.add(new Label("sender: "),0,4);
                transfer.add(sender,1,4);

                transfer.add(new Label("reciever"),0,5);
                transfer.add(reciever,1,5);

                dialog.getDialogPane().setContent(transfer);
                dialog.getDialogPane().getScene().getWindow().sizeToScene();
            }
            if(TType.getSelectionModel().getSelectedItem()== "Payment"){
                var payment = new GridPane();
                payment.add(new Label("Transaction Type: "),0,0);
                payment.add(TType,1,0);

                payment.add(new Label("date: "),0,1);
                payment.add(date,1,1);

                payment.add(new Label("amount: "),0,2);
                payment.add(amount,1,2);

                payment.add(new Label("description: "),0,3);
                payment.add(description,1,3);

                dialog.getDialogPane().setContent(payment);
                dialog.getDialogPane().getScene().getWindow().sizeToScene();
            }
        });

        var alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText("Necessary information missing!");

        // set the grid and show dialog

        dialog.getDialogPane().setContent(general);

        //get input

       var result =  dialog.showAndWait();

       if(result.isPresent() && result.get() == addT){
           if(date.getText().isEmpty()||amount.getText().isEmpty()||description.getText().isEmpty()){
               System.out.println("fehlende Eingabe");
               alert.showAndWait();
               return;
           }
           else{
               System.out.println(date.getText()+amount.getText()+description.getText());
           }

           double damount = Double.parseDouble(amount.getText());

           if(TType.getSelectionModel().getSelectedItem() == "Payment"){
               var nPay = new Payment(date.getText(),damount,description.getText());
               bank.addTransaction(account,nPay);
           }

           if(TType.getSelectionModel().getSelectedItem() =="Transfer"){
               if(sender.getText().isEmpty()||reciever.getText().isEmpty()){
                   System.out.println("fehlende Eingabe");
                   alert.showAndWait();
                   return;
               }

               var trans = new Transfer(date.getText(),damount,description.getText(),sender.getText(),reciever.getText());
               if(account.equals(sender.getText())){
                   var nTrans = new OutgoingTransfer(trans);
                   bank.addTransaction(account,nTrans);
               }
               if(account.equals(reciever.getText())){
                   var ninTrans = new IncomingTransfer(trans);
                   bank.addTransaction(account,ninTrans);
               }
           }
           refresh();
       } else {
           System.out.println(result.get());
       }
    }
}

