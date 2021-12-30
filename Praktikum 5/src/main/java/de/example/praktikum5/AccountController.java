package de.example.praktikum5;

import de.example.praktikum5.bank.PrivateBank;
import de.example.praktikum5.bank.Transaction;
import de.example.praktikum5.bank.exceptions.TransactionDoesNotExistException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;

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
        var answer = dialog.showAndWait();

        if(answer.get()== ButtonType.OK){
            System.out.println("Lösche Transaktion " + trans );
            bank.removeTransaction(account,(Transaction) trans);
            refresh();
        }
    }

    @FXML
    public void back(ActionEvent actionEvent) {

        System.out.println("back");
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
}

