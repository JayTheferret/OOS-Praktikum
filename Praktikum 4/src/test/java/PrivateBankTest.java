import bank.Payment;
import bank.PrivateBank;
import bank.exceptions.AccountAlreadyExistsException;
import bank.exceptions.AccountDoesNotExistException;
import bank.exceptions.TransactionAlreadyExistException;
import bank.exceptions.TransactionDoesNotExistException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeFalse;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class PrivateBankTest {

    PrivateBank a;
    Payment b,c;

    @BeforeEach
    void init() throws AccountAlreadyExistsException, IOException, TransactionAlreadyExistException, AccountDoesNotExistException {
        a = new PrivateBank("TestBank1",0.1,0.5);
        b = new bank.Payment("22.11.2021",1000,"c");
        c = new bank.Payment("22.11.2021",1000,"c");
        a.createAccount("Account1");
        a.addTransaction("Account1",b);
        a.addTransaction("Account1",c);
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
        assumeTrue(acc.exists());
    }

    @Test
    void addTransaction_Test() throws TransactionAlreadyExistException, AccountDoesNotExistException, IOException, AccountAlreadyExistsException {
        assumeTrue(a.containsTransaction("Account1",b));
    }

    @Test
    void removeTransaction_Test() throws TransactionDoesNotExistException, IOException {
        a.removeTransaction("Account1",b);
        assumeFalse(a.containsTransaction("Account1",b));
    }

    @Test
    void containsTransactions_Test(){
        //?
    }

    @Test
    void getAccountBalance_Test(){
        assertEquals(1800,a.getAccountBalance("Account1"));
    }

    @Test
    void getTransactions_Test(){
        //?
    }

    @Test
    void getTransactionsSorted_Test(){
        //?
    }

    @Test
    void getTransactionsByType_Test(){
        //?
    }
}
