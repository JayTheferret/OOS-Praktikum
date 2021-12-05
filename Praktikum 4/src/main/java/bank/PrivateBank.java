package bank;

import bank.exceptions.AccountDoesNotExistException;
import bank.exceptions.AccountAlreadyExistsException;
import bank.exceptions.TransactionAlreadyExistException;
import bank.exceptions.TransactionDoesNotExistException;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import javax.swing.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * class for a bank. Provides multiple methods to handle the interaction between
 *  * accounts and transactions.
 *  and sorts lists of Transactions
 *  implements the interface bank
 */
public class PrivateBank implements Bank {

    private static GsonBuilder builder = new GsonBuilder()
            .registerTypeAdapter(Payment.class, new CustomSerializer())
            .registerTypeAdapter(IncomingTransfer.class, new CustomSerializer())
            .registerTypeAdapter(OutgoingTransfer.class, new CustomSerializer())
            .registerTypeAdapter(Transaction.class, new CustomSerializer())
            .setPrettyPrinting();

    private String name;
    private double incomingInterest;
    private double outgoingInterest;
    private Map<String, List<Transaction>> accountsToTransactions = new HashMap<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getIncomingInterest() {
        return incomingInterest;
    }

    public void setIncomingInterest(double incomingInterest) {
        this.incomingInterest = incomingInterest;
    }

    public double getOutgoingInterest() {
        return outgoingInterest;
    }

    public void setOutgoingInterest(double outgoingInterest) {
        this.outgoingInterest = outgoingInterest;
    }

    public PrivateBank(String name, double incomingInterest, double outgoingInterest) throws AccountAlreadyExistsException, IOException {
        this.name = name;
        this.incomingInterest = incomingInterest;
        this.outgoingInterest = outgoingInterest;

        readAccounts();
    }

    public PrivateBank(PrivateBank a) throws AccountAlreadyExistsException, IOException {
        this(a.name, a.incomingInterest, a.outgoingInterest);
    }

    /**
     * overrides toString, outputs PrivateBank to terminal
     * @return output to terminal
     */
    @Override
    public String toString() {
        return ("------PrivateBank:------ \n"
                + "Name:             " + this.name + "\n"
                + "incomingInterest: " + this.incomingInterest + "\n"
                + "outgoingInterest: " + this.outgoingInterest + "\n"
                + "Transactions:     " + this.accountsToTransactions.toString()); //?
    }

    /**
     * overrides equals method
     * compares if to PrivateBanks are equal to eachother
     * @param obj PrivateBank to be compared to
     * @return true if PrivateBanks are identical, false if not
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return (true);
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return (false);
        }

        PrivateBank pbank = (PrivateBank) obj;
        return (this.name == pbank.name &&
                this.incomingInterest == pbank.incomingInterest &&
                this.outgoingInterest == pbank.outgoingInterest &&
                this.accountsToTransactions.equals(pbank.accountsToTransactions)); //nutzt pro gespeicherter transaction equals von transaction
    }

    /**
     * Adds an account with only a name and empty transaction list to the bank, using. If the account already exists,
     * an exception is thrown.
     *
     * @param account the account to be added
     * @throws AccountAlreadyExistsException
     */
    public void createAccount(String account) throws AccountAlreadyExistsException, IOException {
        createAccount(account, new ArrayList<>());
    }

    /**
     * Adds an account (with all specified transactions) to the bank. If the account already exists,
     * an exception is thrown.
     *
     * @param account the account to be added
     * @param transactions
     * @throws AccountAlreadyExistsException if the account already exists
     */
    public void createAccount(String account, List<Transaction> transactions) throws AccountAlreadyExistsException, IOException {
        if (accountsToTransactions.containsKey(account)) {
            throw new AccountAlreadyExistsException();
        }
        accountsToTransactions.put(account, transactions);

        writeAccount(account);
    }

    /**Adds a transaction to an account. If the specified account does not exist, an exception is
     * thrown. If the transaction already exists, an exception is thrown.
     *
     * @param account     the account to which the transaction is added
     * @param transaction the transaction which is added to the account
     * @throws TransactionAlreadyExistException if the transaction already exists
     * @throws AccountDoesNotExistException if the Account does not exist
     */
    public void addTransaction(String account, Transaction transaction) throws TransactionAlreadyExistException, AccountDoesNotExistException, IOException {

        if (!accountsToTransactions.containsKey(account)) {
            throw new AccountDoesNotExistException();
        }
        if (containsTransaction(account, transaction)) {
            throw new TransactionAlreadyExistException();
        }

        if (transaction instanceof Payment) {
            ((Payment) transaction).setIncomingInterest(this.getIncomingInterest());
            ((Payment) transaction).setOutgoingInterest(this.getOutgoingInterest());
        }
        accountsToTransactions.get(account).add(transaction);

        writeAccount(account);
    }

    /**
     * Removes a transaction from an account. If the transaction does not exist, an exception is
     * thrown.
     *
     * @param account     the account from which the transaction is removed
     * @param transaction the transaction which is added to the account
     * @throws TransactionDoesNotExistException  if the transaction cannot be found
     */
    public void removeTransaction(String account, Transaction transaction) throws TransactionDoesNotExistException, IOException {
        if (!containsTransaction(account, transaction)) {
            throw new TransactionDoesNotExistException();
        }

        accountsToTransactions.get(account).remove(transaction);

        writeAccount(account);
    }

    /**
     * Checks whether the specified transaction for a given account exists.
     *
     * @param account     the account from which the transaction is checked
     * @param transaction the transaction which is added to the account
     * @return true if transaction exists for given account, false if not
     */
    public boolean containsTransaction(String account, Transaction transaction) {
        return (accountsToTransactions.get(account).contains(transaction));
    }

    /**
     * Calculates and returns the current account balance.
     *
     * @param account the selected account
     * @return the current account balance
     */
    public double getAccountBalance(String account) {

     double balance = 0;

        for(Transaction transaction : accountsToTransactions.get(account)) {
            balance += transaction.calculate();
        }

        return balance;

    }

    /**
     * Returns a list of transactions for an account.
     *
     * @param account the selected account
     * @return the list of transactions
     */
    public List<Transaction> getTransactions(String account) {

        return accountsToTransactions.get(account);
    }

    /**
     * Returns a sorted list (-> calculated amounts) of transactions for a specific account . Sorts the list either in ascending or descending order
     * (or empty).
     *
     * @param account the selected account
     * @param asc     selects if the transaction list is sorted ascending or descending
     * @return the list of transactions
     */
    public List<Transaction> getTransactionsSorted(String account, boolean asc) {
        /*
        if(asc){
            accountsToTransactions.get(account).sort(Comparator.comparing(transaction -> transaction.calculate()));
        } else {
            accountsToTransactions.get(account).sort(Comparator.comparing(Transaction::calculate).reversed()); //macht das gleiche reversed
        }
        return accountsToTransactions.get(account); */

        //==============

        List<Transaction> newList = new ArrayList<>(accountsToTransactions.get(account));

        if (asc) {
            newList.sort(Comparator.comparing(transaction -> transaction.calculate()));
        } else {
            newList.sort(Comparator.comparing(Transaction::calculate).reversed()); //macht das gleiche reversed
        }
        return newList;
    }

    /**
     * Returns a list of either positive or negative transactions (-> calculated amounts).
     *
     * @param account  the selected account
     * @param positive selects if positive  or negative transactions are listed
     * @return the list of transactions
     */
    public List<Transaction> getTransactionsByType(String account, boolean positive) {
        //List<Transaction> newList = new ArrayList<>(accountsToTransactions.get(account));
        List<Transaction> posList = new ArrayList<>();
        List<Transaction> negList = new ArrayList<>();

        for (int i = 0; i < accountsToTransactions.get(account).size(); i++) {
            if (accountsToTransactions.get(account).get(i).calculate() >= 0) {
                posList.add(accountsToTransactions.get(account).get(i));
            } else {
                negList.add(accountsToTransactions.get(account).get(i));
            }
        }
        if (positive) return posList;
        else return negList;

        //accountsToTransactions.get(account).stream().filter(x -> x.calculate() >= 0).collect(Collectors.toList());
        // ====================
       /*
        List<Transaction> list = new ArrayList<>();

        for(Transaction transaction : accountsToTransactions.get(account)) {
            if ((transaction.calculate() >= 0) == positive) {
                list.add(transaction);
            }
        }
        return list;
        */

    }

    /**
     * method to read files, containing accounts and Transactions into a PrivateBank
     *
     * @throws IOException when file doesnt exist?
     * @throws AccountAlreadyExistsException if accounts to be added already exist in bank
     */
    private void readAccounts() throws IOException, AccountAlreadyExistsException {

        var customGson = builder.create();

        File dir = new File("./files");

        for(File file : dir.listFiles()){
            FileReader reader = new FileReader(file);
            JsonReader jreader = new JsonReader(reader);

            Transaction[] transarray = customGson.fromJson(jreader, Transaction[].class);
            this.createAccount(file.getName().replace(".json",""), Arrays.asList(transarray));

            reader.close();
        }
    }

    /**
     * method to write a account and its transactiobns into files deserializing them
     *
     * @param account the account to be deserialized
     * @throws IOException when file already exists?
     */
    private void writeAccount(String account) throws IOException{
        var customGson = builder.create();

        FileWriter writer = new FileWriter("./files/"+ account + ".json");
        customGson.toJson(this.getTransactions(account), writer);

        writer.close();
    }
}
