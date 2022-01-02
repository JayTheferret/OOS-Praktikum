package de.example.praktikum5;

import de.example.praktikum5.bank.IncomingTransfer;
import de.example.praktikum5.bank.Payment;
import de.example.praktikum5.bank.PrivateBank;
import de.example.praktikum5.bank.Transfer;
import de.example.praktikum5.bank.exceptions.AccountAlreadyExistsException;
import de.example.praktikum5.bank.exceptions.AccountDoesNotExistException;
import de.example.praktikum5.bank.exceptions.TransactionAlreadyExistException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    @FXML ListView<String> AccountListe;
    private PrivateBank fxmlBank;

    @FXML public  void initialize() throws AccountAlreadyExistsException, IOException, TransactionAlreadyExistException, AccountDoesNotExistException {

        fxmlBank = new PrivateBank("FXML Bank",0.5,0.5);
        AccountListe.getItems().addAll(fxmlBank.getAllAccounts());
    }

    private void refresh(){
        AccountListe.getItems().clear();
        AccountListe.getItems().addAll(fxmlBank.getAllAccounts());
    }

    @FXML public void auswaehlen(ActionEvent event) throws IOException {

        if(AccountListe.getSelectionModel().getSelectedItem() == null){
            return;
        }

        System.out.println( AccountListe.getSelectionModel().getSelectedItem());

        FXMLLoader fxmlLoader = new FXMLLoader(Praktikum5.class.getResource("/Accountview.fxml"));

        Stage stage = (Stage) AccountListe.getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);

        AccountController ac = fxmlLoader.getController();
        ac.setInformation(AccountListe.getSelectionModel().getSelectedItem(), fxmlBank);
    }

    /**
     * method to delete Account in GUI
     *
     * @param event
     * @throws AccountDoesNotExistException
     * @throws IOException
     */
    @FXML public void loeschen(ActionEvent event) throws AccountDoesNotExistException, IOException {
        System.out.println("delete Button pressed");

        String account = AccountListe.getSelectionModel().getSelectedItem();

        if(account == null){
            return;
        }

        Alert dialog = new Alert(Alert.AlertType.CONFIRMATION);

        dialog.setTitle("Delete account");
        dialog.setHeaderText("Do you really wanna delete the Account: " + account +" ?");

        ButtonType yesButton = new ButtonType("yes");
        ButtonType noButton = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

        dialog.getButtonTypes().setAll(yesButton,noButton);

        var answer = dialog.showAndWait();

        if(answer.get()== yesButton){
            System.out.println("Lösche Account " + account );
            fxmlBank.deleteAccount(account);
            refresh();
        }
    }

    /**
     * method to create a new Account in GUI
     *
     * @param actionEvent
     * @throws AccountAlreadyExistsException
     * @throws IOException
     */
    public void newAccount(ActionEvent actionEvent) throws AccountAlreadyExistsException, IOException {
        System.out.println("add new Account Button pressed");

        TextInputDialog dialog = new TextInputDialog("Account Name");
        dialog.setTitle("Create a new Account");
        dialog.setHeaderText("choose a name for the account: ");

        var input = dialog.showAndWait();

        if(input.isPresent()){
            System.out.println("Chosen Account name: " + input.get());
            fxmlBank.createAccount(input.get());
            refresh();
        }
    }
}
