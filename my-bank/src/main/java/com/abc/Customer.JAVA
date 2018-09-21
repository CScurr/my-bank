import java.util.ArrayList;
import java.util.List;
import static java.lang.Math.abs;
/** 
 * Customer class 
 * Contains information on a customer and their accounts
 * @author Ciaran Scurr
 * @version 1.0
 * @date 21/09/1028
 */
public class Customer {
    private String name;
    private List<Account> accounts;
    /**
     * Constructor for objects of class Customer
     * @param customer name (String)
     */
    public Customer(String name) {
        this.name = name;
        this.accounts = new ArrayList<Account>();
    }
    
    /**
     * Returns the customers name
     * @return customer name (String)
     */
    public String getName() {
        return name;
    }

    /**
     * Sets up the customer with an account assuming they don't already own it
     * or it doesn't belong to another customer
     * @param the new account (Account)
     */
    public void openAccount(Account account) {
        boolean add = true;
        // Checks that the same account can't be added twice and two customers can't own
        // the same account
        for(Account a : accounts){
            if(a == account){
                add = false;
            }
        }
        
        if(add == true){
            accounts.add(account);
        }
        else{
            throw new IllegalArgumentException("Account cannot be added");
        }
        account.setOwner(this);
    }
    
    /**
     * Returns the number of accounts owner by the customer
     * @return number of accounts (int)
     */
    public int getNumberOfAccounts() {
        return accounts.size();
    }

    /**
     * Calculates total interest earned from all their accounts
     * @return total interest earned (double)
     */
    public double totalInterestEarned() {
        double total = 0;
        for (Account a : accounts)
            total += a.interestEarned();
        return total;
    }

    /**
     * Returns a string statement of all the activity of accounts belonging to the customer
     * @return statement (String)
     */
    public String getStatement() {
        String statement = null;
        statement = "Statement for " + name + "\n";
        double total = 0.0;
        for (Account a : accounts) {
            statement += "\n" + statementForAccount(a) + "\n";
            total += a.sumTransactions();
        }
        statement += "\nTotal In All Accounts " + toDollars(total);
        return statement;
    }
    
    /**
     * Returns the string statement for the activity of an account
     * @param account (Account)
     * @return statement (string)
     */
    private String statementForAccount(Account a) {
        String s = "";

        s += a.printAccountType();

        //Now total up all the transactions
        double total = 0.0;
        for (Transaction t : a.transactions) {
            s += "  " + (t.amount < 0 ? "withdrawal" : "deposit") + " " + toDollars(t.amount) + "\n";
            total += t.amount;
        }
        s += "Total " + toDollars(total);
        return s;
    }
    
    /**
     * Returns string of input in dollars
     * @param amount to be converted (double)
     * @String string of amount in dollars (String)
     */
    private String toDollars(double d){
        return String.format("$%,.2f", abs(d));
    }

    /**
     * Transfers money between accounts owned by the same customer
     * Transfer amount must be non negative and less than or equal to the amount in source account
     * @param amount of money being transerred (double)
     * @param source account (Account)
     * @param desination account (Account)
     */
    public void transferBetweenAccounts(double amount, Account outAccount, Account inAccount){
        if(outAccount.getOwner() == inAccount.getOwner() && outAccount.getOwner() == this){  // check that the user is only transfering between his/her accounts and that he/she owns the account
            // checks that the user isn't transferring negative amounts and that there is enough money in the source account to be moved.
            if(amount > 0){
                if(amount <= outAccount.sumTransactions()){
                    outAccount.withdraw(amount);
                    inAccount.deposit(amount);
                }
                else{
                    throw new IllegalArgumentException("insufficient funds");
                }
            }
            else{
                throw new IllegalArgumentException("amount must be greater than 0");
            }
        }
        else{
            throw new IllegalArgumentException("money can only be transfered between customer's own accounts");
        }

    }
}
