package de.example.praktikum5.bank.exceptions;

public class AccountDoesNotExistException extends Exception{
    public AccountDoesNotExistException() {
        super("Account does not exists!!!");
    }
}
