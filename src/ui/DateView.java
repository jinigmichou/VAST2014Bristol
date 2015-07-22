package ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SpringLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;

import core.Operator;
import core.Writer;

import javax.swing.JTextField;
import javax.swing.JTextArea;

public class DateView extends JPanel implements ActionListener {

	private MainView frame;
	private ArrayList<String[]> myFile;
	private int column;
	private String initialFormat;
	private String requiredFormat;
	private JTextField textFieldFileName;
	private JTextArea textArea;

	public DateView (MainView frame, ArrayList<String[]> myFile) {
		this.frame = frame;
		this.myFile = myFile;
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		setBackground(Color.LIGHT_GRAY);
		setSize(640, 480);

		JLabel lblColumnReference = new JLabel("Column Reference: ");
		springLayout.putConstraint(SpringLayout.NORTH, lblColumnReference, 55, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, lblColumnReference, -453, SpringLayout.EAST, this);
		add(lblColumnReference);

		JLabel lblNewLabel = new JLabel("Initial Date Format: ");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel, 44, SpringLayout.SOUTH, lblColumnReference);
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel, 0, SpringLayout.WEST, lblColumnReference);
		add(lblNewLabel);

		JLabel lblRequiredDateFormat = new JLabel("Required Date Format:");
		springLayout.putConstraint(SpringLayout.NORTH, lblRequiredDateFormat, 51, SpringLayout.SOUTH, lblNewLabel);
		springLayout.putConstraint(SpringLayout.WEST, lblRequiredDateFormat, 0, SpringLayout.WEST, lblColumnReference);
		add(lblRequiredDateFormat);

		String [] dateFormatInitial = {"dd/MM/yyyy HH:mm:ss","dd-MM-yyyy HH:mm:ss","yyyy-MM-dd'T'HH:mm","MM/dd/yyy HH:mm:ss", "yyyy.MM.dd G 'at' HH:mm:ss z", "EEE, MMM d, ''yy","Timestamp"};
		JComboBox comboBoxInitialFormat = new JComboBox(dateFormatInitial);
		springLayout.putConstraint(SpringLayout.WEST, comboBoxInitialFormat, 35, SpringLayout.EAST, lblNewLabel);
		springLayout.putConstraint(SpringLayout.EAST, comboBoxInitialFormat, -276, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.NORTH, comboBoxInitialFormat, -4, SpringLayout.NORTH, lblNewLabel);
		comboBoxInitialFormat.addActionListener(this);
		comboBoxInitialFormat.setActionCommand("comboBoxInitialFormat");
		add(comboBoxInitialFormat);

		String [] dateFormatRequired = {"dd/MM/yyyy HH:mm:ss","dd/MM/yyyy HH:mm","dd/MM/yyyy","yyyy-MM-dd'T'HH:mm","Timestamp","MM/dd/yyy HH:mm:ss"};
		JComboBox comboBoxRequiredFormat = new JComboBox(dateFormatRequired);
		springLayout.putConstraint(SpringLayout.NORTH, comboBoxRequiredFormat, -4, SpringLayout.NORTH, lblRequiredDateFormat);
		springLayout.putConstraint(SpringLayout.WEST, comboBoxRequiredFormat, 20, SpringLayout.EAST, lblRequiredDateFormat);
		springLayout.putConstraint(SpringLayout.EAST, comboBoxRequiredFormat, -276, SpringLayout.EAST, this);
		comboBoxRequiredFormat.addActionListener(this);
		comboBoxRequiredFormat.setActionCommand("comboBoxRequiredDate");
		add(comboBoxRequiredFormat);

		JButton btnValid = new JButton("Valid");
		springLayout.putConstraint(SpringLayout.EAST, btnValid, 0, SpringLayout.EAST, comboBoxInitialFormat);
		btnValid.addActionListener(this);
		btnValid.setActionCommand("Valid");
		add(btnValid);

		JButton btnBack = new JButton("Back");
		springLayout.putConstraint(SpringLayout.NORTH, btnBack, 0, SpringLayout.NORTH, btnValid);
		springLayout.putConstraint(SpringLayout.EAST, btnBack, -24, SpringLayout.WEST, btnValid);
		btnBack.addActionListener(this);
		btnBack.setActionCommand("back");
		add(btnBack);

		JComboBox comboBoxColumn = new JComboBox(myFile.get(0));
		springLayout.putConstraint(SpringLayout.NORTH, comboBoxColumn, -4, SpringLayout.NORTH, lblColumnReference);
		springLayout.putConstraint(SpringLayout.WEST, comboBoxColumn, 0, SpringLayout.WEST, comboBoxInitialFormat);
		springLayout.putConstraint(SpringLayout.EAST, comboBoxColumn, 1, SpringLayout.EAST, comboBoxInitialFormat);
		comboBoxColumn.addActionListener(this);
		comboBoxColumn.setActionCommand("comboBoxColumn");
		add(comboBoxColumn);

		textFieldFileName = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, btnValid, 18, SpringLayout.SOUTH, textFieldFileName);
		springLayout.putConstraint(SpringLayout.WEST, textFieldFileName, 0, SpringLayout.WEST, comboBoxInitialFormat);
		add(textFieldFileName);
		textFieldFileName.setColumns(10);

		JLabel lblNameOfNew = new JLabel("Name of new file: ");
		springLayout.putConstraint(SpringLayout.NORTH, lblNameOfNew, 52, SpringLayout.SOUTH, lblRequiredDateFormat);
		springLayout.putConstraint(SpringLayout.NORTH, textFieldFileName, -6, SpringLayout.NORTH, lblNameOfNew);
		springLayout.putConstraint(SpringLayout.WEST, lblNameOfNew, 0, SpringLayout.WEST, lblColumnReference);
		add(lblNameOfNew);

		textArea = new JTextArea();
		textArea.setEditable(false);
		JScrollPane scrollpane = new JScrollPane(textArea);
		springLayout.putConstraint(SpringLayout.NORTH, scrollpane, 27, SpringLayout.SOUTH, btnValid);
		springLayout.putConstraint(SpringLayout.WEST, scrollpane, 65, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, scrollpane, 119, SpringLayout.SOUTH, btnValid);
		springLayout.putConstraint(SpringLayout.EAST, scrollpane, 7, SpringLayout.EAST, comboBoxInitialFormat);
		add(scrollpane);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String cmd = e.getActionCommand();
		if(cmd.equals("Valid")){

			if (textFieldFileName.getText().equals("")){
				textArea.append("Please, select a file name. \n");
			}

			else{
				if(requiredFormat.equals("Timestamp")){
					Operator.dateStringtoTimestamp(myFile, column, initialFormat);
				}

				else if(initialFormat.equals("Timestamp")){
					Operator.timeStampToDate(myFile, column, requiredFormat);
				}

				else{
					Operator.dateStringtoTimestamp(myFile, column, initialFormat);
					Operator.timeStampToDate(myFile, column, requiredFormat);
				}

				try {
					Writer.writeCsv(myFile, "CsvData/"+textFieldFileName.getText()+".csv");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				textArea.append("Operation was a success. \n");
			}
		}

		else if (cmd.equals("comboBoxColumn")){
			JComboBox<String> choice = (JComboBox<String>)e.getSource();
			column = choice.getSelectedIndex();
		}

		else if (cmd.equals("comboBoxInitialFormat")){
			JComboBox<String> choice = (JComboBox<String>)e.getSource();
			initialFormat = choice.getSelectedItem().toString();
		}

		else if (cmd.equals("comboBoxRequiredDate")){
			JComboBox<String> choice = (JComboBox<String>)e.getSource();
			requiredFormat = choice.getSelectedItem().toString();
		}

		else if (cmd.equals("back")){
			frame.changePanel(new HomeView(frame));
		}
	}
}
