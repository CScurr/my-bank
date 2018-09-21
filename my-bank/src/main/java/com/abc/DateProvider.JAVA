import java.util.Calendar;
import java.util.Date;

/** 
 * DateProvider class 
 * Returns the timestamp of its creation
 * Singleton class (there can only be one instance of class)
 * @author Ciaran Scurr
 * @version 1.0
 * @date 21/09/1028
 */
public class DateProvider {
    private static DateProvider instance = null;

    /**
     * Constructor for objects of class Transaction
     * creates an instance of itself upon being called the first time
     * all other calls to this class point to the first dateprovider object created
     */
    public static DateProvider getInstance() {
        if (instance == null)
            instance = new DateProvider();
        return instance;
    }

    /** 
     * Returns the current date and time
     * @return current time stamp (Date)
     */
    public Date now() {
        return Calendar.getInstance().getTime();
    }
}
