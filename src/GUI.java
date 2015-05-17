import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.event.*;

@SuppressWarnings({"serial", "static-access", "unused"})
public class GUI  extends JFrame implements ActionListener, DocumentListener
{
	//TODO fix DocumentListener so the buttons activate on both textfields while typing
	
	protected JTextField
	yearPane,
	totalDaysPane;
	protected JTextArea
	newTextArea;	
	private JButton 
	estimateButton,
	timeNowButton,
	compareButton,
	randomButton;
	private int count = 0;
	
	protected String TimeNow()
	{
		CurrentTime mex = new CurrentTime();
		return mex.timeStandard();
	}
	
	protected String Timer()
	{
		CalendarDate mex = new CalendarDate();
		try
		{
			mex.calculate(Integer.parseInt(yearPane.getText()),
				Integer.parseInt(totalDaysPane.getText()));
			
			return mex.toString();
		}
		catch (Exception e)
		{
			return "XYZ";	
		}
	}
	
	/*
	 * File operation protocols
	 */
	private void AppendText() throws IOException
	{
		
		String appendText = "";
		
		if (Integer.parseInt(yearPane.getText()) < 10 && Integer.parseInt(yearPane.getText()) >= 0)
			appendText += "+000";
		else if (Integer.parseInt(yearPane.getText()) < 100 && Integer.parseInt(yearPane.getText()) >= 10)
			appendText += "+00";
		else if (Integer.parseInt(yearPane.getText()) < 1000 && Integer.parseInt(yearPane.getText()) >= 100)
			appendText += "+0";
		else if (Integer.parseInt(yearPane.getText()) > -10 && Integer.parseInt(yearPane.getText()) < 0)
			appendText += "-000";
		else if (Integer.parseInt(yearPane.getText()) > -100 && Integer.parseInt(yearPane.getText()) <= -10)
			appendText += "-00";
		else 
			appendText += "+";
		
		if (Integer.parseInt(yearPane.getText()) >= 0)
			appendText+= yearPane.getText();
		else
			appendText+= Integer.parseInt(yearPane.getText())*-1;
		
		appendText += "~";
		
		
		if (Integer.parseInt(totalDaysPane.getText()) < 10)
			appendText += "00";
		else if (Integer.parseInt(totalDaysPane.getText()) < 100)
			appendText += "0";
		
		appendText += totalDaysPane.getText();
		appendText += ("~"+ Timer()+"\n");
		
		//writing new, or append
		PrintWriter fileOut;
		
		fileOut = new PrintWriter(new BufferedWriter(new FileWriter("DATABASE.rtf", true)));
		fileOut.append(appendText);		
		fileOut.close();
	}
	
	public GUI()
	{
		super("Time Teller");
		
		// operation
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(800,600);
		
		Container 
		c = getContentPane();
		c.setBackground(new Color(100,100,255));
		c.setLayout(null);
		
		Font 
		defaultFont = new Font("Georgia", Font.BOLD, 16);
		
		JLabel 
		titleLabel = new JLabel("Time Now and Then..."),
		instruc1   = new JLabel("Years"),
		instruc2   = new JLabel("Days"),
		instruc3   = new JLabel("Result");
		
		titleLabel.setForeground(Color.WHITE);
		instruc1.  setForeground(Color.WHITE);
		instruc2.  setForeground(Color.WHITE);
		instruc3.  setForeground(Color.WHITE);
		
		titleLabel.setBounds(0, 10, getWidth(), 50);
		instruc1.setBounds  (10,80,105,40);
		instruc2.setBounds  (160,80,105,40);
		instruc3.setBounds  (10,220,105,40);
		
		titleLabel.setFont(defaultFont);
		instruc1.setFont  (defaultFont);
		instruc2.setFont  (defaultFont);
		instruc3.setFont  (defaultFont);
		
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		instruc1.  setHorizontalAlignment (JLabel.RIGHT);
		instruc2.  setHorizontalAlignment (JLabel.RIGHT);
		instruc3.  setHorizontalAlignment (JLabel.RIGHT);
		
		c.add(titleLabel);
		c.add  (instruc1);
		c.add  (instruc2);
		c.add  (instruc3);
		
		yearPane = new JTextField();
		totalDaysPane = new JTextField();
		
		yearPane.getDocument().putProperty("filterNewlines", Boolean.TRUE); 
		totalDaysPane.getDocument().putProperty("filterNewlines", Boolean.TRUE);
		
		yearPane.setBounds (50,120,100,25);
		totalDaysPane.setBounds(200,120,100,25);
		
		yearPane.setFont (defaultFont);
		totalDaysPane.setFont(defaultFont);
		
		yearPane.setHorizontalAlignment(yearPane.CENTER);
		totalDaysPane.setHorizontalAlignment(totalDaysPane.CENTER); 
		
		yearPane.setDocument(new FixedSizeDocument(4));
		totalDaysPane.setDocument(new FixedSizeDocument(3));
		
		c.add(yearPane);
		c.add(totalDaysPane);
		
		
		yearPane.getDocument().addDocumentListener(new DocumentListener()
		{
			public void insertUpdate(DocumentEvent e) 
			{
				estimateButton.setEnabled(e.getDocument().getLength() != 0);
				compareButton.setEnabled(e.getDocument().getLength() != 0);
		    }
		    public void removeUpdate(DocumentEvent e) 
		    {
		    	estimateButton.setEnabled(e.getDocument().getLength() != 0);
		    	compareButton.setEnabled(e.getDocument().getLength() != 0);
		    }
		    public void changedUpdate(DocumentEvent e) 
		    {
		    	estimateButton.setEnabled(e.getDocument().getLength() != 0);
		    	compareButton.setEnabled(e.getDocument().getLength() != 0);
		    }
		});
		totalDaysPane.getDocument().addDocumentListener(new DocumentListener()
		{

			public void insertUpdate(DocumentEvent e) 
			{
				estimateButton.setEnabled(e.getDocument().getLength() != 0);
				compareButton.setEnabled(e.getDocument().getLength() != 0);
		    }
		    public void removeUpdate(DocumentEvent e) 
		    {
		    	estimateButton.setEnabled(e.getDocument().getLength() != 0);
		    	compareButton.setEnabled(e.getDocument().getLength() != 0);
		    }
		    public void changedUpdate(DocumentEvent e) 
		    {
		    	estimateButton.setEnabled(e.getDocument().getLength() != 0);
		    	compareButton.setEnabled(e.getDocument().getLength() != 0);
		    }
		});
		
		
		newTextArea = new JTextArea();
		newTextArea.setBounds  (50,260,405,75);
		newTextArea.setEditable(false);
		newTextArea.setLineWrap(true);
		newTextArea.setWrapStyleWord (true);
		newTextArea.setFont(defaultFont);
		
		c.add(newTextArea);
		
		estimateButton = new JButton("Estimate");
		timeNowButton = new JButton("Time Now");
		compareButton = new JButton("Compare");
		randomButton = new JButton("RANDOM");
		
		estimateButton.setFont (defaultFont);
		timeNowButton.setFont (defaultFont);
		compareButton.setFont (defaultFont);
		randomButton.setFont (defaultFont);
		
		estimateButton.setBounds(520, 100, 190, 30);
		timeNowButton.setBounds(520, 150, 190, 30);
		compareButton.setBounds(520, 200, 190, 30);
		randomButton.setBounds(100, 160, 150, 30);
		
		estimateButton.addActionListener(this);
		timeNowButton.addActionListener(this);
		compareButton.addActionListener(this);
		randomButton.addActionListener(this);
		
		estimateButton.setEnabled(false);
		compareButton.setEnabled(false);
		
		c.add(estimateButton);
		c.add(timeNowButton);
		c.add(compareButton);
		c.add(randomButton);
		
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		CalendarDate make = new CalendarDate();
		
		if (e.getSource() == timeNowButton)
		{
			newTextArea.setText(TimeNow());
		}
		if (e.getSource() == estimateButton)
		{
			if (Timer().equals("XYZ"))
			newTextArea.setText("ERROR: Incorrect values");
			else	
			{
			newTextArea.setText(Timer());
			
			try {AppendText();}
			catch (IOException f){make.error(f);}
			}
		}
		if (e.getSource() == randomButton)
		{
			int rand = 0;
			int rom = (int)(Math.random()*6+1);
			
			if (rom%3==0)
				rand = (int)(Math.random()*10000-45);
			else
				rand = (int)(Math.random()*2000-45);

			if (rand != 0)
				yearPane.setText(rand+"");
			else
				yearPane.setText(1+"");
		
		
			totalDaysPane.setText((int)(Math.random()*365+1)+"");
		}
			
		if (e.getSource() == compareButton)
		{
			if(Timer().equals("XYZ"))
			{
				newTextArea.setText("ERROR: Incorrect values");
			}
			else
			{
				CompareTime ct = new CompareTime();
				newTextArea.setText(ct.compare());
			}
		}
	}

	@Override
	public void changedUpdate(DocumentEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertUpdate(DocumentEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeUpdate(DocumentEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
