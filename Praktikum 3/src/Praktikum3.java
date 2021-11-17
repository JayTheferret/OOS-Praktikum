import bank.Payment;
import bank.Transfer;

import java.util.Scanner;

public class Praktikum3 {
    public static void main(String [] args){
        System.out.println("Konstruktoren + Ausgabe mit toString()");
        System.out.println("1 - Erstelle Payment mit Konstruktor 1");
        System.out.println("2 - Erstelle Payment mit Konstruktor 2");
        System.out.println("3 - Erstelle Payment mit Copy-Konstruktor");
        System.out.println("4 - Erstelle Transfer mit Konstruktor 1");
        System.out.println("5 - Erstelle Transfer mit Konstruktor 2");
        System.out.println("6 - Erstelle Transfer mit Copy-Konstruktor");
        System.out.println("------------------------------------------");
        System.out.println("7 - Test incomingInterest");
        System.out.println("8 - Test outgoingInterest");
        System.out.println("9 - equals()/== Test mit Copy7:");
        System.out.println("0 - Stop");


        Scanner newinput = new Scanner(System.in);
        int input = 1;

        do{
            input = newinput.nextInt();

            switch(input){

                case 1:
                    System.out.println("Erstelle Payment mit Konstruktor 1:");
                    bank.Payment a = new bank.Payment("25.10.2021",12.50,"Hier koennte Ihre Werbung stehen.");
                    System.out.println(a);
                    break;

                case 2:
                    System.out.println("Erstelle Payment mit Konstruktor 2:");
                    bank.Payment b = new bank.Payment("25.10.2021",12.50,"Hier koennte Ihre Werbung stehen.",0.1,0.2);
                    System.out.println(b);
                    break;

                case 3:
                    System.out.println("Erstelle Payment mit Copy-Konstruktor:");
                    System.out.println("Zu kopieren:");
                    bank.Payment tocopy = new bank.Payment("25.10.2021",12.50,"Kopier mich.",0.1,0.2);
                    System.out.println(tocopy);
                    System.out.println("Kopie:");
                    bank.Payment c = new bank.Payment(tocopy);
                    System.out.println(c);
                    break;

                //-----------------

                case 4:
                    System.out.println("Erstelle Transfer mit Konstruktor 1:");
                    bank.Transfer d = new bank.Transfer("25.10.2021", 12.50,"Hier koennte Ihre Werbung stehen.");
                    System.out.println(d);
                    break;

                case 5:
                    System.out.println("Erstelle Transfer mit Konstruktor 2:");
                    bank.Transfer e = new bank.Transfer("25.10.2021", 12.50,"Hier koennte Ihre Werbung stehen.", "Sender", "Empfaenger");
                    System.out.println(e);
                    break;

                case 6:
                    System.out.println("Erstelle Transfer mit Copy-Konstruktor:");
                    System.out.println("Zu kopieren:");
                    bank.Transfer tocopy2 = new bank.Transfer("25.10.2021", 12.50,"Kopier mich.", "Sender", "Empfaenger");
                    System.out.println(tocopy2);
                    System.out.println("Kopie:");
                    bank.Transfer f = new bank.Transfer(tocopy2);
                    System.out.println(f);
                    break;

                case 7:
                    System.out.println("7 - Test incomingInterest:");
                    bank.Payment g = new bank.Payment("08.11.2021",1000,"Test.",0.05,0);
                    System.out.println(g);
                    System.out.println("New amount: " + g.calculate());
                    break;

                case 8:
                    System.out.println(("8 - Test outgoingInterest:"));
                    bank.Payment h = new Payment("08.11.2021",-1000,"Test.",0,0.1);
                    System.out.println(h);
                    System.out.println("New amount: "+ h.calculate());
                    break;

                case 9:
                    System.out.println("equals()/== Test:");
                    bank.Payment i = new Payment("08.11.2021",1000,"Beispiel 1",0.05,0);
                    System.out.println("Original: \n" + i);
                    bank.Payment j = new bank.Payment(i);
                    System.out.println("Kopie: \n" + j);
                    System.out.println("Equal: " + i.equals(j));
                    System.out.println("== :   " + (i == j));
                    break;

            }
        }while(input != 0);


    }
}
