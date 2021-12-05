package bank;

public class Transaction implements CalculateBill{
    //neue Oberklasse für Payment und Transfer

    protected String date;
    protected double amount;
    protected String description; //Beschreibung des Vorgangs

    //getter and setter

    // getter und setter nutzen statt object.attribut -> getter brauchen keinen javadoc
    /**
     * returns the date of the Transaction
     * @return date
     */
    public String getDate() {
        return date;
    }

    /**
     * sets the date of the Transaction
     * @param date date of the Transaction (formatted as DD.MM.YYYY)
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     *returns the amount of the Transaction
     * @return amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * sets the amount of the Transaction
     * @param amount amount of the Transaction (positive)
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * returns the description of the Transaction
     * @return description of the Transaction process
     */
    public String getDescription() {
        return description;
    }

    /**
     * sets the description of the Transaction
     * @param description description of the Transaction process
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Constructor for class Transaction
     * @param date date of Transaction (formatted as DD.MM.YYYY)
     * @param amount amount of transaction (positive or negative)
     * @param description description of the Transaction process
     */
    public Transaction(String date, double amount, String description){ //allgemeiner konstruktor

        this.date = date;
        this.amount = amount;
        this.description = description;
    }

    /**
     * base method for calculating of final amount (doesn't need to change for Transfer)
     * @return amount unchanged
     */
    public double calculate(){
        return amount;};

    /**
     * overrides toString method, outputs Transaction to Terminal
     * (uses @see calculate() if amount shouldn't be overridden)
     * @return output of Transaction to Terminal
     */
    @Override
    public String toString(){

        return (  "  Date:              " + this.date + "\n"
                + "  Amount:            " + this.amount + "\n"
                + "  New Amount:        " + this.calculate() + "\n"
                + "  Description:       " + this.description + "\n");
    }

    //equal bei string -> Ueberprueft Inhalt/ == bei int /double etc.

    /**
     * overrides euqal method, compares attributes from two objecdts
     * @param obj object to be compared with
     * @return true if objects have the same contents, false if they have different contents
     */
    @Override
   public boolean equals(Object obj){
        Transaction trans = (Transaction) obj;
        return (    this.date.equals(trans.date)
                 && this.amount == trans.amount
                 && this.calculate() == trans.calculate()
                 && this.description.equals(trans.description));
   };
}
