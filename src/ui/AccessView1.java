package ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
/**
 * This view concerns "select data" button
 * @author jacquez
 *
 */

@SuppressWarnings("serial")
public class AccessView1  extends JPanel implements ActionListener {

	private MainView frame;
	private  ArrayList<String[]> myFile;
	private int columnID;
	private int columnDay;
	private String dateFormat;

	public AccessView1(MainView frame, ArrayList<String[]> myFile) {

		this.frame = frame;
		this.myFile = myFile;
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		setBackground(Color.LIGHT_GRAY);
		setSize(640, 480);

		JLabel lblSelectReferenceColumn = new JLabel("Select reference column for:");
		springLayout.putConstraint(SpringLayout.NORTH, lblSelectReferenceColumn, 35, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblSelectReferenceColumn, 46, SpringLayout.WEST, this);
		add(lblSelectReferenceColumn);

		JLabel lblId = new JLabel("ID: ");
		springLayout.putConstraint(SpringLayout.NORTH, lblId, 31, SpringLayout.SOUTH, lblSelectReferenceColumn);
		springLayout.putConstraint(SpringLayout.WEST, lblId, 0, SpringLayout.WEST, lblSelectReferenceColumn);
		add(lblId);

		JLabel lblDay = new JLabel("Day:");
		springLayout.putConstraint(SpringLayout.NORTH, lblDay, 32, SpringLayout.SOUTH, lblId);
		springLayout.putConstraint(SpringLayout.WEST, lblDay, 0, SpringLayout.WEST, lblSelectReferenceColumn);
		add(lblDay);

		JComboBox<String> comboBoxID = new JComboBox<String>(myFile.get(0));
		springLayout.putConstraint(SpringLayout.NORTH, comboBoxID, 31, SpringLayout.SOUTH, lblSelectReferenceColumn);
		springLayout.putConstraint(SpringLayout.WEST, comboBoxID, 24, SpringLayout.EAST, lblId);
		springLayout.putConstraint(SpringLayout.EAST, comboBoxID, 9, SpringLayout.EAST, lblSelectReferenceColumn);
		comboBoxID.addActionListener(this);
		comboBoxID.setActionCommand("comboID");
		add(comboBoxID);

		JComboBox<String> comboBoxDay = new JComboBox<String>(myFile.get(0));
		springLayout.putConstraint(SpringLayout.NORTH, comboBoxDay, 17, SpringLayout.SOUTH, comboBoxID);
		springLayout.putConstraint(SpringLayout.WEST, comboBoxDay, 0, SpringLayout.WEST, comboBoxID);
		springLayout.putConstraint(SpringLayout.EAST, comboBoxDay, 0, SpringLayout.EAST, comboBoxID);
		comboBoxDay.addActionListener(this);
		comboBoxDay.setActionCommand("comboDay");
		add(comboBoxDay);

		JButton btnValid = new JButton("Valid");
		springLayout.putConstraint(SpringLayout.EAST, btnValid, 0, SpringLayout.EAST, comboBoxID);
		btnValid.addActionListener(this);
		btnValid.setActionCommand("valid");
		add(btnValid);

		JButton btnBack = new JButton("Back");
		springLayout.putConstraint(SpringLayout.WEST, btnBack, 69, SpringLayout.WEST, this);
		btnBack.addActionListener(this);
		btnBack.setActionCommand("back");
		add(btnBack);

		JLabel lblSelectYourDate = new JLabel("Select date format of your file");
		springLayout.putConstraint(SpringLayout.NORTH, lblSelectYourDate, 6, SpringLayout.SOUTH, comboBoxDay);
		springLayout.putConstraint(SpringLayout.WEST, lblSelectYourDate, 0, SpringLayout.WEST, lblSelectReferenceColumn);
		add(lblSelectYourDate);

		String [] dateFormatInitial = {"dd/MM/yyyy HH:mm:ss","yyyy-MM-dd'T'HH:mm", "Timestamp","MM/dd/yyy HH:mm:ss"};
		JComboBox<String> comboBoxDateFormat = new JComboBox<String>(dateFormatInitial);
		springLayout.putConstraint(SpringLayout.NORTH, btnValid, 12, SpringLayout.SOUTH, comboBoxDateFormat);
		springLayout.putConstraint(SpringLayout.NORTH, btnBack, 12, SpringLayout.SOUTH, comboBoxDateFormat);
		springLayout.putConstraint(SpringLayout.NORTH, comboBoxDateFormat, 6, SpringLayout.SOUTH, lblSelectYourDate);
		springLayout.putConstraint(SpringLayout.WEST, comboBoxDateFormat, 0, SpringLayout.WEST, comboBoxID);
		springLayout.putConstraint(SpringLayout.EAST, comboBoxDateFormat, 0, SpringLayout.EAST, comboBoxID);
		comboBoxDateFormat.addActionListener(this);
		comboBoxDateFormat.setActionCommand("comboDateFormat");
		add(comboBoxDateFormat);

		//Initialization
		
		columnID = 1;
		columnDay =1;
		dateFormat = dateFormatInitial[0];

	}

	@SuppressWarnings("unchecked")
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String cmd = e.getActionCommand();
		if(cmd.equals("valid")){
			frame.changePanel(new AccessView2(frame, myFile, columnID,columnDay,dateFormat));
		}

		else if (cmd.equals("back")){
			frame.changePanel(new HomeView(frame));
		}

		else if (cmd.equals("comboID")){
			JComboBox<String> choice = (JComboBox<String>)e.getSource();
			columnID = choice.getSelectedIndex();	
		}

		else if (cmd.equals("comboDay")){
			JComboBox<String> choice = (JComboBox<String>)e.getSource();
			columnDay = choice.getSelectedIndex();	
		}

		else if (cmd.equals("comboDateFormat")){
			JComboBox<String> choice = (JComboBox<String>)e.getSource();
			dateFormat = choice.getSelectedItem().toString();
		}	
	}
}