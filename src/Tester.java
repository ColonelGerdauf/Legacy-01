import java.awt.event.*;
import java.io.*;

import javax.swing.*;

public class Tester 
{

	public static void main(String[] args)
	{
		final GUI wl = new GUI();
		wl.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	    wl.setVisible(true);
		wl.addWindowListener(new WindowListener() 
		{      
			
	            public void windowActivated(WindowEvent arg0) 
	            {
	                 // initialization of application
	            		
	            }
				public void windowOpened(WindowEvent arg0) 
	            {
	            	// operation of application
	            	PrintWriter fileOut = null;	        		
	        		try	
	        		{fileOut = new PrintWriter(new BufferedWriter(new FileWriter("DATABASE.rtf", true)));}
	        		catch (Exception e){try{
	        			fileOut = new PrintWriter(new BufferedWriter(new FileWriter("DATABASE.rtf", false)));}
	        		catch (IOException f){}}
	        		
	        		fileOut.close();
	            }
	            public void windowIconified(WindowEvent arg0) 
	            {
	                // application being moved off-screen
	        		wl.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	            }
	            public void windowDeiconified(WindowEvent arg0) 
	            {
	            	// application being moved to visible screen
	            	String Micro = wl.newTextArea.getText(); 
	            	wl.newTextArea.setText("Welcome back" + "\n" + Micro);
	        		wl.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	            }
	            public void windowClosing(WindowEvent arg0) 
	            {	
	        		// prior to shutting down code
	            	int n = JOptionPane.showConfirmDialog(null,
                		"Are you sure you want to exit?",
                		"Notice",
                		JOptionPane.YES_NO_OPTION,
                		JOptionPane.WARNING_MESSAGE,
                		null);
                
	            	if (n == 0)
	            		wl.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	            	else if (n == 1)
                		wl.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	            }
	            public void windowDeactivated(WindowEvent arg0) 
	            {
	            	// any time the application is not on screen; iconified or closed
	            	int i = wl.getDefaultCloseOperation();
	            	if (i == JFrame.DISPOSE_ON_CLOSE)
	            	{
	            	Macro mac = new Macro();
	        		try{mac.assemble();}
	        		catch(IOException e){/*abort sorting in case of failure*/}
	            	}
	            }
	            public void windowClosed(WindowEvent arg0) 
	            {
	            	// after shutting down code
	            }
		 });
	}
}