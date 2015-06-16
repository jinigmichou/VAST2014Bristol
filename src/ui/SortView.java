package ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Date;

import javafx.scene.control.ComboBox;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;

import core.Operator;
import core.Writer;

import javax.swing.JTextArea;

public class SortView extends JPanel implements ActionListener {
	
	private JComboBox<String> comboBox;
	private String[] title;
	private int columnChoosen;
	private ArrayList<String[]> myFile;
	private JTextArea textArea;
	/**
	 * Create the panel.
	 */
	public SortView(String[] title, ArrayList<String[]> myFile) {
		
		this.myFile=myFile;
		this.title=title;
		int columnChoosen=0;
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		
		setBackground(Color.LIGHT_GRAY);
		
		JLabel lblSelectReferenceColumn = new JLabel("Select reference column");
		springLayout.putConstraint(SpringLayout.NORTH, lblSelectReferenceColumn, 61, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblSelectReferenceColumn, 34, SpringLayout.WEST, this);
		add(lblSelectReferenceColumn);
		
		JComboBox comboBox = new JComboBox(title);
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
		
		JButton btnCancel = new JButton("Cancel");
		springLayout.putConstraint(SpringLayout.EAST, btnCancel, -22, SpringLayout.WEST, btnValid);
		springLayout.putConstraint(SpringLayout.NORTH, btnValid, 0, SpringLayout.NORTH, btnCancel);
		springLayout.putConstraint(SpringLayout.NORTH, btnCancel, 25, SpringLayout.SOUTH, comboBox);
		btnCancel.addActionListener(this);
		btnCancel.setActionCommand("Cancel");
		add(btnCancel);
		
		 textArea = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(textArea);
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 118, SpringLayout.SOUTH, lblSelectReferenceColumn);
		springLayout.putConstraint(SpringLayout.WEST, scrollPane, 31, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, 269, SpringLayout.SOUTH, lblSelectReferenceColumn);
		springLayout.putConstraint(SpringLayout.EAST, scrollPane, 485, SpringLayout.WEST, this);
		textArea.setEditable(false);
		add(scrollPane);


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
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String cmd = e.getActionCommand();
		if(cmd.equals("Valid")){ 
			// We delete title column for sorting
			this.getMyFile().remove(0);
			
			Operator.sortColumn(this.getMyFile(), columnChoosen);
			String filepath = "fileSorted";
			this.getMyFile().add(0, this.getTitle());
			ArrayList<String[]> test3 = Operator.verifyJourney(getMyFile());
			
		
			try {
				Writer.writeCsv(this.getMyFile(), filepath+".csv");
				Writer.writeCsv(test3, filepath+"Error.csv");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Date date =new Date();
			textArea.append("File "+filepath+".csv has been created at "+Operator.usingDateFormatter(date.getTime())+"\n");
			textArea.append("File "+filepath+"Error.csv has been created at "+Operator.usingDateFormatter(date.getTime())+"\n");
		}
		else if(cmd.equals("Cancel")){ 
			HomeView homeview=new HomeView();
			MainView.changePanel(homeview);
			
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
