
/**
 * Checking account, child of the abstract class Account
 * Unique from other children of Account in its interest rates
 *
 * @author Ciaran Scurr
 * @version 1.0
 * @date 21/09/1028
 */
public class Checking extends Account
{

    /**
     * Constructor for objects of class Checking
     * No parameters required
     */
    public Checking()
    {
        
    }
    
    /** 
     * Returns the total interest earned from account
     * 0.1% interest for checking
     * @return total interest (double)
     */
    public double interestEarned(){
        return calculateCompoundInterest(0.001);
    }
    
    /**
     * Returns string of account type
     * @return account type (String)
     */
    public String printAccountType(){
        return "Checking Account\n";
    }
    
    
}
