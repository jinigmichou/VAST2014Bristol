package ui;

import javax.swing.JPanel;
import javax.swing.SpringLayout;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;

import core.Operator;
import core.Writer;

import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * This view concerns "select data" button
 * @author jacquez
 *
 */
public class AccessView2 extends JPanel implements ActionListener {
	private MainView frame;
	private ArrayList<String[]> myFile;
	private ArrayList<String[]> myFilestampForWriting;
	private String userString;
	private String date;
	private int columnDay;
	private String dateFormat;
	private int columnID;
	private JTextArea textArea;
	private JTextField textFieldFilePath;


	public AccessView2(MainView frame, ArrayList<String[]> myFile, int columnID, int columnDay, String dateFormat) {

		setForeground(Color.LIGHT_GRAY);
		this.frame = frame;
		this.myFile = myFile;
		this.dateFormat = dateFormat;
		this.columnDay = columnDay;
		this.columnID = columnID;

		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		setBackground(Color.LIGHT_GRAY);
		setSize(640, 480);


		JLabel lblDataAreaAccess = new JLabel("Data Area Access");
		springLayout.putConstraint(SpringLayout.NORTH, lblDataAreaAccess, 10, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblDataAreaAccess, 72, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, lblDataAreaAccess, 249, SpringLayout.WEST, this);
		add(lblDataAreaAccess);

		ArrayList<String> user = Operator.selectDistinctValuesInAColumn(myFile, columnID);
		JComboBox comboBoxUser = new JComboBox();
		for(int i = 1 ; i<user.size() ; i++ ){ comboBoxUser.addItem(user.get(i));}
		springLayout.putConstraint(SpringLayout.NORTH, comboBoxUser, 39, SpringLayout.SOUTH, lblDataAreaAccess);
		springLayout.putConstraint(SpringLayout.WEST, comboBoxUser, 124, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, comboBoxUser, 0, SpringLayout.EAST, lblDataAreaAccess);
		comboBoxUser.addActionListener(this);
		comboBoxUser.setActionCommand("comboUser");
		add(comboBoxUser);

		JLabel lblUserId = new JLabel("User Id:");
		springLayout.putConstraint(SpringLayout.NORTH, lblUserId, 4, SpringLayout.NORTH, comboBoxUser);
		springLayout.putConstraint(SpringLayout.WEST, lblUserId, 35, SpringLayout.WEST, this);
		add(lblUserId);

		JLabel lblDay = new JLabel("Day: ");
		springLayout.putConstraint(SpringLayout.NORTH, lblDay, 29, SpringLayout.SOUTH, lblUserId);
		springLayout.putConstraint(SpringLayout.WEST, lblDay, 0, SpringLayout.WEST, lblUserId);
		add(lblDay);

		ArrayList<String[]> myFilestamp1 = Operator.cloneArrayList(myFile);
		Operator.dateStringtoTimestamp(myFilestamp1, columnDay, dateFormat);
		Operator.timeStampToDate(myFilestamp1, columnDay, "yyyy-MM-dd");


		ArrayList<String> day = Operator.selectDistinctValuesInAColumn(myFilestamp1, columnDay);
		JComboBox comboBoxDay = new JComboBox();
		for(int i = 1;i<day.size();i++ ){ comboBoxDay.addItem(day.get(i));}
		springLayout.putConstraint(SpringLayout.NORTH, comboBoxDay, 22, SpringLayout.SOUTH, comboBoxUser);
		springLayout.putConstraint(SpringLayout.WEST, comboBoxDay, 0, SpringLayout.WEST, comboBoxUser);
		springLayout.putConstraint(SpringLayout.EAST, comboBoxDay, 0, SpringLayout.EAST, lblDataAreaAccess);
		comboBoxDay.addActionListener(this);
		comboBoxDay.setActionCommand("comboDay");
		add(comboBoxDay);

		JButton btnValid = new JButton("Valid");
		springLayout.putConstraint(SpringLayout.NORTH, btnValid, 23, SpringLayout.SOUTH, comboBoxDay);
		springLayout.putConstraint(SpringLayout.EAST, btnValid, 0, SpringLayout.EAST, lblDataAreaAccess);
		btnValid.addActionListener(this);
		btnValid.setActionCommand("valid");
		add(btnValid);

		JButton btnBack = new JButton("Back");
		springLayout.putConstraint(SpringLayout.NORTH, btnBack, 0, SpringLayout.NORTH, btnValid);
		springLayout.putConstraint(SpringLayout.EAST, btnBack, -6, SpringLayout.WEST, btnValid);
		btnBack.addActionListener(this);
		btnBack.setActionCommand("back");
		add(btnBack);

		textArea =  new JTextArea();
		textArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(textArea);
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 20, SpringLayout.SOUTH, btnValid);
		springLayout.putConstraint(SpringLayout.WEST, scrollPane, 0, SpringLayout.WEST, lblUserId);
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, 236, SpringLayout.SOUTH, btnValid);
		springLayout.putConstraint(SpringLayout.EAST, scrollPane, 542, SpringLayout.WEST, lblUserId);
		add(scrollPane);

		textFieldFilePath = new JTextField();
		springLayout.putConstraint(SpringLayout.WEST, textFieldFilePath, -248, SpringLayout.EAST, scrollPane);
		springLayout.putConstraint(SpringLayout.SOUTH, textFieldFilePath, 0, SpringLayout.SOUTH, comboBoxUser);
		springLayout.putConstraint(SpringLayout.EAST, textFieldFilePath, 0, SpringLayout.EAST, scrollPane);
		add(textFieldFilePath);
		textFieldFilePath.setColumns(10);

		JButton btnEraseText = new JButton("Erase text");
		springLayout.putConstraint(SpringLayout.NORTH, btnEraseText, 0, SpringLayout.NORTH, lblDay);
		springLayout.putConstraint(SpringLayout.WEST, btnEraseText, 0, SpringLayout.WEST, textFieldFilePath);
		btnEraseText.addActionListener(this);
		btnEraseText.setActionCommand("erase");
		add(btnEraseText);

		JButton btnWriteIntoFile = new JButton("Write into file");
		springLayout.putConstraint(SpringLayout.NORTH, btnWriteIntoFile, -1, SpringLayout.NORTH, comboBoxDay);
		springLayout.putConstraint(SpringLayout.EAST, btnWriteIntoFile, 0, SpringLayout.EAST, scrollPane);
		btnWriteIntoFile.addActionListener(this);
		btnWriteIntoFile.setActionCommand("write");
		add(btnWriteIntoFile);

		JLabel lblFileName = new JLabel("File name:");
		springLayout.putConstraint(SpringLayout.WEST, lblFileName, 0, SpringLayout.WEST, textFieldFilePath);
		springLayout.putConstraint(SpringLayout.SOUTH, lblFileName, -6, SpringLayout.NORTH, textFieldFilePath);
		add(lblFileName);

		myFilestampForWriting = new ArrayList<String[]>();
		myFilestampForWriting.add(myFile.get(0));



	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String cmd = e.getActionCommand();
		if(cmd.equals("valid")){

			ArrayList<String[]> myFilestamp2 = Operator.cloneArrayList(myFile);

			Operator.dateStringtoTimestamp(myFilestamp2, columnDay, dateFormat);

			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date myDate= new Date();
			try {
				myDate =  format.parse(date);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}

			long timestamp = myDate.getTime();
			date = Long.toString(timestamp);
			for (int i = 1; i<myFile.size(); i++){

				if(myFile.get(i)[columnID].equals(userString) && Operator.compareTimeStamp(date, myFilestamp2.get(i)[columnDay], 86400000)
						&& Operator.compareTimeStamp(myFilestamp2.get(i)[columnDay], date, 0)){	
					myFilestampForWriting.add(myFile.get(i));

					for(int j =0; j< myFile.get(i).length;j++){
						this.textArea.append(myFile.get(0)[j]+" : "+myFile.get(i)[j].toString()+" ; ");
					}
					this.textArea.append("\n");
				}
			}
		}

		else if (cmd.equals("back")){
			frame.changePanel(new AccessView1(frame, myFile));
		}

		else if (cmd.equals("comboUser")){
			JComboBox<String> choice = (JComboBox<String>)e.getSource();
			userString = choice.getSelectedItem().toString();
		}

		else if (cmd.equals("comboDay")){
			JComboBox<String> choice = (JComboBox<String>)e.getSource();
			date = choice.getSelectedItem().toString();
		}

		else if (cmd.equals("write")){
			if(textFieldFilePath.getText().equals("")){
				textArea.append("Please, fill file name. \n");
			}
			else {
				try {
					Writer.writeCsv(myFilestampForWriting, "CsvData/"+textFieldFilePath.getText()+".csv");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}

		else if (cmd.equals("erase")){
			textArea.setText("");
			int i = 1;
			while (i<myFilestampForWriting.size()){
				myFilestampForWriting.remove(i);
			}
		}
	}
}
