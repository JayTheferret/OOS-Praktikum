import de.example.praktikum5.bank.IncomingTransfer;
import de.example.praktikum5.bank.OutgoingTransfer;
import de.example.praktikum5.bank.Transfer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assumptions.assumeFalse;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class TransferTest {

    private Transfer a,b,c;

    @BeforeEach
    void init(){
        a = new Transfer("06.12.2021",100,"Beschreibung","Person1","Person2");
        b = new Transfer(a);
        c = new Transfer("06.12.2021",100,"Beschreibung","Person2","Person1");
    }

    @Test
    void constructor_Test() {

        assertNotNull(a);
        assertEquals("06.12.2021", a.getDate());
        assertEquals(100, a.getAmount());
        assertEquals("Beschreibung", a.getDescription());
        assertEquals("Person1",a.getSender());
        assertEquals("Person2", a.getRecipient());
    }

    @Test
    void copy_constructor_Test(){

        assertEquals(b.getDate(), a.getDate());
        assertEquals(b.getAmount(), a.getAmount());
        assertEquals(b.getDescription(), a.getDescription());
        assertEquals(b.getSender(),a.getSender());
        assertEquals(b.getRecipient(), a.getRecipient());
    }

    @Test
    void calculate_Test(){
        var d = new IncomingTransfer(a);
        var e = new OutgoingTransfer(b);

        assertEquals(100,d.calculate());
        assertEquals(-100,e.calculate());
    }

    @Test
    void equalsTest(){
        assertTrue(a.equals(b));
        assertFalse(a.equals(c));
    }

    @Test
    void toString_Test(){
        assertEquals( "  --------------Transfer--------------" + "\n"
                        +"  Date:              " + a.getDate() + "\n"
                        + "  Amount:            " + a.getAmount()+ "\n"
                        + "  New Amount:        " + a.calculate() + "\n"
                        + "  Description:       " + a.getDescription() + "\n"
                        + "  Sender:            " + a.getSender() + "\n"
                        + "  Recipient:         " + a.getRecipient() + "\n"
                        +"  -----------------------------------",
                a.toString());
    }
}
