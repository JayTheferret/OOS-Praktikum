package de.example.praktikum5.bank;

/**
 * class to handle Incoming Transfers
 * used to give positive values for incoming Transfers
 */
public class IncomingTransfer extends Transfer{

    public IncomingTransfer(Transfer a) {
        super(a);
    }

    @Override
    public double calculate() {
        return getAmount();
    }
}
