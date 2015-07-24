package ui;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;

import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JScrollPane;

import core.Operator;
import core.Writer;

import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * This view concerns "calculate journey" button
 * @author jacquez
 *
 */
@SuppressWarnings("serial")
public class CalculJourneyView extends JPanel implements ActionListener{

	private String[] title;
	private int S_Time;
	private int F_Time;
	private int S_Lat;
	private int S_Lon;
	private int F_Lat;
	private int F_Lon;
	private String unit;
	private ArrayList<String[]> myFile;
	private MainView frame;
	private JTextArea textArea;
	private JTextField textFieldFileName;
	/**
	 * Create the panel.
	 */
	public CalculJourneyView(MainView frame, String[] title, ArrayList<String[]> myFile) {
		this.setTitle(title);
		this.myFile=myFile;
		this.frame = frame;
		setLayout(null);
		setBackground(Color.LIGHT_GRAY);
		this.setSize(frame.getSize());
		
		//Initialization of choices
		 S_Time = 0;
		 F_Time = 0;
		 S_Lat  = 0;
		 S_Lon  = 0;
		 F_Lat  = 0;
		 F_Lon  = 0;
		 unit = "N";
		 

		JLabel lblStart = new JLabel("Start:");
		lblStart.setBounds(31, 22, 61, 16);
		add(lblStart);

		JLabel lblTime = new JLabel("Time");
		lblTime.setBounds(31, 56, 61, 16);
		add(lblTime);

		JComboBox<String> comboBox_S_Time = new JComboBox<String>(title);
		comboBox_S_Time.setBounds(70, 52, 133, 27);
		comboBox_S_Time.addActionListener(this);
		comboBox_S_Time.setActionCommand("comboBox_S_Time");
		add(comboBox_S_Time);

		JLabel lblLatitude = new JLabel("Latitude");
		lblLatitude.setBounds(231, 56, 61, 16);
		add(lblLatitude);

		JComboBox<String> comboBox_S_Lat = new JComboBox<String>(title);
		comboBox_S_Lat.setBounds(288, 52, 138, 27);
		comboBox_S_Lat.addActionListener(this);
		comboBox_S_Lat.setActionCommand("comboBox_S_Lat");
		add(comboBox_S_Lat);

		JLabel lblLongitude = new JLabel("Longitude");
		lblLongitude.setBounds(438, 56, 74, 16);
		add(lblLongitude);

		JComboBox<String> comboBox_S_Lon = new JComboBox<String>(title);
		comboBox_S_Lon.setBounds(546, 52, 133, 27);
		comboBox_S_Lon.addActionListener(this);
		comboBox_S_Lon.setActionCommand("comboBox_S_Lon");
		add(comboBox_S_Lon);

		JLabel lblFinish = new JLabel("Finish:");
		lblFinish.setBounds(31, 123, 61, 16);
		add(lblFinish);

		JLabel lblTime_1 = new JLabel("Time");
		lblTime_1.setBounds(31, 177, 61, 16);
		add(lblTime_1);

		JComboBox<String> comboBox_F_Time = new JComboBox<String>(title);
		comboBox_F_Time.setBounds(70, 173, 133, 27);
		comboBox_F_Time.addActionListener(this);
		comboBox_F_Time.setActionCommand("comboBox_F_Time");
		add(comboBox_F_Time);

		JLabel lblLatitude_1 = new JLabel("Latitude");
		lblLatitude_1.setBounds(231, 177, 61, 16);
		add(lblLatitude_1);

		JComboBox<String> comboBox_F_Lat = new JComboBox<String>(title);
		comboBox_F_Lat.setBounds(288, 173, 138, 27);
		comboBox_F_Lat.addActionListener(this);
		comboBox_F_Lat.setActionCommand("comboBox_F_Lat");
		add(comboBox_F_Lat);

		JLabel lblLongitude_1 = new JLabel("Longitude");
		lblLongitude_1.setBounds(438, 177, 74, 16);
		add(lblLongitude_1);

		JComboBox<String> comboBox_F_Lon = new JComboBox<String>(title);
		comboBox_F_Lon.setBounds(546, 173, 133, 27);
		comboBox_F_Lon.addActionListener(this);
		comboBox_F_Lon.setActionCommand("comboBox_F_Lon");
		add(comboBox_F_Lon);

		JButton btnValid = new JButton("Valid");
		btnValid.setBounds(433, 243, 117, 29);
		btnValid.addActionListener(this);
		btnValid.setActionCommand("Valid");
		add(btnValid);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(562, 243, 117, 29);
		btnCancel.addActionListener(this);
		btnCancel.setActionCommand("Cancel");
		add(btnCancel);

		JLabel lblDistanceUnit = new JLabel("Distance unit:");
		lblDistanceUnit.setBounds(31, 235, 110, 16);
		add(lblDistanceUnit);

		JComboBox<String> comboBox_unit = new JComboBox<String>();
		comboBox_unit.addItem("Mile");
		comboBox_unit.addItem("Km");
		comboBox_unit.setBounds(126, 231, 90, 27);
		comboBox_unit.addActionListener(this);
		comboBox_unit.setActionCommand("Unit");
		add(comboBox_unit);

		textArea = new JTextArea();
		textArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(30, 344, 649, 118);
		add(scrollPane);

		JLabel lblNameOfNew = new JLabel("Name of new file: ");
		lblNameOfNew.setBounds(31, 284, 133, 16);
		add(lblNameOfNew);

		textFieldFileName = new JTextField();
		textFieldFileName.setBounds(186, 278, 169, 28);
		add(textFieldFileName);
		textFieldFileName.setColumns(10);

	}

	@SuppressWarnings("unchecked")
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String cmd = e.getActionCommand();
		if(cmd.equals("Valid")){

			ArrayList<String[]> myFile2 = new ArrayList<String[]>();
			ArrayList<String[]> myFileError = new ArrayList<String[]>();

			String fileName= "./CsvData/"+this.textFieldFileName.getText();

			myFile2 = Operator.journeyCalculation(myFile, S_Time, F_Time, S_Lat, S_Lon, F_Lat, F_Lon, unit);

			myFileError = Operator.verifyJourney(myFile2);

			try {
				Writer.writeCsv(myFile2, fileName+".csv");
				Writer.writeCsv(myFileError, fileName+"Error.csv");
			} catch (Exception e1) {
				e1.printStackTrace();
			}

			Date date =new Date();

			this.getTextArea().append("file "+fileName+".csv"+" has been created at "+Operator.usingDateFormatter(date.getTime())+"\n");
			this.getTextArea().append("file "+fileName+"Error.csv"+" has been created at "+Operator.usingDateFormatter(date.getTime())+"\n");

			MainView.logger.log(Level.WARNING, "Calculate journey with parameters : \n" + "file : " + fileName 
					+ ", start-time : " + this.getTitle()[S_Time]
							+ ", final-time : " + this.getTitle()[F_Time]
									+ ", start-lat : " +  this.getTitle()[S_Lat]
											+ ", start-long : " + this.getTitle()[S_Lon]
													+ ", final-time : " + this.getTitle()[F_Lat]
															+ ", fianl-time : " + this.getTitle()[F_Lon]
																	+ ", unit : " + unit);

			MainView.logger.log(Level.WARNING, "file : \n" + fileName + ".csv and "
					+ fileName + "Error.csv" + " have been created");

		}

		else if (cmd.equals("Cancel")){
			MainView.logger.log(Level.INFO, "Go back to homePage");
			frame.changePanel(new HomeView(frame));	
		}

		else if (cmd.equals("comboBox_S_Time")){
			JComboBox<String> choice = (JComboBox<String>)e.getSource();
			S_Time = choice.getSelectedIndex();
		}

		else if (cmd.equals("comboBox_F_Time")){
			JComboBox<String> choice = (JComboBox<String>)e.getSource();
			F_Time = choice.getSelectedIndex();
		}

		else if (cmd.equals("comboBox_S_Lat")){
			JComboBox<String> choice = (JComboBox<String>)e.getSource();
			S_Lat = choice.getSelectedIndex();
		}

		else if (cmd.equals("comboBox_S_Lon")){
			JComboBox<String> choice = (JComboBox<String>)e.getSource();
			S_Lon = choice.getSelectedIndex();
		}

		else if (cmd.equals("comboBox_F_Lat")){
			JComboBox<String> choice = (JComboBox<String>)e.getSource();
			F_Lat = choice.getSelectedIndex();
		}

		else if (cmd.equals("comboBox_F_Lon")){
			JComboBox<String> choice = (JComboBox<String>)e.getSource();
			F_Lon = choice.getSelectedIndex();
		}

		else if (cmd.equals("Unit")){
			JComboBox<String> choice = (JComboBox<String>)e.getSource();
			int unit_index = choice.getSelectedIndex();
			if (unit_index==0){
				unit="N";
			}
			else if (unit_index==1){
				unit="K";
			}
		}
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}

	public String[] getTitle() {
		return title;
	}
	public void setTitle(String[] title) {
		this.title = title;
	}
}
