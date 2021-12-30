package de.example.praktikum5.bank.exceptions;

public class TransactionDoesNotExistException extends Exception{
        public TransactionDoesNotExistException() {
            super("Transaction does not exist!!!");
        }
    }
