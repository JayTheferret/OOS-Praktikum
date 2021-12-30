import de.example.praktikum5.bank.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assumptions.assumeFalse;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class PaymentTest {

    private Payment a,b,c;

    @BeforeEach
    void init(){
        a = new Payment("06.12.2021", 100, "Beschreibung", 0.1,0.5);
        b = new Payment(a);
        c = new Payment("06.12.2021", -100, "BeschreibungAlternative", 0.1,0.5);
    }

    @Test
    void constructor_Test() {

        assertNotNull(a);
        assertEquals("06.12.2021", a.getDate());
        assertEquals(100, a.getAmount());
        assertEquals("Beschreibung", a.getDescription());
        assertEquals(0.1,a.getIncomingInterest());
        assertEquals(0.5, a.getOutgoingInterest());
    }

    @Test
    void copy_constructor_Test(){

        assertEquals(b.getDate(), a.getDate());
        assertEquals(b.getAmount(), a.getAmount());
        assertEquals(b.getDescription(), a.getDescription());
        assertEquals(b.getIncomingInterest(),a.getIncomingInterest());
        assertEquals(b.getOutgoingInterest(), a.getOutgoingInterest());

        //original verändern gucken das neu nich tändert
    }

    @Test
    void calculate_Test(){

        assertEquals(90,a.calculate());
        assertEquals(-150,c.calculate());
    }

    @Test
    void equalsTest(){
        assertTrue(a.equals(b));
        assertFalse(a.equals(c));
    }

    @Test
    void toString_Test(){
        assertEquals( "\n  --------------Payment-------------- \n"
                    + "  Date:              " + a.getDate() + "\n"
                    + "  Amount:            " + a.getAmount()+ "\n"
                    + "  New Amount:        " + a.calculate() + "\n"
                    + "  Description:       " + a.getDescription() + "\n"
                    + "  Incoming Interest: " + a.getIncomingInterest() + "\n"
                    + "  Outgoing Interest: " + a.getOutgoingInterest() + "\n"
                + "  -----------------------------------",
                a.toString());
    }
}