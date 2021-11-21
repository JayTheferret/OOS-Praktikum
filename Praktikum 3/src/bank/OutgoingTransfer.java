package bank;

public class OutgoingTransfer extends Transfer{

    public OutgoingTransfer(Transfer a) {
        super(a);
    }

    @Override
    public double calculate() {
        return (- this.getAmount());
    }
}
