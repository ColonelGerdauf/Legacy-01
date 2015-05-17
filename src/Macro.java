import java.io.*;
import java.util.*;

@SuppressWarnings ({"unchecked","rawtypes"})
public class Macro 
{
	/**
	 * Steps:
	 * 1) open BufferReader of the designated file
	 * 2) IF not found, do nothing and stop code
	 * 3) ELSE initialize variables
	 * 4) Count the number of lines until no longer ready
	 * 5) declare indexes of a String array using number of lines
	 * 6) close and reboot BufferReader
	 * 7) reset counter to zero
	 * 8) place each line into corresponding index of the array
	 * 9) close the BufferReader
	 * 10) sort the array by values
	 * 11) initialize an ArrayList 
	 * 12) clear duplicate entries
	 * 14) turn ArrayList back into an array
	 * 15) open PrintWriter
	 * 16) overwrite the file
	 * 17) write the new array per element per line
	 * @throws IOException
	 */

	protected void assemble() throws IOException
	{
		//for testing purposes only
		
		try 
		{
			BufferedReader oldFile;
			oldFile = new BufferedReader(new FileReader("DATABASE.rtf"));
			
			int k=0;
			String[] str = {};


			while (true)
			{
			  if (! oldFile.ready())
				break;
			
				oldFile.readLine();
				k++;
			} 
			
			str = new String[k];
			oldFile.close();
			
			k=0;
			
			oldFile = new BufferedReader(new FileReader("DATABASE.rtf"));
			
			while (true)
			{
			  if (! oldFile.ready())
				break;
			
				String next_line = oldFile.readLine();
				
				str[k] = next_line;
				k++;
			} 

			oldFile.close();
						
			//java.util.Arrays.sort(str);		
			Quicksort quickie = new Quicksort();
			quickie.sort(str);
			//TODO reassign the numbers so that the whole first block of int is sorted, or place the value of '-' before '+', and have numbers with '-' have the order reversed
			
			/*TODO fix this code
			ArrayList arlList = new ArrayList(Arrays.asList(str));
			HashSet h = new HashSet(arlList);
			arlList.clear();
			arlList.addAll(h);

			arlList.toArray(str);
			*/		
			PrintWriter newFile;
			newFile = new PrintWriter
			(new BufferedWriter
					(new FileWriter
							("DATABASE.rtf", false)));;
							
			for (int i = 0; i < str.length; i++)				
				newFile.append(str[i] + "\n");
			
			newFile.close();
			
		} 
		catch (FileNotFoundException e){/*do nothing and stop code*/} 
	}
}
