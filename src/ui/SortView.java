package ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.Date;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;

import core.Operator;
import core.Writer;

import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * This view concerns "sort" button
 * @author jacquez
 *
 */
@SuppressWarnings("serial")
public class SortView extends JPanel implements ActionListener {

	private JComboBox<String> comboBox;
	private String[] title;
	private int columnChoosen;
	private ArrayList<String[]> myFile;
	private JTextArea textArea;
	private JTextField textFieldFileName;
	private MainView frame;

	public SortView(MainView frame, String[] title, ArrayList<String[]> myFile) {

		this.frame = frame;
		this.myFile=myFile;
		this.title=title;
		SpringLayout springLayout = new SpringLayout();
		setSize(640, 480);
		setLayout(springLayout);
		setBackground(Color.LIGHT_GRAY);

		JLabel lblSelectReferenceColumn = new JLabel("Select reference column");
		springLayout.putConstraint(SpringLayout.NORTH, lblSelectReferenceColumn, 61, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblSelectReferenceColumn, 34, SpringLayout.WEST, this);
		add(lblSelectReferenceColumn);

		JComboBox<String> comboBox = new JComboBox<String>(title);
		springLayout.putConstraint(SpringLayout.NORTH, comboBox, -4, SpringLayout.NORTH, lblSelectReferenceColumn);
		springLayout.putConstraint(SpringLayout.WEST, comboBox, 18, SpringLayout.EAST, lblSelectReferenceColumn);
		springLayout.putConstraint(SpringLayout.EAST, comboBox, 173, SpringLayout.EAST, lblSelectReferenceColumn);
		comboBox.addActionListener(this);
		comboBox.setActionCommand("combo");
		add(comboBox);

		JLabel lblCsvSort = new JLabel("Csv Sort");
		springLayout.putConstraint(SpringLayout.NORTH, lblCsvSort, 10, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblCsvSort, 185, SpringLayout.WEST, this);
		add(lblCsvSort);

		JButton btnValid = new JButton("Valid");
		springLayout.putConstraint(SpringLayout.EAST, btnValid, 0, SpringLayout.EAST, comboBox);
		btnValid.addActionListener(this);
		btnValid.setActionCommand("Valid");
		add(btnValid);

		JButton btnCancel = new JButton("Back");
		springLayout.putConstraint(SpringLayout.EAST, btnCancel, -22, SpringLayout.WEST, btnValid);
		springLayout.putConstraint(SpringLayout.NORTH, btnValid, 0, SpringLayout.NORTH, btnCancel);
		springLayout.putConstraint(SpringLayout.NORTH, btnCancel, 25, SpringLayout.SOUTH, comboBox);
		btnCancel.addActionListener(this);
		btnCancel.setActionCommand("Back");
		add(btnCancel);

		textArea = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(textArea);
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 118, SpringLayout.SOUTH, lblSelectReferenceColumn);
		springLayout.putConstraint(SpringLayout.WEST, scrollPane, 31, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, 269, SpringLayout.SOUTH, lblSelectReferenceColumn);
		springLayout.putConstraint(SpringLayout.EAST, scrollPane, 485, SpringLayout.WEST, this);
		textArea.setEditable(false);
		add(scrollPane);

		JLabel lblNameOfSorted = new JLabel("Name of sorted file: ");
		springLayout.putConstraint(SpringLayout.WEST, lblNameOfSorted, 22, SpringLayout.EAST, comboBox);
		springLayout.putConstraint(SpringLayout.SOUTH, lblNameOfSorted, 0, SpringLayout.SOUTH, lblSelectReferenceColumn);
		add(lblNameOfSorted);

		textFieldFileName = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textFieldFileName, 0, SpringLayout.NORTH, comboBox);
		springLayout.putConstraint(SpringLayout.WEST, textFieldFileName, 21, SpringLayout.EAST, lblNameOfSorted);
		add(textFieldFileName);
		textFieldFileName.setColumns(10);
		
		columnChoosen=0;
	}

	public ArrayList<String[]> getMyFile() {
		return myFile;
	}
	public void setMyFile(ArrayList<String[]> myFile) {
		this.myFile = myFile;
	}
	public JComboBox<String> getComboBox() {
		return comboBox;
	}
	public void setComboBox(JComboBox<String> comboBox) {
		this.comboBox = comboBox;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String cmd = e.getActionCommand();
		if(cmd.equals("Valid")){ 

			// We delete title column for sorting
			this.getMyFile().remove(0);
			Operator.sortColumn(this.getMyFile(), columnChoosen);
			this.getMyFile().add(0, this.getTitle());

			MainView.logger.log(Level.WARNING, "Choice of " + this.getTitle()[columnChoosen] + " from the ComboBox to sort the file");

			ArrayList<String[]> errorFile = Operator.verifyJourney(getMyFile());
			String filepath = "CsvData/"+this.textFieldFileName.getText();

			try {
				Writer.writeCsv(this.getMyFile(), filepath+".csv");
				Writer.writeCsv(errorFile, filepath+"Error.csv");

				MainView.logger.log(Level.WARNING, "It created : " + filepath + ".csv and " 
						+ filepath + " Error.csv");
			} catch (Exception e1) {
				e1.printStackTrace();
			}

			Date date =new Date();
			textArea.append("File "+filepath+".csv has been created at "+Operator.usingDateFormatter(date.getTime())+"\n");
			textArea.append("File "+filepath+"Error.csv has been created at "+Operator.usingDateFormatter(date.getTime())+"\n");
		}

		else if(cmd.equals("Cancel")){ 
			MainView.logger.log(Level.INFO, "Go back to homePage");
			frame.changePanel(new HomeView(frame));
		}

		else if(cmd.equals("Back")){ 

			MainView.logger.log(Level.INFO, "Go back to homePage");
			frame.changePanel(new HomeView(frame));
		}

		else if(cmd.equals("combo")){ 
			JComboBox<String> choice = (JComboBox<String>)e.getSource();
			columnChoosen = choice.getSelectedIndex();
		}
	}

	public String[] getTitle() {
		return title;
	}

	public void setTitle(String[] title) {
		this.title = title;
	}
}
