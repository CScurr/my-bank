import java.util.Calendar;
import java.util.Date;

/** 
 * Transaction class 
 * Contains information on the amount of the transaction and a time stamp
 * @author Ciaran Scurr
 * @version 1.0
 * @date 21/09/1028
 */
public class Transaction {
    public final double amount;

    private Date transactionDate;
    
     /**
     * Constructor for objects of class Transaction
     * @param transaction amount (double)
     */
    public Transaction(double amount) {
        this.amount = amount; 
        this.transactionDate = DateProvider.getInstance().now();
    }
    
    /**
     * Returns the date of the transaction 
     * @return date of transaction (Date)
     */
    public Date getDate(){
        return transactionDate;
    }
}
