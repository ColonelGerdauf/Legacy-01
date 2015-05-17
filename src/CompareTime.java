public class CompareTime 
{
	int years=0;
	int days=0;
	int months=0;
	
	protected String compare()
	{
		GUI gui = new GUI();
		CalendarDate next = new CalendarDate();
		
		String 
		a1 = gui.TimeNow(),
		a2 = gui.Timer();
	
		String answer = "The calendar dates are";
		
		a1 = a1.substring(a1.indexOf(", ")+2);
		a2 = a2.substring(a2.indexOf(", ")+2);
		
		int b2 = 0;try
		{
			b2 = Integer.parseInt(a2.substring(a2.indexOf(", ")+2));
		}
		catch (Exception x)
		{
			try
			{
				b2 = Integer.parseInt(a2.substring(a2.indexOf(", ")+2,
						a2.indexOf("BC")-1))*-1;
			}
			catch (Exception y)
			{
				try
				{
					b2 = Integer.parseInt(a2.substring(a2.indexOf(", ")+2,
							a2.indexOf("CE")-1));
				}
				catch (Exception z)
				{
					b2 = Integer.parseInt(a2.substring(a2.indexOf(", ")+2,
							a2.indexOf("AD")-1));
				}
			}
		}
		
		int 
		b1 = Integer.parseInt(a1.substring(a1.indexOf(", ")+2));
		
		if (b1 > b2)             // MARK 1
			years = b1 - b2;
		else if (b1 < b2)
			years = b2 - b1;
						
		int
		s1 = next.toInt(a1.substring(0, a1.indexOf(" "))),
		s2 = next.toInt(a2.substring(0, a2.indexOf(" ")));
		
		if (s1 > s2)             // MARK 2
			months = s1 - s2;
		else if (s1 < s2)
			months = s2 - s1;
		
		int
		c1 = Integer.parseInt(a1.substring(a1.indexOf(" ")+1,a1.indexOf(", ")) ),
		c2 = Integer.parseInt(a2.substring(a2.indexOf(" ")+1,a2.indexOf(", ")) );
		
		if (c1 > c2)             // MARK 3
			days = c1 - c2;
		else if (c1 < c2)
			days = c2 - c1;
		
		if (years == 0 && months == 0 && days == 0)
			answer += " the same.";
		else
		{
			if (years > 1)
				answer += " " + years + " years";
			else if (years == 1)
				answer += " 1 year";
			
			
			if (years > 0 && months > 0 && days == 0 ||
					years > 0 && months == 0 && days > 0)
				answer += " and";
			else if (years > 0 && months > 0 && days > 0)
				answer += ",";
			
			if (months > 1)
				answer += " " + months + " months";
			else if (months == 1)
				answer += " 1 month";
			
			
			if (months > 0 && days > 0)
				answer += " and";
			
			if (days > 1)
				answer += " "+ days +" days";
			else if (days == 1)
				answer += " 1 day";
			
			
			answer += " apart.";
			
			String ans = 
			(years*365 + months*31 + days) + "";
			
			int i = (ans.length())-3;
			// .0.1.2.3.4.5.6.7.8.9.
	
			while (i > 0)
			{
				String a = ans.substring(i);
				String o = ans.substring(0, i);
				
				ans = o + "'" + a;
				i -= 3;
			}
			
			if (years > 0 || months > 0)
				answer += "\nOtherwise the difference is approximately " 
				+ ans + " days.";
		}
		
		return answer;
	}
}
