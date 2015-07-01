package ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import core.Operator;
import core.Writer;

public class ActivityView2 extends JPanel implements ActionListener {

	private MainView frame;
	private ArrayList<String[]> file1;
	private ArrayList<String[]> file2;
	private int columnFile;
	private int columnDate1;
	private int columnDate2;
	private String dateFormat1;
	private String dateFormat2;

	private JTextField textFieldFilePath;
	private JTextArea textArea;
	

	public ActivityView2(MainView frame, ArrayList<String[]> file1,  ArrayList<String[]> file2) {

		this.frame = frame;
		this.file1 = file1;
		this.file2 = file2;
		this.columnDate1 = columnDate1;
		this.columnDate2 = columnDate2;


		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		setBackground(Color.LIGHT_GRAY);
		//this.setSize(frame.getSize());
		setSize(640, 480);

		JComboBox comboBox1 = new JComboBox(this.file1.get(0));
		comboBox1.addActionListener(this);
		comboBox1.setActionCommand("combo");
		add(comboBox1);

		JComboBox comboBoxDate1 = new JComboBox(this.file1.get(0));
		springLayout.putConstraint(SpringLayout.SOUTH, comboBox1, -20, SpringLayout.NORTH, comboBoxDate1);
		springLayout.putConstraint(SpringLayout.EAST, comboBox1, 0, SpringLayout.EAST, comboBoxDate1);
		springLayout.putConstraint(SpringLayout.EAST, comboBoxDate1, -204, SpringLayout.EAST, this);
		comboBoxDate1.addActionListener(this);
		comboBoxDate1.setActionCommand("comboDate1");
		add(comboBoxDate1);

		JComboBox comboBoxDate2 = new JComboBox(this.file2.get(0));
		springLayout.putConstraint(SpringLayout.WEST, comboBox1, 0, SpringLayout.WEST, comboBoxDate2);
		comboBoxDate2.addActionListener(this);
		comboBoxDate2.setActionCommand("comboDate2");
		add(comboBoxDate2);


		JLabel lblFile = new JLabel("Choose argument to link:");
		springLayout.putConstraint(SpringLayout.NORTH, lblFile, 35, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblFile, 64, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, lblFile, -382, SpringLayout.EAST, this);
		add(lblFile);


		JButton btnValid = new JButton("Valid");
		springLayout.putConstraint(SpringLayout.WEST, btnValid, 361, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, btnValid, -204, SpringLayout.EAST, this);
		btnValid.addActionListener(this);
		btnValid.setActionCommand("Valid");
		add(btnValid);

		JButton btnCancel = new JButton("back");
		springLayout.putConstraint(SpringLayout.NORTH, btnCancel, 0, SpringLayout.NORTH, btnValid);
		springLayout.putConstraint(SpringLayout.EAST, btnCancel, -6, SpringLayout.WEST, btnValid);
		btnCancel.addActionListener(this);
		btnCancel.setActionCommand("back");
		add(btnCancel);

		textArea = new JTextArea();
		textArea.setEditable(false);
		JScrollPane scrollpane = new JScrollPane(textArea);
		springLayout.putConstraint(SpringLayout.SOUTH, btnValid, -23, SpringLayout.NORTH, scrollpane);
		springLayout.putConstraint(SpringLayout.NORTH, scrollpane, 250, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.SOUTH, scrollpane, -10, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, scrollpane, 615, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.WEST, scrollpane, 24, SpringLayout.WEST, this);
		add(scrollpane);

		JLabel lblColumnDatefile = new JLabel("Column Date (File 1): ");
		springLayout.putConstraint(SpringLayout.NORTH, comboBoxDate1, -4, SpringLayout.NORTH, lblColumnDatefile);
		springLayout.putConstraint(SpringLayout.WEST, comboBoxDate1, 62, SpringLayout.EAST, lblColumnDatefile);
		springLayout.putConstraint(SpringLayout.NORTH, lblColumnDatefile, 31, SpringLayout.SOUTH, lblFile);
		springLayout.putConstraint(SpringLayout.WEST, lblColumnDatefile, 0, SpringLayout.WEST, lblFile);
		add(lblColumnDatefile);

		JLabel lblColumnDatefile_1 = new JLabel("Column Date (File 2): ");
		springLayout.putConstraint(SpringLayout.NORTH, comboBoxDate2, -4, SpringLayout.NORTH, lblColumnDatefile_1);
		springLayout.putConstraint(SpringLayout.WEST, comboBoxDate2, 64, SpringLayout.EAST, lblColumnDatefile_1);
		springLayout.putConstraint(SpringLayout.NORTH, lblColumnDatefile_1, 31, SpringLayout.SOUTH, lblColumnDatefile);
		springLayout.putConstraint(SpringLayout.EAST, lblColumnDatefile_1, 0, SpringLayout.EAST, lblColumnDatefile);
		add(lblColumnDatefile_1);
		
		JLabel lblFormat = new JLabel("Format: ");
		springLayout.putConstraint(SpringLayout.NORTH, lblFormat, 0, SpringLayout.NORTH, comboBoxDate1);
		springLayout.putConstraint(SpringLayout.WEST, lblFormat, 12, SpringLayout.EAST, comboBoxDate1);
		add(lblFormat);
		
		JLabel lblFormat_1 = new JLabel("Format: ");
		springLayout.putConstraint(SpringLayout.EAST, comboBoxDate2, -12, SpringLayout.WEST, lblFormat_1);
		springLayout.putConstraint(SpringLayout.NORTH, lblFormat_1, 0, SpringLayout.NORTH, comboBoxDate2);
		springLayout.putConstraint(SpringLayout.WEST, lblFormat_1, 0, SpringLayout.WEST, lblFormat);
		add(lblFormat_1);
		
		String [] dateFormat = {"dd/MM/yyyy HH:mm:ss","dd-MM-yyyy HH:mm:ss","yyyy-MM-dd'T'HH:mm","MM/dd/yyy HH:mm:ss", "yyyy.MM.dd G 'at' HH:mm:ss z", "EEE, MMM d, ''yy","Timestamp"};
		JComboBox comboBoxFormat1 = new JComboBox(dateFormat);
		springLayout.putConstraint(SpringLayout.NORTH, comboBoxFormat1, 0, SpringLayout.NORTH, comboBoxDate1);
		springLayout.putConstraint(SpringLayout.WEST, comboBoxFormat1, 11, SpringLayout.EAST, lblFormat);
		springLayout.putConstraint(SpringLayout.EAST, comboBoxFormat1, 115, SpringLayout.EAST, lblFormat);
		comboBoxFormat1.addActionListener(this);
		comboBoxFormat1.setActionCommand("comboFormat1");
		add(comboBoxFormat1);
		
		JComboBox comboBoxFormat2 = new JComboBox(dateFormat);
		springLayout.putConstraint(SpringLayout.NORTH, comboBoxFormat2, 20, SpringLayout.SOUTH, comboBoxFormat1);
		springLayout.putConstraint(SpringLayout.WEST, comboBoxFormat2, 11, SpringLayout.EAST, lblFormat_1);
		springLayout.putConstraint(SpringLayout.EAST, comboBoxFormat2, 115, SpringLayout.EAST, lblFormat_1);
		comboBoxFormat2.addActionListener(this);
		comboBoxFormat2.setActionCommand("comboFormat2");
		add(comboBoxFormat2);
		
		JLabel lblFileName = new JLabel("File name:");
		springLayout.putConstraint(SpringLayout.NORTH, lblFileName, 31, SpringLayout.SOUTH, lblColumnDatefile_1);
		springLayout.putConstraint(SpringLayout.EAST, lblFileName, 0, SpringLayout.EAST, lblColumnDatefile);
		add(lblFileName);
		
		textFieldFilePath = new JTextField();
		springLayout.putConstraint(SpringLayout.WEST, textFieldFilePath, 0, SpringLayout.WEST, comboBox1);
		springLayout.putConstraint(SpringLayout.SOUTH, textFieldFilePath, -6, SpringLayout.NORTH, btnValid);
		springLayout.putConstraint(SpringLayout.EAST, textFieldFilePath, 0, SpringLayout.EAST, comboBox1);
		add(textFieldFilePath);
		textFieldFilePath.setColumns(10);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String cmd = e.getActionCommand();
		if(cmd.equals("Valid")){ 
			ArrayList<String[]> finalResult = new ArrayList<String[]>();

			Operator.dateStringtoTimestamp(file2, columnDate2, dateFormat2);
			Operator.dateStringtoTimestamp(file1, columnDate1, dateFormat1);



			for (int i = 1 ; i< file1.size(); i++){
				
				ArrayList<String[]> resultStamp = new ArrayList<String[]>();
				if (i < file1.size()-1){
				resultStamp = Operator.selectValuesBetweenTwoTimestamps(file2, columnDate2, 
						file1.get(i)[columnDate1],
						file1.get(i+1)[columnDate1]);}
				// There is not activity between this two dates

				finalResult.add(file1.get(i));

				if (resultStamp.isEmpty()){

					System.out.println("result samtp:  "+resultStamp);
				}
				// There are activities between this two dates
				else {


					int j = 0;
					while (j < resultStamp.size()){
						if(!Operator.containInArray(resultStamp.get(j), file1.get(1)[columnFile])){
							resultStamp.remove(j);
						}
						else j++;	
					}
					
					Operator.appendFileToAnOther(finalResult, resultStamp);

				}

			}
			
			try {
				Writer.writeCsv(finalResult, "CsvData/"+textFieldFilePath.getText()+".csv");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		else if(cmd.equals("cancel")){ 
			frame.changePanel(new HomeView(frame));

		}
		else if(cmd.equals("combo")){ 
			JComboBox<String> choicefile = (JComboBox<String>)e.getSource();
			columnFile = choicefile.getSelectedIndex();
		}
		else if(cmd.equals("comboDate1")){ 
			JComboBox<String> choiceDate1 = (JComboBox<String>)e.getSource();
			columnDate1 = choiceDate1.getSelectedIndex();
		}
		else if(cmd.equals("comboDate2")){ 
			JComboBox<String> choiceDate2 = (JComboBox<String>)e.getSource();
			columnDate2 = choiceDate2.getSelectedIndex();
		}
		else if(cmd.equals("comboFormat1")){ 
			JComboBox<String> choiceFormat1 = (JComboBox<String>)e.getSource();
			dateFormat1 = (String) choiceFormat1.getSelectedItem();
		}
		else if(cmd.equals("comboFormat2")){ 
			JComboBox<String> choiceFormat2 = (JComboBox<String>)e.getSource();
			dateFormat2 = (String) choiceFormat2.getSelectedItem();
		}
	}
}
