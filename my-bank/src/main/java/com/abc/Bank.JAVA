import java.util.ArrayList;
import java.util.List;
/** 
 * Bank class 
 * Contains information on the bank, the customers belonging to that bank and the accounts belonging to the customers
 * @author Ciaran Scurr
 * @version 1.0
 * @date 21/09/1028
 */
public class Bank {
    private List<Customer> customers;

    /**
     * Constructor for objects of class Customer
     * No parameters required
     */
    public Bank() {
        customers = new ArrayList<Customer>();
    }

    /**
     * Sets up customer with the bank
     * @param customer to be added (Customer)
     */
    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    /**
     * Creates a string statement on all the customers in the bank and their number of accounts
     * @return string statement (String)
     */
    public String customerSummary() {
        String summary = "Customer Summary";
        for (Customer c : customers){
            summary += "\n - " + c.getName() + " (" + format(c.getNumberOfAccounts(), "account") + ")";
        }
        System.out.println(summary);
        return summary;
    }

    /**
     * Makes sure correct plural of word is created based on the number passed in
     * If number passed in is 1 just return the word otherwise add an 's' at the end
     * @param amount of objects (int)
     * @param word to be pluralised (String)
     * @return string of pluralised word (String)
     */
    private String format(int number, String word) {
        return number + " " + (number == 1 ? word : word + "s"); 
    }

    /**
     * Returns the total interest paid by the bank in interest to all customers
     * @return amount of interest paid (double)
     */
    public double totalInterestPaid() {
        double total = 0;
        for(Customer c: customers)
            total += c.totalInterestEarned();
        return total;
    }

    /**
     * Returns the name of the first customer added to the bank
     * If bank has no customers returns appropriate error string
     * @return string of first customer (String)
     */
    public String getFirstCustomer() {
        try {
            return customers.get(0).getName();
        } catch (Exception e){
            e.printStackTrace();
            return "No Customers in Database";
        }
    }
}
