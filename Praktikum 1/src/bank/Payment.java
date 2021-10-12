package bank;

public class Payment {

    //überlegen ob public oder private sinnvoll
    String date; //Zeitpunkt -> Format: DD.MM.YYYY(muss nicht überprüft werden)
    double amount; //Geldmenge (kann auch negativ sein (Auszahlung))
    String description; //beschreibung des Vorgangs


    //Zinsen -> pos Wert von 0 bis 1
    double incomingInterest; //bei Einzahlung/Deposit
    double outgoingInterest; //bei Auszahlung/Withdrawal

    //getter and setter
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public double getIncomingInterest() {
        return incomingInterest;
    }
    public void setIncomingInterest(double incomingInterest) {
        this.incomingInterest = incomingInterest;
    }

    public double getOutgoingInterest() {
        return outgoingInterest;
    }
    public void setOutgoingInterest(double outgoingInterest) {
        this.outgoingInterest = outgoingInterest;
    }

    //constructor for attributes date, amount und description.
    public Payment(String date, double amount, String description) {
        this.date = date;
        this.amount = amount;
        this.description = description;
    }

    public Payment(String date, double amount, String description, double incomingInterest, double outgoingInterest) {
        this(date,amount,description); //use other constructor to not duplicate code
        this.incomingInterest = incomingInterest;
        this.outgoingInterest = outgoingInterest;
    }

    //copy constructor -> make new object based on old object
    public Payment(Payment a){
        date = a.date;
        amount = a.amount;
        description = a.description;
        incomingInterest = a.incomingInterest;
        outgoingInterest = a.outgoingInterest;
    }

    public void printObject(){
        System.out.print("Date: " + this.date);
        System.out.print("Amount: " + this.amount);
        System.out.print("Description: " + this.description);
        System.out.print("Incoming Interest: " + this.incomingInterest);
        System.out.print("Outgoing Interest: " + this.outgoingInterest);
    };
}
