package de.example.praktikum5.bank;

public class Transfer extends Transaction{

    private String sender = "unknown"; //Sender
    private String recipient = "unknown"; //Empfänger

    /**
     * returns Sender of the Transfer
     * @return sender
     */
    public String getSender() {
        return sender;
    }
    /**
     * sets sender of the Transfer
     * @param sender sender ("unknown" if none is given)
     */
    public void setSender(String sender) {
        this.sender = sender;
    }

    /**
     * returns Recipient of the Transfer
     * @return recipient
     */
    public String getRecipient() {
        return recipient;
    }
    /**
     * sets recipient of the Transfer
     * @param recipient recipient ("unknown" if none is given)
     */
    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }


    /**
     * Constructor for class Transfer using minimal attributes
     * uses constructor of superclass
     * @param date date of Transfer (formatted as DD.MM.YYYY)
     * @param amount amount of Transfer (positive)
     * @param description description of the Transfer
     */
    public Transfer(String date, double amount, String description){
        super(date,amount,description);
    }

    /**
     * Constructor for class Transfer using all attributes
     * uses constructor of superclass and other constructor
     * @param date date of Transfer (formatted as DD.MM.YYYY)
     * @param amount amount of Transfer (positive)
     * @param description description of the Transfer
     * @param sender sender of the Transfer
     * @param recipient recipient of the Transfer
     */
    public Transfer(String date, double amount, String description, String sender, String recipient) {
        this(date,amount,description);
        this.sender = sender;
        this.recipient = recipient;
    }

    //copy constructor -> make new object based on old object

    /**
     * Copy Constructor for Transfer obj, creates new transfer based on other Transfer (a)
     * uses other constructor
     * @param a Transfer object on which new one will be based
     */
    public Transfer(Transfer a){
        this(a.date,a.amount,a.description,a.sender,a.recipient);
    }

    /**
     * overrides toString method, outputs Transfer to Terminal
     * uses superclass method
     * @return output to Terminal
     */
    @Override
    public String toString(){
        return( "  --------------Transfer--------------" + "\n"
                + super.toString()
                + "  Sender:            " + this.sender + "\n"
                + "  Recipient:         " + this.recipient) + "\n"
                +"  -----------------------------------";
    };

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
        Transfer trans = (Transfer) obj;
        return(    super.equals(obj)
                && this.sender.equals(trans.sender)
                && this.recipient.equals(trans.recipient));
    }
}
