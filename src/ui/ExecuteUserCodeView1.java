package ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;

import core.TerminalOutput;
import core.Writer;

public class ExecuteUserCodeView1 extends JPanel implements ActionListener {

	private MainView frame;
	private ArrayList<String[]> file1;
	private ArrayList<String[]> file2;
	private ArrayList<String[]> file3;
	private ArrayList<String[]> file4;
	private JTextArea textArea;
	private JTextArea textAreaCode;
	private JTextField textFieldNameOfOutput;
	private JTextField textFieldExecutableName;
	/**
	 * Create the panel.
	 * @wbp.parser.constructor
	 */
	public ExecuteUserCodeView1(MainView frame, ArrayList<String[]> file1) {
		this.frame = frame;
		this.file1 = file1;

		initialize();
		textArea.append("File 1 : \n");
		for (int i = 0 ; i< file1.get(0).length; i++){
			textArea.append("column "+i+" : "+file1.get(0)[i]+"\n");
		}

	}

	public ExecuteUserCodeView1(MainView frame, ArrayList<String[]> file1, ArrayList<String[]> file2) {
		this.frame = frame;
		this.file1 = file1;
		this.file2 = file2;

		initialize();

		textArea.append("File 1 : \n");
		for (int i = 0 ; i< file1.get(0).length; i++){
			textArea.append("column "+i+" : "+file1.get(0)[i]+"\n");
		}


		textArea.append("File 2 : \n");
		for (int i = 0 ; i< file2.get(0).length; i++){
			textArea.append("column "+i+" : "+file2.get(0)[i]+"\n");
		}


	}

	public ExecuteUserCodeView1(MainView frame, ArrayList<String[]> file1, ArrayList<String[]> file2, ArrayList<String[]> file3) {
		this.frame = frame;
		this.file1 = file1;
		this.file2 = file2;
		this.file3 = file3;

		initialize();

		textArea.append("File 1 : \n");
		for (int i = 0 ; i< file1.get(0).length; i++){
			textArea.append("column "+i+" : "+file1.get(0)[i]+"\n");
		}

		textArea.append("File 2 : \n");
		for (int i = 0 ; i< file2.get(0).length; i++){
			textArea.append("column "+i+" : "+file2.get(0)[i]+"\n");
		}

		textArea.append("File 3 : \n");
		for (int i = 0 ; i< file3.get(0).length; i++){
			textArea.append("column "+i+" : "+file3.get(0)[i]+"\n");
		}

	}

	public ExecuteUserCodeView1(MainView frame, ArrayList<String[]> file1, ArrayList<String[]> file2, ArrayList<String[]> file3,ArrayList<String[]> file4) {
		this.frame = frame;
		this.file1 = file1;
		this.file2 = file2;
		this.file3 = file3;
		this.file4 = file4;

		initialize();


		textArea.append("File 1 : \n");
		for (int i = 0 ; i< file1.get(0).length; i++){
			textArea.append("column "+i+" : "+file1.get(0)[i]+"\n");
		}

		textArea.append("File 2 : \n");
		for (int i = 0 ; i< file2.get(0).length; i++){
			textArea.append("column "+i+" : "+file2.get(0)[i]+"\n");
		}

		textArea.append("File 3 : \n");
		for (int i = 0 ; i< file3.get(0).length; i++){
			textArea.append("column "+i+" : "+file3.get(0)[i]+"\n");
		}

		textArea.append("File 4 : \n");
		for (int i = 0 ; i< file4.get(0).length; i++){
			textArea.append("column "+i+" : "+file4.get(0)[i]+"\n");
		}



	}
	public void initialize(){
		SpringLayout springLayout = new SpringLayout();
		setSize(640, 480);
		setLayout(springLayout);
		setBackground(Color.LIGHT_GRAY);

		textArea = new JTextArea();
		textArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(textArea);
		springLayout.putConstraint(SpringLayout.WEST, scrollPane, 26, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, -57, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, scrollPane, 182, SpringLayout.WEST, this);
		add(scrollPane);

		JLabel lblFileColumns = new JLabel("File Columns:");
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 6, SpringLayout.SOUTH, lblFileColumns);
		springLayout.putConstraint(SpringLayout.WEST, lblFileColumns, 26, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.NORTH, lblFileColumns, 10, SpringLayout.NORTH, this);
		add(lblFileColumns);

		textFieldNameOfOutput = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textFieldNameOfOutput, 4, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.SOUTH, textFieldNameOfOutput, 32, SpringLayout.NORTH, this);
		add(textFieldNameOfOutput);
		textFieldNameOfOutput.setColumns(10);

		JLabel lblOutputFileName = new JLabel("Output File Name: ");
		springLayout.putConstraint(SpringLayout.WEST, textFieldNameOfOutput, 32, SpringLayout.EAST, lblOutputFileName);
		springLayout.putConstraint(SpringLayout.EAST, textFieldNameOfOutput, 215, SpringLayout.EAST, lblOutputFileName);
		springLayout.putConstraint(SpringLayout.NORTH, lblOutputFileName, 0, SpringLayout.NORTH, lblFileColumns);
		springLayout.putConstraint(SpringLayout.WEST, lblOutputFileName, 88, SpringLayout.EAST, lblFileColumns);
		add(lblOutputFileName);

		JLabel lblExecutableFileName = new JLabel("Executable File Name: ");
		springLayout.putConstraint(SpringLayout.NORTH, lblExecutableFileName, 15, SpringLayout.SOUTH, lblOutputFileName);
		springLayout.putConstraint(SpringLayout.WEST, lblExecutableFileName, 0, SpringLayout.WEST, lblOutputFileName);
		add(lblExecutableFileName);

		textFieldExecutableName = new JTextField();
		springLayout.putConstraint(SpringLayout.EAST, textFieldExecutableName, 191, SpringLayout.EAST, lblExecutableFileName);
		springLayout.putConstraint(SpringLayout.NORTH, textFieldExecutableName, 32, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, textFieldExecutableName, 8, SpringLayout.EAST, lblExecutableFileName);
		add(textFieldExecutableName);
		textFieldExecutableName.setColumns(10);

		JLabel lblYourCode = new JLabel("Your code: ");
		springLayout.putConstraint(SpringLayout.NORTH, lblYourCode, 10, SpringLayout.SOUTH, textFieldExecutableName);
		springLayout.putConstraint(SpringLayout.WEST, lblYourCode, 19, SpringLayout.EAST, scrollPane);
		add(lblYourCode);

		textAreaCode = new JTextArea();
		JScrollPane scrollPaneCode = new JScrollPane(textAreaCode);
		springLayout.putConstraint(SpringLayout.NORTH, scrollPaneCode, 6, SpringLayout.SOUTH, lblYourCode);
		springLayout.putConstraint(SpringLayout.WEST, scrollPaneCode, 18, SpringLayout.EAST, scrollPane);
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPaneCode, 0, SpringLayout.SOUTH, scrollPane);
		springLayout.putConstraint(SpringLayout.EAST, scrollPaneCode, -21, SpringLayout.EAST, this);
		add(scrollPaneCode);

		JButton btnExecuteCode = new JButton("Execute code");
		springLayout.putConstraint(SpringLayout.NORTH, btnExecuteCode, 6, SpringLayout.SOUTH, scrollPaneCode);
		springLayout.putConstraint(SpringLayout.EAST, btnExecuteCode, -23, SpringLayout.EAST, this);
		btnExecuteCode.addActionListener(this);
		btnExecuteCode.setActionCommand("Execute");
		add(btnExecuteCode);


	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String cmd = e.getActionCommand();
		if(cmd.equals("Execute")){ 
			
			String bodyMainClass = "public class Main {"
					+ "\n\n"
					+ "public static void main(String [ ] args){";
			try {
				Writer.writeFile(bodyMainClass+"\n"+
			textAreaCode.getText()+"\n}"
					+ "\n}", "Main");
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			Runtime runtime = Runtime.getRuntime();
			
			String Cmd = new String("javac Main.java");
			String Cmd1 = new String("jar cvmf MANIFEST.MF "
					+ textFieldExecutableName.getText()+".jar"
					+" Main.class");
			
			
			String Cmd2 = new String("java -jar "+textFieldExecutableName.getText()+".jar");
		
			try {
				Process process = runtime.exec(Cmd);
				try {
					process.waitFor();
					
					process = runtime.exec(Cmd1);
					process.waitFor();
					
					process = runtime.exec(Cmd2);
					process.waitFor();
					
				} catch (InterruptedException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				System.out.println("Resultat apres execution");
				TerminalOutput output= new TerminalOutput(process.getInputStream()); 
				output.run();
				/*
				System.out.println("erreur");
				TerminalOutput outputE= new TerminalOutput(process.getErrorStream()); 
				outputE.run();*/

			
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}


		}
	}
}
