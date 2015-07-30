package ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

import core.Operator;
import core.Reader;

import javax.swing.JTextArea;

/**
 * This view concerns "execute your code" button
 * @author jacquez
 *
 */
@SuppressWarnings("serial")
public class ExecuteUserCodeView extends JPanel implements ActionListener{

	private String NumberOfInputFiles;
	private MainView frame;
	private JTextField textFieldFile1;
	private JTextField textFieldFile2;
	private JTextField textFieldFile3;
	private JTextField textFieldFile4;
	private JTextArea textArea;

	public ExecuteUserCodeView(MainView frame) {

		this.frame = frame;
		setLayout(null);
		setBackground(Color.LIGHT_GRAY);
		setSize(640, 480);
		NumberOfInputFiles = "1";
		initialize();
	}

	public void initialize(){


		JLabel lblNewLabel = new JLabel("Number of input files: ");
		lblNewLabel.setBounds(16, 22, 151, 35);
		add(lblNewLabel);

		String[] inputfileNumber = {"1","2","3","4"};
		JComboBox<String> comboBoxInputFiles = new JComboBox<String>(inputfileNumber);
		comboBoxInputFiles.setBounds(170, 30, 74, 20);
		comboBoxInputFiles.addActionListener(this);
		comboBoxInputFiles.setActionCommand("comboInput");
		add(comboBoxInputFiles);

		JButton btnValid = new JButton("Valid");
		btnValid.setBounds(170, 74, 74, 29);
		btnValid.addActionListener(this);
		btnValid.setActionCommand("Valid");
		add(btnValid);

		JButton btnBack = new JButton("Back");
		btnBack.setBounds(92, 74, 75, 29);
		btnBack.addActionListener(this);
		btnBack.setActionCommand("Back");
		add(btnBack);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String cmd = e.getActionCommand();
		if(cmd.equals("Valid")){ 
			this.removeAll();
			this.initialize();

			textArea = new JTextArea();
			JScrollPane scrollPane = new JScrollPane(textArea);
			scrollPane.setBounds(16, 317, 314, 131);
			add(scrollPane);

			if (NumberOfInputFiles.equals("1")){

				JLabel label = new JLabel("File 1: ");
				label.setBounds(26, 112, 52, 20);
				add(label);

				textFieldFile1 = new JTextField();
				textFieldFile1.setBounds(64, 115, 180, 17);
				this.add(textFieldFile1);
				textFieldFile1.setColumns(10);

				JButton btnValidInputFiles = new JButton("Valid Input files");
				btnValidInputFiles.setBounds(88, 144, 156, 23);
				btnValidInputFiles.addActionListener(this);
				btnValidInputFiles.setActionCommand("ValidWithOneInput");
				add(btnValidInputFiles);

				JButton btnBrowse1 = new JButton("Browse");
				btnBrowse1.setBounds(256, 109, 117, 29);
				btnBrowse1.addActionListener(this);
				btnBrowse1.setActionCommand("Browse1");
				add(btnBrowse1);

				this.revalidate();
				this.repaint();	
				
				NumberOfInputFiles = "1";
			}

			else if (NumberOfInputFiles.equals("2")){

				JLabel label = new JLabel("File 1: ");
				label.setBounds(26, 112, 52, 20);
				add(label);

				textFieldFile1 = new JTextField();
				textFieldFile1.setBounds(64, 115, 180, 17);
				this.add(textFieldFile1);
				textFieldFile1.setColumns(10);

				JLabel lblFile = new JLabel("File 2: ");
				lblFile.setBounds(26, 149, 52, 20);
				this.add(lblFile);

				textFieldFile2 = new JTextField();
				textFieldFile2.setColumns(10);
				textFieldFile2.setBounds(64, 150, 180, 17);
				add(textFieldFile2);

				JButton btnValidInputFiles = new JButton("Valid Input files");
				btnValidInputFiles.setBounds(92, 185, 156, 23);
				btnValidInputFiles.addActionListener(this);
				btnValidInputFiles.setActionCommand("ValidWithTwoInputs");
				add(btnValidInputFiles);

				JButton btnBrowse1 = new JButton("Browse");
				btnBrowse1.setBounds(256, 109, 117, 29);
				btnBrowse1.addActionListener(this);
				btnBrowse1.setActionCommand("Browse1");
				add(btnBrowse1);

				JButton btnBrowse2 = new JButton("Browse");
				btnBrowse2.setBounds(256, 146, 117, 29);
				btnBrowse2.addActionListener(this);
				btnBrowse2.setActionCommand("Browse2");
				add(btnBrowse2);

				this.revalidate();
				this.repaint();
				
				NumberOfInputFiles = "1";

			}
			else if (NumberOfInputFiles.equals("3")){

				JLabel label = new JLabel("File 1: ");
				label.setBounds(26, 112, 52, 20);
				add(label);

				textFieldFile1 = new JTextField();
				textFieldFile1.setBounds(64, 115, 180, 17);
				this.add(textFieldFile1);
				textFieldFile1.setColumns(10);

				JLabel lblFile = new JLabel("File 2: ");
				lblFile.setBounds(26, 149, 52, 20);
				this.add(lblFile);

				textFieldFile2 = new JTextField();
				textFieldFile2.setColumns(10);
				textFieldFile2.setBounds(64, 150, 180, 17);
				add(textFieldFile2);

				JLabel lblFile_1 = new JLabel("File 3: ");
				lblFile_1.setBounds(26, 183, 52, 20);
				add(lblFile_1);

				textFieldFile3 = new JTextField();
				textFieldFile3.setColumns(10);
				textFieldFile3.setBounds(64, 186, 180, 17);
				add(textFieldFile3);

				JButton btnValidInputFiles = new JButton("Valid Input files");
				btnValidInputFiles.setBounds(88, 214, 156, 23);
				btnValidInputFiles.addActionListener(this);
				btnValidInputFiles.setActionCommand("ValidWithThreeInputs");
				add(btnValidInputFiles);

				JButton btnBrowse1 = new JButton("Browse");
				btnBrowse1.setBounds(256, 109, 117, 29);
				btnBrowse1.addActionListener(this);
				btnBrowse1.setActionCommand("Browse1");
				add(btnBrowse1);

				JButton btnBrowse2 = new JButton("Browse");
				btnBrowse2.setBounds(256, 146, 117, 29);
				btnBrowse2.addActionListener(this);
				btnBrowse2.setActionCommand("Browse2");
				add(btnBrowse2);

				JButton btnBrowse3 = new JButton("Browse");
				btnBrowse3.setBounds(256, 180, 117, 29);
				btnBrowse3.addActionListener(this);
				btnBrowse3.setActionCommand("Browse3");
				add(btnBrowse3);

				this.revalidate();
				this.repaint();
				
				NumberOfInputFiles = "1";

			}

			else if (NumberOfInputFiles.equals("4")){

				JLabel label = new JLabel("File 1: ");
				label.setBounds(26, 112, 52, 20);
				add(label);

				textFieldFile1 = new JTextField();
				textFieldFile1.setBounds(64, 115, 180, 17);
				this.add(textFieldFile1);
				textFieldFile1.setColumns(10);

				JLabel lblFile = new JLabel("File 2: ");
				lblFile.setBounds(26, 149, 52, 20);
				this.add(lblFile);

				textFieldFile2 = new JTextField();
				textFieldFile2.setColumns(10);
				textFieldFile2.setBounds(64, 150, 180, 17);
				add(textFieldFile2);

				JLabel lblFile_1 = new JLabel("File 3: ");
				lblFile_1.setBounds(26, 183, 52, 20);
				add(lblFile_1);

				textFieldFile3 = new JTextField();
				textFieldFile3.setColumns(10);
				textFieldFile3.setBounds(64, 186, 180, 17);
				add(textFieldFile3);

				JLabel lblFile_2 = new JLabel("File 4: ");
				lblFile_2.setBounds(26, 217, 52, 20);
				add(lblFile_2);

				textFieldFile4 = new JTextField();
				textFieldFile4.setColumns(10);
				textFieldFile4.setBounds(64, 220, 180, 17);
				add(textFieldFile4);

				JButton btnValidInputFiles = new JButton("Valid Input files");
				btnValidInputFiles.setBounds(88, 246, 156, 23);
				btnValidInputFiles.addActionListener(this);
				btnValidInputFiles.setActionCommand("ValidWithFourInputs");
				add(btnValidInputFiles);

				JButton btnBrowse1 = new JButton("Browse");
				btnBrowse1.setBounds(256, 109, 117, 29);
				btnBrowse1.addActionListener(this);
				btnBrowse1.setActionCommand("Browse1");
				add(btnBrowse1);

				JButton btnBrowse2 = new JButton("Browse");
				btnBrowse2.setBounds(256, 146, 117, 29);
				btnBrowse2.addActionListener(this);
				btnBrowse2.setActionCommand("Browse2");
				add(btnBrowse2);

				JButton btnBrowse3 = new JButton("Browse");
				btnBrowse3.setBounds(256, 180, 117, 29);
				btnBrowse3.addActionListener(this);
				btnBrowse3.setActionCommand("Browse3");
				add(btnBrowse3);

				JButton btnBrowse4 = new JButton("Browse");
				btnBrowse4.setBounds(256, 214, 117, 29);
				btnBrowse4.addActionListener(this);
				btnBrowse4.setActionCommand("Browse4");
				add(btnBrowse4);

				this.revalidate();
				this.repaint();
				
				NumberOfInputFiles = "1";
			}
		}

		else if (cmd.equals("comboInput")){
			@SuppressWarnings("unchecked")
			JComboBox<String> choice = (JComboBox<String>)e.getSource();
			NumberOfInputFiles = (String) choice.getSelectedItem();
		}

		else if(cmd.equals("Browse1")){ 
			textFieldFile1.setText(Operator.selectFile());
		}

		else if(cmd.equals("Browse2")){ 
			textFieldFile2.setText(Operator.selectFile());
		}

		else if(cmd.equals("Browse3")){ 
			textFieldFile3.setText(Operator.selectFile());
		}

		else if(cmd.equals("Browse4")){ 
			textFieldFile4.setText(Operator.selectFile());
		}

		else if (cmd.equals("ValidWithOneInput")){

			if (textFieldFile1.getText().equals("")){
				textArea.append("Please, select a file from file browser 1 \n");
			}

			else {
				ArrayList<String[]> myFile1 = new ArrayList<String[]>();
				String filePath1= textFieldFile1.getText();
				Reader myreader1 = new Reader(filePath1);

				try {
					myFile1 = myreader1.readCsv(myreader1.getMyFilePath());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				frame.changePanel(new ExecuteUserCodeView1(frame, myFile1));
			}
		}

		else if (cmd.equals("ValidWithTwoInputs")){

			if (textFieldFile1.getText().equals("")){
				textArea.append("Please, select a file from file browser 1 \n");
			}

			else if(textFieldFile2.getText().equals("")){
				textArea.append("Please, select a file from file browser 2 \n");
			}

			else {
				ArrayList<String[]> myFile1 = new ArrayList<String[]>();
				ArrayList<String[]> myFile2 = new ArrayList<String[]>();

				String filePath1= textFieldFile1.getText();
				String filePath2= textFieldFile2.getText();

				Reader myreader1 = new Reader(filePath1);
				Reader myreader2 = new Reader(filePath2);

				try {
					myFile1 = myreader1.readCsv(myreader1.getMyFilePath());
					myFile2 = myreader2.readCsv(myreader2.getMyFilePath());

				} catch (Exception e1) {
					e1.printStackTrace();
				}

				frame.changePanel(new ExecuteUserCodeView1(frame, myFile1, myFile2));
			}
		}

		else if (cmd.equals("ValidWithThreeInputs")){

			if (textFieldFile1.getText().equals("")){
				textArea.append("Please, select a file from file browser 1 \n");
			}
			else if(textFieldFile2.getText().equals("")){
				textArea.append("Please, select a file from file browser 2 \n");
			}
			else if(textFieldFile3.getText().equals("")){
				textArea.append("Please, select a file from file browser 3 \n");
			}

			else {
				ArrayList<String[]> myFile1 = new ArrayList<String[]>();
				ArrayList<String[]> myFile2 = new ArrayList<String[]>();
				ArrayList<String[]> myFile3 = new ArrayList<String[]>();

				String filePath1= textFieldFile1.getText();
				String filePath2= textFieldFile2.getText();
				String filePath3= textFieldFile3.getText();

				Reader myreader1 = new Reader(filePath1);
				Reader myreader2 = new Reader(filePath2);
				Reader myreader3 = new Reader(filePath3);

				try {
					myFile1 = myreader1.readCsv(myreader1.getMyFilePath());
					myFile2 = myreader2.readCsv(myreader2.getMyFilePath());
					myFile3 = myreader3.readCsv(myreader3.getMyFilePath());

				} catch (Exception e1) {
					e1.printStackTrace();
				}
				frame.changePanel(new ExecuteUserCodeView1(frame, myFile1, myFile2, myFile3));
			}
		}

		else if (cmd.equals("ValidWithFourInputs")){

			if (textFieldFile1.getText().equals("")){
				textArea.append("Please, select a file from file browser 1 \n");
			}
			else if(textFieldFile2.getText().equals("")){
				textArea.append("Please, select a file from file browser 2 \n");
			}
			else if(textFieldFile3.getText().equals("")){
				textArea.append("Please, select a file from file browser 3 \n");
			}
			else if(textFieldFile4.getText().equals("")){
				textArea.append("Please, select a file from file browser 4 \n");
			}

			else {
				ArrayList<String[]> myFile1 = new ArrayList<String[]>();
				ArrayList<String[]> myFile2 = new ArrayList<String[]>();
				ArrayList<String[]> myFile3 = new ArrayList<String[]>();
				ArrayList<String[]> myFile4 = new ArrayList<String[]>();

				String filePath1= textFieldFile1.getText();
				String filePath2= textFieldFile2.getText();
				String filePath3= textFieldFile3.getText();
				String filePath4= textFieldFile4.getText();

				Reader myreader1 = new Reader(filePath1);
				Reader myreader2 = new Reader(filePath2);
				Reader myreader3 = new Reader(filePath3);
				Reader myreader4 = new Reader(filePath4);

				try {
					myFile1 = myreader1.readCsv(myreader1.getMyFilePath());
					myFile2 = myreader2.readCsv(myreader2.getMyFilePath());
					myFile3 = myreader3.readCsv(myreader3.getMyFilePath());
					myFile4 = myreader4.readCsv(myreader4.getMyFilePath());

				} catch (Exception e1) {
					e1.printStackTrace();
				}

				frame.changePanel(new ExecuteUserCodeView1(frame, myFile1, myFile2, myFile3, myFile4));
			}
		}

		else if (cmd.equals("Back")){
			frame.changePanel(new HomeView(frame));
		}
	}
}
