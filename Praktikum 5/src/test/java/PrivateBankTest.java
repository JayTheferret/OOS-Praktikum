import de.example.praktikum5.bank.Payment;
import de.example.praktikum5.bank.PrivateBank;
import de.example.praktikum5.bank.exceptions.AccountAlreadyExistsException;
import de.example.praktikum5.bank.exceptions.AccountDoesNotExistException;
import de.example.praktikum5.bank.exceptions.TransactionAlreadyExistException;
import de.example.praktikum5.bank.exceptions.TransactionDoesNotExistException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeFalse;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class PrivateBankTest {

    PrivateBank a;
    Payment b,c,d;

    @BeforeEach
    void init() throws AccountAlreadyExistsException, IOException, TransactionAlreadyExistException, AccountDoesNotExistException {
        a = new PrivateBank("TestBank1",0.1,0.5);
        b = new de.example.praktikum5.bank.Payment("22.11.2021",1000,"Beschreibung1");
        c = new de.example.praktikum5.bank.Payment("22.11.2021",1000,"Beschreibung2");
        d = new de.example.praktikum5.bank.Payment("22.11.2021",-10,"Beschreibung3");
        a.createAccount("Account1");
        a.addTransaction("Account1",b);
        a.addTransaction("Account1",c);
        a.addTransaction("Account1",d);
    }

    @AfterEach
    void cleanup(){

        //delete files after each iteration
        File dir = new File("./files/");
        for(File file : dir.listFiles()){
            file.delete();
        }
    }

    @Test
    void Constructor_Test(){
        assertNotNull(a);

        assertEquals("TestBank1",a.getName());
        assertEquals(0.1,a.getIncomingInterest());
        assertEquals(0.5,a.getOutgoingInterest());
    }

    @Test
    void createAccount_Test() throws AccountAlreadyExistsException, IOException {
        File acc = new File("./files/Account1.json");
        assertTrue(acc.exists());

        assertThrows(AccountAlreadyExistsException.class,() -> {a.createAccount("Account1");});
       // assertEquals("Account already exists!!!",exception.getMessage());
    }

    @Test
    void addTransaction_Test() throws TransactionAlreadyExistException, AccountDoesNotExistException, IOException {
        assertTrue(a.containsTransaction("Account1",b));

        assertThrows(TransactionAlreadyExistException.class, () -> {a.addTransaction("Account1",b);});
        assertThrows(AccountDoesNotExistException.class, () -> {a.addTransaction("Unknown",b);});
    }

    @Test
    void removeTransaction_Test() throws TransactionDoesNotExistException, IOException {
        //assertDoesNotThrow(..)
        a.removeTransaction("Account1",b);
        assertFalse(a.containsTransaction("Account1",b));

        assertThrows(TransactionDoesNotExistException.class, () -> {a.removeTransaction("Account1", b);});
    }

    @Test
    void containsTransactions_Test(){
        assertTrue(a.containsTransaction("Account1",b));

        //Gegenteil Testen -> danach hinzufügen nochmal testen
    }

    @Test
    void getAccountBalance_Test(){
        assertEquals(1785,a.getAccountBalance("Account1"),"Account Balance false");

        //Gegenteil Testen -> danach hinzufügen nochmal testen
    }

    @Test
    void getTransactions_Test(){
        var list = a.getTransactions("Account1");
        assertTrue(list.contains(b));
        assertTrue(list.contains(c));
        assertEquals(3,list.size());

    }

    @ParameterizedTest
    @ValueSource (booleans = {true,false})
    void getTransactionsSorted_Test(boolean asc){
        var trans = a.getTransactionsSorted("Account1",asc);

        for(int i = 1; i < trans.size(); i++){
            if(asc){
                assertTrue(trans.get(i).getAmount() >= trans.get(i-1).getAmount());
            }
            else{
                assertTrue(trans.get(i).getAmount() <= trans.get(i-1).getAmount());
            }
        }
    }

    @ParameterizedTest
    @ValueSource (booleans = {true,false})
    void getTransactionsByType_Test(boolean pos){
        var trans = a.getTransactionsByType("Account1", pos);

        for(var t:trans){
            if(pos) {
                assertTrue(t.getAmount() > 0);
            }
            else{
                assertTrue(t.getAmount() < 0);
            }
        }
    }
}
