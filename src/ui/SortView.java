package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.logging.Level;

import javafx.scene.control.ComboBox;

import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;

import core.Operator;
import core.Writer;

public class SortView extends JPanel implements ActionListener {
	
	private JComboBox<String> comboBox;
	private String[] title;
	private int columnChoosen;
	private ArrayList<String[]> myFile;
	/**
	 * Create the panel.
	 */
	public SortView(String[] title, ArrayList<String[]> myFile) {
		
		this.myFile=myFile;
		this.title=title;
		int columnChoosen=0;
		SpringLayout springLayout = new SpringLayout();
				
		JLabel lblSelectReferenceColumn = new JLabel("Select reference column");
		springLayout.putConstraint(SpringLayout.NORTH, lblSelectReferenceColumn, 61, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblSelectReferenceColumn, 34, SpringLayout.WEST, this);
		add(lblSelectReferenceColumn);
		
		JComboBox<String> comboBox = new JComboBox<String>(title);
		springLayout.putConstraint(SpringLayout.NORTH, comboBox, 61, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, comboBox, 30, SpringLayout.EAST, lblSelectReferenceColumn);
		springLayout.putConstraint(SpringLayout.EAST, comboBox, 157, SpringLayout.EAST, lblSelectReferenceColumn);
		comboBox.addActionListener(this);
		comboBox.setActionCommand("combo");
		add(comboBox);
		
		JLabel lblCsvSort = new JLabel("Csv Sort");
		springLayout.putConstraint(SpringLayout.NORTH, lblCsvSort, 10, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblCsvSort, 185, SpringLayout.WEST, this);
		add(lblCsvSort);
		
		JButton btnValid = new JButton("Valid");
		springLayout.putConstraint(SpringLayout.NORTH, btnValid, 37, SpringLayout.SOUTH, comboBox);
		springLayout.putConstraint(SpringLayout.EAST, btnValid, -84, SpringLayout.EAST, this);
		btnValid.addActionListener(this);
		btnValid.setActionCommand("Valid");
		add(btnValid);
		
		JButton btnCancel = new JButton("Cancel");
		springLayout.putConstraint(SpringLayout.SOUTH, btnCancel, 0, SpringLayout.SOUTH, btnValid);
		springLayout.putConstraint(SpringLayout.EAST, btnCancel, -30, SpringLayout.WEST, btnValid);
		btnCancel.addActionListener(this);
		btnCancel.setActionCommand("Cancel");
		add(btnCancel);

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

			this.getMyFile().add(0, this.getTitle());
			MainView.logger.log(Level.INFO, "Choice of " + this.getTitle()[columnChoosen] + " from the ComboBox to sort the file");
			
			try {
				Writer.writeCsv(this.getMyFile(), "SortFile.csv");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
		}
		else if(cmd.equals("Cancel")){ 
			HomeView homeview=new HomeView();
			MainView.changePanel(homeview);
			MainView.logger.log(Level.INFO, "Cancel, go back to Home Page ");
			
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
