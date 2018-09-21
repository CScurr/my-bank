import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Maxi_Savings account, child of the abstract class Account
 * Unique from other children of Account in its interest rates
 *
 * @author Ciaran Scurr
 * @version 1.0
 * @date 21/09/1028
 */
public class Maxi_Savings extends Account
{

    /**
     * Constructor for objects of class Maxi_Savings
     * No parameters required
     */
    public Maxi_Savings()
    {

    }

    /** 
     * Returns the total interest earned from account
     * 5% interest for maxi_savings if no money taken out in last 10 days
     * 0.1% otherwise
     * @return total interest (double)
     */
    public double interestEarned(){
        //calculates if the most recent withdrawal was more than 10 days ago
        if(getDateDiff(getMostRecentWithdrawal(), DateProvider.getInstance().now(), TimeUnit.DAYS) > 10){
            return calculateCompoundInterest(0.05);
        }
        else{
            return calculateCompoundInterest(0.001);
        }
        
    }
    
    /**
     * Returns string of account type
     * @return account type (String)
     */
    public String printAccountType(){
        return "Maxi Savings Account\n\n";
    }
    /**
     * Returns the most recent withdrawal from the account
     * Used to calculate the interest rate
     * @return most recent withdrawal (Date)
     */
    private Date getMostRecentWithdrawal(){
        Date mostRecent = new Date(60, 1, 1); // old date so that transaction date is always more recent
        for (Transaction t: transactions){
            if(t.getDate().compareTo(mostRecent) > 0){ // if > 0 then date is after agrument date
                mostRecent = t.getDate();
            }
        }
        return mostRecent;
    }

    
}
