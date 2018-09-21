
/**
 * Maxi_Savings account, child of the abstract class Account
 * Unique from other children of Account in its interest rates
 *
 * @author Ciaran Scurr
 * @version 1.0
 * @date 21/09/1028
 */
public class Savings extends Account
{


    /**
     * Constructor for objects of class Savings
     * No parameters required
     */
    public Savings()
    {

    }
    
    /** 
     * Returns the total interest earned from account
     * 0.1% interest below $1000
     * 0.2% otherwise
     * @return total interest (double)
     */
    public double interestEarned(){
        double amount = sumTransactions();
        if (amount <= 1000)
            return calculateCompoundInterest(0.001);
        else
            return calculateCompoundInterest(0.002);
    }
    
    /**
     * Returns string of account type
     * @return account type (String)
     */
    public String printAccountType(){
        return "Savings Account\n\n";
    }
    
    
    
}
