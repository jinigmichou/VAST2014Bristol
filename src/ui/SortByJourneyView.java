package ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.Date;

import javax.swing.ComboBoxModel;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;

import core.Operator;
import core.Writer;

import javax.swing.JTextArea;
import javax.swing.JTextField;

public class SortByJourneyView extends JPanel implements ActionListener{

	private Integer timeChoosen;
	private ArrayList<String[]> myFile;
	private JTextArea textArea;
	private JTextField textFieldfileName;
	private MainView frame;
	/**
	 * Create the panel.
	 */
	public SortByJourneyView(MainView frame, ArrayList<String[]> myFile)  {
		this.frame = frame;
		this.myFile=myFile;
		setLayout(null);
		setBackground(Color.LIGHT_GRAY);
		//this.setSize(frame.getSize());
		setSize(640, 480);

		JLabel lblTimeBetweenTwo = new JLabel("Time between two journeys: ");
		lblTimeBetweenTwo.setBounds(36, 75, 180, 16);
		add(lblTimeBetweenTwo);

		Integer[] timeJourney = {1,2,5,10,20};
		JComboBox comboBoxTime = new JComboBox(timeJourney);
		comboBoxTime.setBounds(228, 71, 133, 27);

		comboBoxTime.addActionListener(this);
		comboBoxTime.setActionCommand("combo");
		add(comboBoxTime);

		JButton btnCalculate = new JButton("Order By Journey");
		btnCalculate.setBounds(89, 134, 152, 29);
		btnCalculate.addActionListener(this);
		btnCalculate.setActionCommand("Order");
		add(btnCalculate);

		JButton btnCancel = new JButton("Back");
		btnCancel.setBounds(253, 134, 117, 29);
		btnCancel.addActionListener(this);
		btnCancel.setActionCommand("Cancel");
		add(btnCancel);

		JLabel lblMinutes = new JLabel("minutes");
		lblMinutes.setBounds(370, 75, 61, 16);
		add(lblMinutes);

		textArea = new JTextArea();
		textArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(36, 211, 533, 143);
		add(scrollPane);

		JLabel lblNameOfNew = new JLabel("Name of new file: ");
		lblNameOfNew.setBounds(39, 29, 126, 16);
		add(lblNameOfNew);

		textFieldfileName = new JTextField();
		textFieldfileName.setBounds(177, 23, 184, 28);
		add(textFieldfileName);
		textFieldfileName.setColumns(10);

	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String cmd = e.getActionCommand();
		if(cmd.equals("Order")){ 
			getMyFile().remove(0);
			Operator.tranformDate(this.getMyFile(), 0);
			System.out.println(timeChoosen*60000);
			ArrayList<String[]> test = Operator.sortTimestamp(this.getMyFile(), 0, timeChoosen*60000);
			ArrayList<String[]> testError = Operator.verifyJourney(test);
			String fileName = "CsvData/"+this.textFieldfileName.getText();
			try {


				MainView.logger.log(Level.INFO, "Click on the buttom SortByJourney, new file is "+this.textFieldfileName.getText());

				Writer.writeCsv(test, fileName+".csv");
				Writer.writeCsv(testError, fileName+"Error.csv");

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Date date =new Date();
			//date = format.parse(date.getTime());

			this.textArea.append("file "+fileName+".csv"+" has been created at "+Operator.usingDateFormatter(date.getTime())+"\n");
			this.textArea.append("file "+fileName+"Error.csv"+" has been created at "+Operator.usingDateFormatter(date.getTime())+"\n");

			frame.changePanel(new HomeView(frame));

		}
		else if(cmd.equals("Cancel")){

			frame.changePanel(new HomeView(frame));

		}
		else if (cmd.equals("combo")){
			JComboBox<String> choice = (JComboBox<String>)e.getSource();
			timeChoosen = (Integer) choice.getSelectedItem();

		}
	}

	public ArrayList<String[]> getMyFile() {
		return myFile;
	}

	public void setMyFile(ArrayList<String[]> myFile) {
		this.myFile = myFile;
	}
}
