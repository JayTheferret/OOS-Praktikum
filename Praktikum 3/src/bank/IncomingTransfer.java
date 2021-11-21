package bank;

public class IncomingTransfer extends Transfer{

    public IncomingTransfer(Transfer a) {
        super(a);
    }

    @Override
    public double calculate() {
        return getAmount();
    }
}
