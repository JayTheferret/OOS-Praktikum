import java.util.Scanner;

public class Praktikum2 {
    public static void main(String [] args){

        System.out.println("1 - Erstelle Payment mit Konstruktor 1");
        System.out.println("2 - Erstelle Payment mit Konstruktor 2");
        System.out.println("3 - Erstelle Payment mit Copy-Konstruktor");
        System.out.println("4 - Erstelle Transfer mit Konstruktor 1");
        System.out.println("5 - Erstelle Transfer mit Konstruktor 2");
        System.out.println("6 - Erstelle Transfer mit Copy-Konstruktor");
        System.out.println("0 - Stop");

        Scanner newinput = new Scanner(System.in);
        int input = 1;

        do{
            input = newinput.nextInt();

            switch(input){

                case 1:
                    System.out.println("Erstelle Payment mit Konstruktor 1:");
                    bank.Payment a = new bank.Payment("25.10.2021",12.50,"Hier koennte Ihre Werbung stehen.");
                    a.printObject();
                    break;

                case 2:
                    System.out.println("Erstelle Payment mit Konstruktor 2:");
                    bank.Payment b = new bank.Payment("25.10.2021",12.50,"Hier koennte Ihre Werbung stehen.",0.1,0.2);
                    b.printObject();
                    break;

                case 3:
                    System.out.println("Erstelle Payment mit Copy-Konstruktor:");
                    System.out.println("Zu kopieren:");
                    bank.Payment tocopy = new bank.Payment("25.10.2021",12.50,"Kopier mich.",0.1,0.2);
                    tocopy.printObject();
                    System.out.println("Kopie:");
                    bank.Payment c = new bank.Payment(tocopy);
                    c.printObject();
                    break;

                //-----------------

                case 4:
                    System.out.println("Erstelle Transfer mit Konstruktor 1:");
                    bank.Transfer d = new bank.Transfer("25.10.2021", 12.50,"Hier koennte Ihre Werbung stehen.");
                    d.printObject();
                    break;

                case 5:
                    System.out.println("Erstelle Transfer mit Konstruktor 2:");
                    bank.Transfer e = new bank.Transfer("25.10.2021", 12.50,"Hier koennte Ihre Werbung stehen.", "Sender", "Empfaenger");
                    e.printObject();
                    break;

                case 6:
                    System.out.println("Erstelle Transfer mit Copy-Konstruktor:");
                    System.out.println("Zu kopieren:");
                    bank.Transfer tocopy2 = new bank.Transfer("25.10.2021", 12.50,"Kopier mich.", "Sender", "Empfaenger");
                    tocopy2.printObject();
                    System.out.println("Kopie:");
                    bank.Transfer f = new bank.Transfer(tocopy2);
                    f.printObject();
                    break;

            }
        }while(input != 0);
    }
}
