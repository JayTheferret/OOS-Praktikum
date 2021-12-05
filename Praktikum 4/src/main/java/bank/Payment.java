package bank;

import java.io.ObjectStreamException;

public class Payment extends Transaction{

    private double incomingInterest = 0;
    private double outgoingInterest = 0;

    /**
     *returns the IncomingInterest for deposits
     * @return incomingInterest
     */
    public double getIncomingInterest() {
        return incomingInterest;
    }
    /**
     * sets IncomingInterest for deposits
     * @param incomingInterest incomingInterest to be set (value from 0 to 1)
     */
    public void setIncomingInterest(double incomingInterest) {
        this.incomingInterest = incomingInterest;
    }

    /**
     * returns OutgoingInterest for withdrawals
     * @return outgoingInterest
     */
    public double getOutgoingInterest() {
        return outgoingInterest;
    }
    /**
     * sets outgoingInterest for withdrawals
     * @param outgoingInterest outgoingInterest to be set (value from 0 to 1)
     */
    public void setOutgoingInterest(double outgoingInterest) {
        this.outgoingInterest = outgoingInterest;
    }


    /**
     * Constructor for class Payment using minimal attributes
     * uses constructor of superclass
     * @param date date of Payment (formatted as DD.MM.YYYY)
     * @param amount amount of Payment (can be positive or negative)
     * @param description description of the payment
     */
    public Payment(String date, double amount, String description){
        super(date,amount,description);
    }
    /**
     * Constructor for class Payment using all attributes //(calls calculate to calculate new amount)
     * uses constructor of superclass and other constructor
     * @param date date of Payment (formatted as DD.MM.YYYY)
     * @param amount amount of Payment (can be positive or negative)
     * @param description description of the payment process
     * @param incomingInterest incoming Interest due for deposit
     * @param outgoingInterest outgoing Interest due for withdrawal
     */
    public Payment(String date, double amount, String description, double incomingInterest, double outgoingInterest) {
        this(date,amount,description); //use other constructor to not duplicate code
        this.incomingInterest = incomingInterest;
        this.outgoingInterest = outgoingInterest;

        //this.calculate(); //falls ueberschrieben werden soll
    }
    /**
     * Copy Constructor for Payment obj, creates new Payment based on other Payment (a)
     * uses other constructor
     * @param a Payment object on which new one will be based
     */
    public Payment(Payment a){
        this(a.date,a.amount,a.description,a.incomingInterest,a.outgoingInterest);
    }

    /**
     * calculates new amount based on incomingInterest(if amount is positive) or OutgoingInterest(if amount is negative)(sets calculated amount as amount)
     * @return new calculated amount
     */
    public double calculate(){
        double new_amount = 0;
        if(this.amount > 0){ //positiv -> Einzahlung
            new_amount = amount - (amount*incomingInterest);
        }
        else if( this.amount < 0){ //negativ -> Auszahlung
            new_amount= amount + (amount*outgoingInterest);
        }
        else {
            System.out.println("Betrag ungültig."); // Payment = 0
        }

       //this.setAmount(new_amount); //? überschreiben sonst einfach ausgeben -> hierfür im konstruktor bereits aufrufen

        return new_amount;};

    /**
     *overrides toString method, outputs Payment to Terminal
     * uses superclass method
     * @return output to Terminal
     */
    @Override
    public String toString(){
        return ( "\n  --------------Payment-------------- \n"
                + super.toString()
                + "  Incoming Interest: " + this.incomingInterest + "\n"
                + "  Outgoing Interest: " + this.outgoingInterest + "\n"
                + "  -----------------------------------") ;
    }

    /**
     * overrides equal method, compares attributes from two objects
     * @param obj object to be compared with
     * @return true if objects have the same contents, false if they have different contents
     */
    @Override
    public  boolean equals(Object obj){
        if(obj == this){
            return (true);
        }
        if(obj == null || this.getClass() != obj.getClass()){
            return (false);
        }
        Payment pay = (Payment) obj;
        return(    super.equals(obj)
                && this.outgoingInterest == pay.outgoingInterest
                && this.incomingInterest == pay.incomingInterest);
    }
}
