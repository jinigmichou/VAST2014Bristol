package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ComboBoxModel;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;

import core.Operator;
import core.Writer;

public class SortByJourneyView extends JPanel implements ActionListener{
	private Integer timeChoosen;
	private ArrayList<String[]> myFile;
	/**
	 * Create the panel.
	 */
	public SortByJourneyView( ArrayList<String[]> myFile)  {
		this.myFile=myFile;
		
		
		JLabel lblTimeBetweenTwo = new JLabel("Time between two journeys: ");
		lblTimeBetweenTwo.setBounds(49, 52, 180, 16);
		add(lblTimeBetweenTwo);
		
		Integer[] timeJourney = {1,2,5,10,20};
		JComboBox<Integer> comboBoxTime = new JComboBox<Integer>(timeJourney);
		comboBoxTime.addActionListener(this);
		comboBoxTime.setActionCommand("combo");
		add(comboBoxTime);
		
		JButton btnCalculate = new JButton("Order By Journey");
		btnCalculate.setBounds(112, 134, 117, 29);
		btnCalculate.addActionListener(this);
		btnCalculate.setActionCommand("Order");
		add(btnCalculate);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(253, 134, 117, 29);
		add(btnCancel);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String cmd = e.getActionCommand();
		if(cmd.equals("Order")){ 
			getMyFile().remove(0);
			Operator.tranformDate(this.getMyFile(), 0);
			ArrayList<String[]> test3 = Operator.sortTimestamp(this.getMyFile(), 0);
			try {
				Writer.writeCsv(test3, "sortByjourney.csv");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			
		}
		else if(cmd.equals("Cancel")){
			
		}
		else if (cmd.equals("combo")){
			JComboBox<String> choice = (JComboBox<String>)e.getSource();
			timeChoosen = (Integer) choice.getSelectedItem();
			System.out.println(timeChoosen);
		}
	}

	public ArrayList<String[]> getMyFile() {
		return myFile;
	}

	public void setMyFile(ArrayList<String[]> myFile) {
		this.myFile = myFile;
	}
}
