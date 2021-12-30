package de.example.praktikum5.bank;

/**
 * class to handle outgoing Transfers
 * used to give negative values for outgoing Transfers
 */
public class OutgoingTransfer extends Transfer{

    public OutgoingTransfer(Transfer a) {
        super(a);
    }

    @Override
    public double calculate() {
        return (- this.getAmount());
    }
}
