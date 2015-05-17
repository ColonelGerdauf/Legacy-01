import java.lang.*;
import javax.swing.*;

@SuppressWarnings("unused")
public class CalendarDate 
{
	private final byte[][] dayM= {{31,28,31,30,31,30,31,31,30,31,30,31},
							{31,29,31,30,31,30,31,31,30,31,30,31}};
	private final String[] dayW=
	{"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
	
	private final String[][] month = 
	{//Gregorian calendar
	{"January",
	"February",
	"March",
	"April",
	"May",
	"June",
	"July",
	"August",
	"September",
	"October",
	"November",
	"December"},
	{//Julian- calendar
	"Ianuarius", 
	"Februarius",
	"Martius",  
	"Aprilis", 
	"Maius", 
	"Iunius",
	"Quintilis", 
	"Sextilis", 
	"September",  
	"October",  
	"November", 
	"December"}};
	
	private String[] ans = {"-DAY_OF_WEEK-", "-MONTH-", "-DAY_OF_MONTH-", "-YEAR-"};
	
	private int year;
	
	
	protected int toInt (String str)
	{
		for (int i = 0; i < month[0].length; i++)
		{
			if (str.equals(month[0][i]))
			{
				return i+1;
			}
			else if (str.equals(month[1][i]))
			{
				return i+1;
			}
		}
		return 0;
	}
	private boolean commonYear(int y)
	{
		return (y%100 == 0 && y%400 != 0 && y > 1582 ||
			y%4 != 0 && y > -45);
	}
	protected void error(Exception e)
	{
		String errorMessage = e.getMessage();
		String ET = e.getClass().toString();
		
		String errorType = ET.substring(ET.indexOf(" ")+1); 
		
		while (errorType.indexOf(".") != -1)
		{
			errorType = errorType.substring(errorType.indexOf(".")+1);
		}
		
		JOptionPane.showMessageDialog(null,
			errorType + ": " + errorMessage,
			"RUN-TIME ERROR",
			JOptionPane.ERROR_MESSAGE);
		
		GUI gui = new GUI();
		gui.yearPane.setText("");
		gui.totalDaysPane.setText("");
		
		
	}
	private String DOW(int y, int tD)
	{
		
		long ans = tD;
		
		for (int i = -45; i <= y; i++)
		{
			if (i == 0)
				i++;
			if (commonYear(i) == true)
				ans += 365;
			else
				ans += 366;
		}
		return dayW[(int)ans%7];

	}
	public String[] calculate(int y, int totDays)
	{

		if (totDays > 365 && commonYear(y) == true||
				totDays > 366 && commonYear(y) == false||
				totDays <= 0 ||
				y < -45 && y > 9999)
			error (new IndexOutOfBoundsException("Invalid value(s)"));
				
		
		if (y == 0)
			error (new ArithmeticException("There is no year zero"));
		else if (y < 0)
			ans[3] = y*-1 + " BC";
		else if (y <= 575)
			ans[3] = y + " AD";
		else if (y <= 1582)
			 ans[3] = y + " CE";
		else
			ans[3] = y+"";	
		
		byte 
		yearIndex = 0,
		monthIndex = 0,
		Dindex = 0; 
		int dayIndex = 0;
		
		
		if (commonYear(y) == true) 
			yearIndex = 0;
		else if (commonYear(y) == false) 
			yearIndex = 1;
			
		while (dayIndex < totDays)
		{
			dayIndex++;
			Dindex++;
			
			if (Dindex > dayM[yearIndex][monthIndex])
			{
				Dindex = 1;
				monthIndex++;
				if (monthIndex > 11)
					break;
			}
			
		}
		
		ans[2] = Dindex + "";
		
		if (y == 1582 && totDays >= 55 || y > 1582)
			ans[1] = month[0][monthIndex];
		else if (y == 1582 && totDays < 55 || y < 1582)
			ans[1] = month[1][monthIndex];
		
		if (commonYear(y) == true) // repeated operation. Required for leap years
			ans[0] = DOW(y,totDays);
		else
			ans[0] = DOW(y,totDays-1);

		return ans;
	
	}
	public String toString()
	{
		return ans[0] + ", " + ans[1] + " " + ans[2] + ", " + ans[3];	
	}
}
