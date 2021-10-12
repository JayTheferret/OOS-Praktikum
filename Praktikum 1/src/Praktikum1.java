import java.util.Scanner;

public class Praktikum1 {
    public static void main(String [] args){

        System.out.println("Erstelle Payment mit Konstruktor 1:");
        bank.Payment a = new bank.Payment("25.10.2021",12.50,"Hier koennte Ihre Werbung stehen.");
        a.printObject();

        System.out.println("Erstelle Payment mit Konstruktor 2:");
        bank.Payment b = new bank.Payment("25.10.2021",12.50,"Hier koennte Ihre Werbung stehen.",0.1,0.2);
        b.printObject();

        System.out.println("Erstelle Payment mit Copy-Konstruktor: (kopiere vorherigen)");
        bank.Payment c = new bank.Payment(b);
        c.printObject();

        //-----------------

        System.out.println("Erstelle Transfer mit Konstruktor 1:");
        bank.Transfer d = new bank.Transfer("25.10.2021", 12.50,"Hier koennte Ihre Werbung stehen.");
        d.printObject();

        System.out.println("Erstelle Transfer mit Konstruktor 2:");
        bank.Transfer e = new bank.Transfer("25.10.2021", 12.50,"Hier koennte Ihre Werbung stehen.", "Sender", "Empfaenger");
        e.printObject();

        System.out.println("Erstelle Transfer mit Copy-Konstruktor: (kopiere vorherigen)");
        bank.Transfer f = new bank.Transfer(e);
        f.printObject();

    }
}
