package bank;

public class Transfer {

    //ueberlegen ob public oder private sinnvoll
    String date; //Zeitpunkt -> Format: DD.MM.YYYY (muss nicht ueberprueft werden)
    double amount; //Geldmenge (nur positiv (-> muss nicht überprueft werden))
    String description; //Beschreibung des Vorgangs

    String sender = "unknown"; //Sender
    String recipient = "unknown"; //Empfänger

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

    public String getSender() {
        return sender;
    }
    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getRecipient() {
        return recipient;
    }
    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public Transfer(String date, double amount, String description) {
        this.date = date;
        this.amount = amount;
        this.description = description;
    }

    public Transfer(String date, double amount, String description, String sender, String recipient) {
        this(date,amount,description);
        this.sender = sender;
        this.recipient = recipient;
    }

    //copy constructor -> make new object based on old object
    public Transfer(Transfer a){
        date = a.date;
        amount = a.amount;
        description = a.description;
        sender = a.sender;
        recipient = a.recipient;
    }

    public void printObject(){

        System.out.println("  --------------Payment--------------");
        System.out.println("  Date:              " + this.date);
        System.out.println("  Amount:            " + this.amount);
        System.out.println("  Description:       " + this.description);
        System.out.println("  Sender:            " + this.sender);
        System.out.println("  Recipient:         " + this.recipient);
        System.out.println("  -----------------------------------");
    };
}
