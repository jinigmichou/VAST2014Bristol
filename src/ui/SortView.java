package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class SortView extends JPanel implements ActionListener {


	/**
	 * Create the panel.
	 */
	public SortView(String[] title) {

		SpringLayout springLayout = new SpringLayout();

		
		JLabel lblSelectReferenceColumn = new JLabel("Select reference column");
		springLayout.putConstraint(SpringLayout.NORTH, lblSelectReferenceColumn, 61, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblSelectReferenceColumn, 34, SpringLayout.WEST, this);
		add(lblSelectReferenceColumn);
		
		JComboBox<String> comboBox = new JComboBox<String>(title);
		springLayout.putConstraint(SpringLayout.NORTH, comboBox, 61, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, comboBox, 30, SpringLayout.EAST, lblSelectReferenceColumn);
		springLayout.putConstraint(SpringLayout.EAST, comboBox, 157, SpringLayout.EAST, lblSelectReferenceColumn);
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
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String cmd = e.getActionCommand();
		if(cmd.equals("Valid")){ 
			
			
		}
		else if(cmd.equals("Cancel")){ 
			HomeView homeview=new HomeView();
			MainView.changePanel(homeview);
			
		}

		
	}
}
