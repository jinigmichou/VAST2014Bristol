package ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;

import core.Operator;
import core.Writer;

public class DateView extends JPanel implements ActionListener {
	private MainView frame;
	private ArrayList<String[]> myFile;
	private int column;
	private String initialFormat;
	private String requiredFormat;
	/**
	 * Create the panel.
	 */
	public DateView (MainView frame, ArrayList<String[]> myFile) {
		this.frame = frame;
		this.myFile = myFile;
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		setBackground(Color.LIGHT_GRAY);

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

		String [] dateFormatInitial = {"dd/MM/yyyy HH:mm:ss","yyyy-MM-dd'T'HH:mm", "Timestamp","MM/dd/yyy HH:mm:ss"};
		JComboBox comboBoxInitialFormat = new JComboBox(dateFormatInitial);
		springLayout.putConstraint(SpringLayout.WEST, comboBoxInitialFormat, 35, SpringLayout.EAST, lblNewLabel);
		springLayout.putConstraint(SpringLayout.EAST, comboBoxInitialFormat, -276, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.NORTH, comboBoxInitialFormat, -4, SpringLayout.NORTH, lblNewLabel);
		comboBoxInitialFormat.addActionListener(this);
		comboBoxInitialFormat.setActionCommand("comboBoxInitialFormat");
		add(comboBoxInitialFormat);

		String [] dateFormatRequired = {"dd/MM/yyyy HH:mm:ss","dd/MM/yyyy HH:mm","dd/MM/yyyy","Timestamp"};
		JComboBox comboBoxRequiredFormat = new JComboBox(dateFormatRequired);
		springLayout.putConstraint(SpringLayout.NORTH, comboBoxRequiredFormat, -4, SpringLayout.NORTH, lblRequiredDateFormat);
		springLayout.putConstraint(SpringLayout.WEST, comboBoxRequiredFormat, 20, SpringLayout.EAST, lblRequiredDateFormat);
		springLayout.putConstraint(SpringLayout.EAST, comboBoxRequiredFormat, -276, SpringLayout.EAST, this);
		comboBoxRequiredFormat.addActionListener(this);
		comboBoxRequiredFormat.setActionCommand("comboBoxRequiredDate");
		add(comboBoxRequiredFormat);

		JButton btnValid = new JButton("Valid");
		springLayout.putConstraint(SpringLayout.NORTH, btnValid, 41, SpringLayout.SOUTH, comboBoxRequiredFormat);
		springLayout.putConstraint(SpringLayout.EAST, btnValid, -276, SpringLayout.EAST, this);
		btnValid.addActionListener(this);
		btnValid.setActionCommand("Valid");
		add(btnValid);

		JButton btnBack = new JButton("Back");
		springLayout.putConstraint(SpringLayout.NORTH, btnBack, 0, SpringLayout.NORTH, btnValid);
		springLayout.putConstraint(SpringLayout.EAST, btnBack, -24, SpringLayout.WEST, btnValid);
		btnBack.addActionListener(this);
		btnBack.setActionCommand("back");
		add(btnBack);

		JComboBox<String> comboBoxColumn = new JComboBox<String>(myFile.get(0));
		springLayout.putConstraint(SpringLayout.NORTH, comboBoxColumn, -4, SpringLayout.NORTH, lblColumnReference);
		springLayout.putConstraint(SpringLayout.WEST, comboBoxColumn, 0, SpringLayout.WEST, comboBoxInitialFormat);
		springLayout.putConstraint(SpringLayout.EAST, comboBoxColumn, 1, SpringLayout.EAST, comboBoxInitialFormat);
		comboBoxColumn.addActionListener(this);
		comboBoxColumn.setActionCommand("comboBoxColumn");
		add(comboBoxColumn);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String cmd = e.getActionCommand();
		if(cmd.equals("Valid")){
			Operator.dateStringtoTimestamp(myFile, column, initialFormat);
			Operator.timeStampToDate(myFile, column, requiredFormat);

			try {
				Writer.writeCsv(myFile, "CsvData/testsurlesdates.csv");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
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
