import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.List;
import java.lang.Math;

/** 
 * Abstrac class from which the account types inherit 
 * Contains a list of transactions in and out of the account along with the account owner
 * @author Ciaran Scurr
 * @version 1.0
 * @date 21/09/1028
 */
public abstract class Account {

    public List<Transaction> transactions;
    public static Customer owner;
    /**
     * Constructor of account
     * No parameteres required
     */
    public Account() {
        owner = null;
        this.transactions = new ArrayList<Transaction>();
    }
    
    /**
     * Deposit money into account
     * Must be non negative
     * @param deposit amount (double)
     */
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } else {
            transactions.add(new Transaction(amount));
        }
    }
    
    /**
     * Withdraw money from account
     * Must be non negative and less than or equal to total money in account
     * @param withdrawal amount (double)
     */
    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        }
        else if(sumTransactions() < amount){ 
            // Customer cannot withdraw more funds than they have in the account
            throw new IllegalArgumentException("insufficient funds");
        }
        else {
            transactions.add(new Transaction(-amount));
        }
    }
    
    /**
     * Overwritten in child classes
     */
    abstract double interestEarned();

    /** 
     * Sums the previous transactions of the account 
     * @return total of transactions (double)
     */    
    public double sumTransactions(){
        double amount = 0.0;
        for (Transaction t: transactions){
            amount += t.amount;
        }
        return amount; 
    }
    
    
    /**
     * Sets the owner of the account
     * @param owner (Customer)
     */
    public void setOwner(Customer customer){
        this.owner = customer;
    }
    
    /**
     * Returns the owner of the account
     * @return owner (Customer)
     */
    public Customer getOwner(){
        return owner;
    }

    /**
     * Overwritten in child methods 
     */
    abstract String printAccountType();

    /**
     * Calculates difference between two dates
     * @param first date (Date)
     * @param second date (Date)
     * @param unit of time diffence to be calculated in eg. seconds (TimeUnit) 
     */
    public long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
        long diffInMillies = date2.getTime() - date1.getTime();
        return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
    }

    /**
     * Returns the earliest transaction in account
     * @return earliest transaction (Date)
     */
    public Date getFirstTransaction(){
        Date firstTransaction = new Date(200, 1, 1); // old date so that transaction date is always more recent
        if(transactions.size() > 0){
            for (Transaction t: transactions){
                if(t.getDate().compareTo(firstTransaction) < 0){ // if > 0 then date is after agrument date
                    firstTransaction = t.getDate();
                }
            }
        }
        else{
            firstTransaction = null;
        }
        return firstTransaction;
    }
    /**
     * Calculates the compound interest of an account depending on the interest rate.
     * Uses the compound interest formula and returns the result
     * @param interest rate of account (double)
     * @return compound interest (double)
     */
    public double calculateCompoundInterest(double interestRate){
        double amount = 0;
        if(getFirstTransaction() != null){
            // amount = p*(1+r/n)^nt   (nt/365 to give daily interest)
            amount = sumTransactions()* ((1 + interestRate/365));
            double exponent = getDateDiff(getFirstTransaction(), DateProvider.getInstance().now(), (TimeUnit.DAYS))/365;
            amount = Math.pow(amount, exponent);
            
        }
        return amount;
    }
}
