import java.text.*;
import java.util.*;
/**
 * This class will display the time in the specified formats.
 * @author Amin Hosseini
 * @since Wednesday, March 2, 2011
 */
public class CurrentTime 
{
	/**
	 * Return the current date and time.
	 * @return 			date/time in standard format
	 */
	protected String timeStandard() 
	{
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("EEEE, MMMM dd, yyyy");
		return sdf.format(cal.getTime());

	}// end timeNow method
	/**
	 * Return the current date and time.
	 * @return 			date/time in shortened format
	 */
	protected String timeShortened() 
	{
		Date today;
		SimpleDateFormat sdf;
		
		today = new Date();
		sdf = new SimpleDateFormat("MM/dd/yy");
		return sdf.format(today);
	}// end timeCheck method
	/**
	 * Return the current date and time.
	 * @return 			date/time in defined format
	 */
	protected String timeDefined() 
	{
		Date today;	
		today = new Date();
		return today.toString();
	}// end timeCheck method
	
}// end class


