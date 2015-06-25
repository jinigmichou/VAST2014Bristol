package ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SpringLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;

import core.Operator;
import core.Writer;

import javax.swing.JTextField;
import javax.swing.JTextArea;

public class MergingView extends JPanel implements ActionListener {
	private MainView frame;
	private ArrayList<String[]> file1;
	private ArrayList<String[]> file2;
	private int columnFile1;
	private int columnFile2;
	private JTextField textFieldFilePath;
	private JTextArea textArea;
	/**
	 * Create the panel.
	 */
	public MergingView(MainView frame, ArrayList<String[]> file1,  ArrayList<String[]> file2) {
		this.frame = frame;
		this.file1 = file1;
		this.file2 = file2;
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		setBackground(Color.LIGHT_GRAY);
		//this.setSize(frame.getSize());
		setSize(640, 480);

		JComboBox comboBox1 = new JComboBox(this.file1.get(0));
		springLayout.putConstraint(SpringLayout.NORTH, comboBox1, 32, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, comboBox1, 64, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, comboBox1, 184, SpringLayout.WEST, this);
		comboBox1.addActionListener(this);
		comboBox1.setActionCommand("combo1");
		add(comboBox1);

		JComboBox comboBox2 = new JComboBox(this.file2.get(0));
		springLayout.putConstraint(SpringLayout.NORTH, comboBox2, 32, SpringLayout.NORTH, this);
		comboBox2.addActionListener(this);
		comboBox2.setActionCommand("combo2");
		add(comboBox2);

		JLabel lblJoin = new JLabel("Join");
		springLayout.putConstraint(SpringLayout.NORTH, lblJoin, 36, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblJoin, 21, SpringLayout.EAST, comboBox1);
		springLayout.putConstraint(SpringLayout.WEST, comboBox2, 20, SpringLayout.EAST, lblJoin);
		springLayout.putConstraint(SpringLayout.EAST, comboBox2, 140, SpringLayout.EAST, lblJoin);
		add(lblJoin);

		JLabel lblFile = new JLabel("File 1");
		springLayout.putConstraint(SpringLayout.WEST, lblFile, 98, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, lblFile, -6, SpringLayout.NORTH, comboBox1);
		add(lblFile);

		JLabel lblFile_1 = new JLabel("File 2");
		springLayout.putConstraint(SpringLayout.WEST, lblFile_1, 134, SpringLayout.EAST, lblFile);
		springLayout.putConstraint(SpringLayout.SOUTH, lblFile_1, -6, SpringLayout.NORTH, comboBox2);
		add(lblFile_1);

		JButton btnValid = new JButton("Valid");
		springLayout.putConstraint(SpringLayout.WEST, btnValid, 163, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, btnValid, 238, SpringLayout.WEST, this);
		btnValid.addActionListener(this);
		btnValid.setActionCommand("Valid");
		add(btnValid);

		JButton btnCancel = new JButton("back");
		springLayout.putConstraint(SpringLayout.WEST, btnCancel, 0, SpringLayout.WEST, comboBox1);
		btnCancel.addActionListener(this);
		btnCancel.setActionCommand("back");
		add(btnCancel);

		textFieldFilePath = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, btnValid, 29, SpringLayout.SOUTH, textFieldFilePath);
		springLayout.putConstraint(SpringLayout.NORTH, textFieldFilePath, 19, SpringLayout.SOUTH, comboBox1);
		springLayout.putConstraint(SpringLayout.EAST, textFieldFilePath, -7, SpringLayout.EAST, comboBox2);
		add(textFieldFilePath);
		textFieldFilePath.setColumns(10);

		JLabel lblFileName = new JLabel("File name:");
		springLayout.putConstraint(SpringLayout.NORTH, btnCancel, 35, SpringLayout.SOUTH, lblFileName);
		springLayout.putConstraint(SpringLayout.WEST, textFieldFilePath, 24, SpringLayout.EAST, lblFileName);
		springLayout.putConstraint(SpringLayout.NORTH, lblFileName, 25, SpringLayout.SOUTH, comboBox1);
		springLayout.putConstraint(SpringLayout.EAST, lblFileName, 0, SpringLayout.EAST, btnCancel);
		add(lblFileName);

		textArea = new JTextArea();
		textArea.setEditable(false);
		JScrollPane scrollpane = new JScrollPane(textArea);
		springLayout.putConstraint(SpringLayout.NORTH, scrollpane, 21, SpringLayout.SOUTH, btnValid);
		springLayout.putConstraint(SpringLayout.WEST, scrollpane, 69, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, scrollpane, 126, SpringLayout.SOUTH, btnValid);
		springLayout.putConstraint(SpringLayout.EAST, scrollpane, 369, SpringLayout.WEST, this);
		add(scrollpane);

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String cmd = e.getActionCommand();
		if(cmd.equals("Valid")){ 
			if (textFieldFilePath.getText().equals("")){

				textArea.append("Please, fill file name.  \n");
			}
			else{

				ArrayList<String[]> result = Operator.twoCsvFilesMerging(file1, columnFile1, file2, columnFile2);
				try {
					Writer.writeCsv(result,"CsvData/"+textFieldFilePath.getText()+".csv" );
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		else if (cmd.equals("combo1")){
			JComboBox<String> choice1 = (JComboBox<String>)e.getSource();
			columnFile1 = choice1.getSelectedIndex();
		}
		else if (cmd.equals("combo2")){
			JComboBox<String> choice2 = (JComboBox<String>)e.getSource();
			columnFile2 =  choice2.getSelectedIndex();
		}
		else if (cmd.equals("back")){
			frame.changePanel(new HomeView(frame));
		}
	}
}
