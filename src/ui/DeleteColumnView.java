package ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;

import core.Operator;
import core.Writer;

public class DeleteColumnView extends JPanel implements ActionListener {
	private JTextField textField;
	private JTextArea textArea;
	private MainView frame;
	private ArrayList<String[]> myFile;
	private int columnToDelete;

	/**
	 * Create the panel.
	 */
	public DeleteColumnView(MainView frame, ArrayList<String[]> myFile) {
		this.frame = frame;
		this.myFile = myFile;
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		setBackground(Color.LIGHT_GRAY);
		//this.setSize(frame.getSize());
		setSize(640, 480);

		JLabel lblChooseAColumn = new JLabel("Choose a column: ");
		springLayout.putConstraint(SpringLayout.NORTH, lblChooseAColumn, 33, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblChooseAColumn, 40, SpringLayout.WEST, this);
		add(lblChooseAColumn);

		JComboBox comboBoxDeletedColumn = new JComboBox(myFile.get(0));
		springLayout.putConstraint(SpringLayout.NORTH, comboBoxDeletedColumn, 33, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, comboBoxDeletedColumn, 24, SpringLayout.EAST, lblChooseAColumn);
		springLayout.putConstraint(SpringLayout.EAST, comboBoxDeletedColumn, 180, SpringLayout.EAST, lblChooseAColumn);
		comboBoxDeletedColumn.addActionListener(this);
		comboBoxDeletedColumn.setActionCommand("comboColumn");
		add(comboBoxDeletedColumn);

		JLabel lblNameOfNew = new JLabel("Name of new file: ");
		springLayout.putConstraint(SpringLayout.NORTH, lblNameOfNew, 52, SpringLayout.SOUTH, lblChooseAColumn);
		springLayout.putConstraint(SpringLayout.WEST, lblNameOfNew, 0, SpringLayout.WEST, lblChooseAColumn);
		add(lblNameOfNew);

		textField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textField, 41, SpringLayout.SOUTH, comboBoxDeletedColumn);
		springLayout.putConstraint(SpringLayout.WEST, textField, 0, SpringLayout.WEST, comboBoxDeletedColumn);
		springLayout.putConstraint(SpringLayout.EAST, textField, 0, SpringLayout.EAST, comboBoxDeletedColumn);
		add(textField);
		textField.setColumns(10);

		JButton btnValid = new JButton("Valid");
		springLayout.putConstraint(SpringLayout.NORTH, btnValid, 26, SpringLayout.SOUTH, textField);
		springLayout.putConstraint(SpringLayout.EAST, btnValid, 0, SpringLayout.EAST, comboBoxDeletedColumn);
		btnValid.addActionListener(this);
		btnValid.setActionCommand("valid");
		add(btnValid);

		JButton btnBack = new JButton("Back");
		springLayout.putConstraint(SpringLayout.SOUTH, btnBack, 0, SpringLayout.SOUTH, btnValid);
		springLayout.putConstraint(SpringLayout.EAST, btnBack, -4, SpringLayout.WEST, btnValid);
		btnBack.addActionListener(this);
		btnBack.setActionCommand("back");
		add(btnBack);


		textArea = new JTextArea();
		textArea.setEditable(false);
		JScrollPane scrollpane = new JScrollPane(textArea);
		springLayout.putConstraint(SpringLayout.NORTH, textArea, 10, SpringLayout.SOUTH, btnValid);
		springLayout.putConstraint(SpringLayout.WEST, textArea, 47, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, textArea, -46, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, textArea, 0, SpringLayout.EAST, comboBoxDeletedColumn);
		add(scrollpane);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String cmd = e.getActionCommand();
		if(cmd.equals("valid")){
			if (textField.getText().equals("")){
				textArea.append("Please, fill a file name");
			}
			else {
				ArrayList<String[]> myFileResult = Operator.deleteColumn(myFile, columnToDelete);
				try {
					System.out.println("coucou");
					Writer.writeCsv(myFileResult, "CsvData/"+textField.getText()+".csv");
					MainView.logger.log(Level.WARNING, "The column deleted is : " +  columnToDelete 
							+ "and the new file is " + myFileResult);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		}
		else if (cmd.equals("back")){

			MainView.logger.log(Level.INFO, "Come back to HomePage");
			frame.changePanel(new HomeView(frame));

		}
		else if (cmd.equals("comboColumn")){
			JComboBox<String> choice = (JComboBox<String>)e.getSource();
			columnToDelete = choice.getSelectedIndex();

		}

	}
}
