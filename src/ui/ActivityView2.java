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

	private JTextField textFieldFilePath;
	private JTextArea textArea;

	public ActivityView2(MainView frame, ArrayList<String[]> file1,  ArrayList<String[]> file2) {

		this.frame = frame;
		this.file1 = file1;
		this.file2 = file2;

		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		setBackground(Color.LIGHT_GRAY);
		//this.setSize(frame.getSize());
		setSize(640, 480);

		JComboBox comboBox1 = new JComboBox(this.file1.get(0));
		springLayout.putConstraint(SpringLayout.EAST, comboBox1, -204, SpringLayout.EAST, this);
		comboBox1.addActionListener(this);
		comboBox1.setActionCommand("combo");
		add(comboBox1);

		JComboBox comboBoxDate1 = new JComboBox(this.file1.get(0));
		springLayout.putConstraint(SpringLayout.NORTH, comboBoxDate1, 24, SpringLayout.SOUTH, comboBox1);
		springLayout.putConstraint(SpringLayout.EAST, comboBoxDate1, 0, SpringLayout.EAST, comboBox1);
		springLayout.putConstraint(SpringLayout.WEST, comboBoxDate1, 262, SpringLayout.WEST, this);
		comboBoxDate1.addActionListener(this);
		comboBoxDate1.setActionCommand("comboDate1");
		add(comboBoxDate1);

		JComboBox comboBoxDate2 = new JComboBox(this.file2.get(0));
		springLayout.putConstraint(SpringLayout.NORTH, comboBoxDate2, 20, SpringLayout.SOUTH, comboBoxDate1);
		springLayout.putConstraint(SpringLayout.WEST, comboBoxDate2, 264, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, comboBoxDate2, 0, SpringLayout.EAST, comboBox1);
		comboBoxDate2.addActionListener(this);
		comboBoxDate2.setActionCommand("comboDate2");
		add(comboBoxDate2);


		JLabel lblFile = new JLabel("Choose argument to link:");
		springLayout.putConstraint(SpringLayout.NORTH, comboBox1, -4, SpringLayout.NORTH, lblFile);
		springLayout.putConstraint(SpringLayout.WEST, comboBox1, 4, SpringLayout.EAST, lblFile);
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
		springLayout.putConstraint(SpringLayout.NORTH, lblColumnDatefile, 0, SpringLayout.NORTH, comboBoxDate1);
		springLayout.putConstraint(SpringLayout.WEST, lblColumnDatefile, 0, SpringLayout.WEST, lblFile);
		add(lblColumnDatefile);

		JLabel lblColumnDatefile_1 = new JLabel("Column Date (File 2): ");
		springLayout.putConstraint(SpringLayout.NORTH, lblColumnDatefile_1, 0, SpringLayout.NORTH, comboBoxDate2);
		springLayout.putConstraint(SpringLayout.EAST, lblColumnDatefile_1, 0, SpringLayout.EAST, lblColumnDatefile);
		add(lblColumnDatefile_1);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String cmd = e.getActionCommand();
		if(cmd.equals("Valid")){ 
			ArrayList<String[]> finalResult = new ArrayList<String[]>();
			Operator.dateStringtoTimestamp(file1, columnDate1, "yyyy-MM-dd'T'HH:mm");
			Operator.dateStringtoTimestamp(file2, columnDate2, "yyyy-MM-dd'T'HH:mm");

			for (int k = 0; k<file1.get(1).length;k++){
				textArea.append(file1.get(0)[k]+" : "+file1.get(1)[k]+" ; ");
			}
			textArea.append("\n");

			for (int i = 1; i< file1.size()-1; i++){

				ArrayList<String[]> resultStamp = new ArrayList<String[]>();




				resultStamp = Operator.selectValuesBetweenTwoTimestamps(file2, columnDate2, file1.get(i)[columnDate1], file1.get(i+1)[columnDate1]);

				for (int j = 0 ; j<resultStamp.size();j++){
					System.out.println("IP       "+file1.get(1)[columnFile]);
					if (Operator.containInArray(resultStamp.get(j), file1.get(1)[columnFile])){
						for (int k = 0; k<resultStamp.get(j).length;k++){
							textArea.append(file2.get(0)[k]+" : "+resultStamp.get(j)[k]+" ; ");
						}
						textArea.append("\n");

					}
				}

				for (int k = 0; k<file1.get(i+1).length;k++){
					textArea.append(file1.get(0)[k]+" : "+file1.get(i+1)[k]+" ; ");
				}
				textArea.append("\n");
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
	}
}
