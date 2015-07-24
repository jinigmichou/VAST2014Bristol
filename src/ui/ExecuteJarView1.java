package ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

import core.Reader;
import core.Operator;
import core.TerminalOutput;

import javax.swing.JTextArea;

/**
 * This view concerns "execute jar file" button
 * @author jacquez
 *
 */
public class ExecuteJarView1 extends JPanel implements ActionListener{

	/**
	 * The serialVersionUID is a universal version identifier for a Serializable class.
	 * Deserialization uses this number to ensure that a loaded class corresponds exactly
	 * to a serialized object.
	 */
	private static final long serialVersionUID = 1L;
	private String NumberOfOutputFiles;
	private MainView frame;
	private JTextField textFieldFileOutput1;
	private JTextField textFieldFileOutput2;
	private JTextField textFieldFileOutput3;
	private JTextField textFieldFileOutput4;
	private JTextArea textArea;
	private JTextField textFieldJarFile;
	private String InputsPath;

	public ExecuteJarView1(MainView frame, String InputsPath) {

		this.InputsPath = InputsPath;
		this.frame = frame;
		NumberOfOutputFiles = "1";

		setLayout(null);
		setBackground(Color.LIGHT_GRAY);
		setSize(640, 480);
		initialize();
	}

	public void initialize(){

		JLabel lblNewLabel = new JLabel("Number of output files: ");
		lblNewLabel.setBounds(16, 22, 151, 35);
		add(lblNewLabel);

		String[] outputfileNumber = {"1","2","3","4"};
		JComboBox<String> comboBoxOutputFiles = new JComboBox<String>(outputfileNumber);
		comboBoxOutputFiles.setBounds(170, 30, 74, 20);
		comboBoxOutputFiles.addActionListener(this);
		comboBoxOutputFiles.setActionCommand("comboOutput");
		add(comboBoxOutputFiles);

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

		JLabel lblSelectYourJar = new JLabel("Select your Jar file:");
		lblSelectYourJar.setBounds(275, 31, 122, 16);
		add(lblSelectYourJar);

		textFieldJarFile = new JTextField();
		textFieldJarFile.setBounds(275, 74, 182, 29);
		add(textFieldJarFile);
		textFieldJarFile.setColumns(10);

		JButton btnBrowse = new JButton("Browse");
		btnBrowse.setBounds(469, 74, 117, 29);
		btnBrowse.addActionListener(this);
		btnBrowse.setActionCommand("Browse");
		add(btnBrowse);
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

			if (NumberOfOutputFiles.equals("1")){

				JLabel label = new JLabel("File 1: ");
				label.setBounds(26, 112, 52, 20);
				add(label);

				textFieldFileOutput1 = new JTextField();
				textFieldFileOutput1.setBounds(64, 115, 180, 17);
				this.add(textFieldFileOutput1);
				textFieldFileOutput1.setColumns(10);

				JButton btnValidOutputFiles = new JButton("Valid output files");
				btnValidOutputFiles.setBounds(88, 144, 156, 23);
				btnValidOutputFiles.addActionListener(this);
				btnValidOutputFiles.setActionCommand("ValidWithOneOutput");
				add(btnValidOutputFiles);

				this.revalidate();
				this.repaint();	
				
				NumberOfOutputFiles = "1";
				
			}
			else if (NumberOfOutputFiles.equals("2")){

				JLabel label = new JLabel("File 1: ");
				label.setBounds(26, 112, 52, 20);
				add(label);

				textFieldFileOutput1 = new JTextField();
				textFieldFileOutput1.setBounds(64, 115, 180, 17);
				this.add(textFieldFileOutput1);
				textFieldFileOutput1.setColumns(10);

				JLabel lblFile = new JLabel("File 2: ");
				lblFile.setBounds(26, 149, 52, 20);
				this.add(lblFile);

				textFieldFileOutput2 = new JTextField();
				textFieldFileOutput2.setColumns(10);
				textFieldFileOutput2.setBounds(64, 150, 180, 17);
				add(textFieldFileOutput2);

				JButton btnValidOutputFiles = new JButton("Valid Output files");
				btnValidOutputFiles.setBounds(92, 185, 156, 23);
				btnValidOutputFiles.addActionListener(this);
				btnValidOutputFiles.setActionCommand("ValidWithTwoOutputs");
				add(btnValidOutputFiles);

				this.revalidate();
				this.repaint();
				
				NumberOfOutputFiles = "1";

			}
			else if (NumberOfOutputFiles.equals("3")){

				JLabel label = new JLabel("File 1: ");
				label.setBounds(26, 112, 52, 20);
				add(label);

				textFieldFileOutput1 = new JTextField();
				textFieldFileOutput1.setBounds(64, 115, 180, 17);
				this.add(textFieldFileOutput1);
				textFieldFileOutput1.setColumns(10);

				JLabel lblFile = new JLabel("File 2: ");
				lblFile.setBounds(26, 149, 52, 20);
				this.add(lblFile);

				textFieldFileOutput2 = new JTextField();
				textFieldFileOutput2.setColumns(10);
				textFieldFileOutput2.setBounds(64, 150, 180, 17);
				add(textFieldFileOutput2);

				JLabel lblFile_1 = new JLabel("File 3: ");
				lblFile_1.setBounds(26, 183, 52, 20);
				add(lblFile_1);

				textFieldFileOutput3 = new JTextField();
				textFieldFileOutput3.setColumns(10);
				textFieldFileOutput3.setBounds(64, 186, 180, 17);
				add(textFieldFileOutput3);

				JButton btnValidOutputFiles = new JButton("Valid Output files");
				btnValidOutputFiles.setBounds(88, 214, 156, 23);
				btnValidOutputFiles.addActionListener(this);
				btnValidOutputFiles.setActionCommand("ValidWithThreeOutputs");
				add(btnValidOutputFiles);

				this.revalidate();
				this.repaint();
				
				NumberOfOutputFiles = "1";

			}
			else if (NumberOfOutputFiles.equals("4")){

				JLabel label = new JLabel("File 1: ");
				label.setBounds(26, 112, 52, 20);
				add(label);

				textFieldFileOutput1 = new JTextField();
				textFieldFileOutput1.setBounds(64, 115, 180, 17);
				this.add(textFieldFileOutput1);
				textFieldFileOutput1.setColumns(10);

				JLabel lblFile = new JLabel("File 2: ");
				lblFile.setBounds(26, 149, 52, 20);
				this.add(lblFile);

				textFieldFileOutput2 = new JTextField();
				textFieldFileOutput2.setColumns(10);
				textFieldFileOutput2.setBounds(64, 150, 180, 17);
				add(textFieldFileOutput2);

				JLabel lblFile_1 = new JLabel("File 3: ");
				lblFile_1.setBounds(26, 183, 52, 20);
				add(lblFile_1);

				textFieldFileOutput3 = new JTextField();
				textFieldFileOutput3.setColumns(10);
				textFieldFileOutput3.setBounds(64, 186, 180, 17);
				add(textFieldFileOutput3);

				JLabel lblFile_2 = new JLabel("File 4: ");
				lblFile_2.setBounds(26, 217, 52, 20);
				add(lblFile_2);

				textFieldFileOutput4 = new JTextField();
				textFieldFileOutput4.setColumns(10);
				textFieldFileOutput4.setBounds(64, 220, 180, 17);
				add(textFieldFileOutput4);

				JButton btnValidOutputFiles = new JButton("Valid Output files");
				btnValidOutputFiles.setBounds(88, 246, 156, 23);
				btnValidOutputFiles.addActionListener(this);
				btnValidOutputFiles.setActionCommand("ValidWithFourOutputs");
				add(btnValidOutputFiles);

				this.revalidate();
				this.repaint();
				
				NumberOfOutputFiles = "1";
			}

		}

		else if (cmd.equals("comboOutput")){
			@SuppressWarnings("unchecked")
			JComboBox<String> choice = (JComboBox<String>) e.getSource();
			NumberOfOutputFiles = (String) choice.getSelectedItem();
		}

		else if (cmd.equals("ValidWithOneOutput")){

			if (textFieldFileOutput1.getText().equals("")){
				textArea.append("Please, select a file from file browser 1 \n");
			}
			
			else if( textFieldJarFile.getText().equals("")){
				textArea.append("Please, select a jar file \n");
			}

			else {

				String filePath1= textFieldFileOutput1.getText();
				String filesPath = filePath1;

				Runtime runtime = Runtime.getRuntime();
				try {
					Process process = runtime.exec(new String("java -jar "+textFieldJarFile.getText()+" "
							+InputsPath.toString()+" "
							+filesPath.toString()));
				

					ArrayList<String> result = new ArrayList<String>();
					ArrayList<String> resultE = new ArrayList<String>();

					TerminalOutput output= new TerminalOutput(process.getInputStream());
					TerminalOutput outputE= new TerminalOutput(process.getErrorStream()); 

					output.run(result);
					outputE.run(resultE);

					for (int i = 0 ; i < result.size(); i++){textArea.setText("results  : "+result.get(i));}
					for (int i = 0 ; i < resultE.size(); i++){textArea.setText("Errors  : "+resultE.get(i));}
					
					ArrayList<String[]> myFile = new ArrayList<String[]>();
					Reader myreader= new Reader(InputsPath.toString());
					try {
						myFile = myreader.readCsv(myreader.getMyFilePath());
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					System.out.println("taille 2  " + myFile.size());

				} catch (IOException e1) {
					e1.printStackTrace();
				}	
			}
		}

		else if (cmd.equals("ValidWithTwoOutputs")){

			if (textFieldFileOutput1.getText().equals("")){
				textArea.append("Please, select a file from file browser 1 \n");
			}
			else if(textFieldFileOutput2.getText().equals("")){
				textArea.append("Please, select a file from file browser 2 \n");
			}
			else if( textFieldJarFile.getText().equals("")){
				textArea.append("Please, select a jar file \n");
			}
			else {

				String filePath1= textFieldFileOutput1.getText();
				String filePath2= textFieldFileOutput2.getText();
				String filesPath = filePath1+" "+filePath2;

				Runtime runtime = Runtime.getRuntime();
				try {
					Process process = runtime.exec(new String("java -jar "+textFieldJarFile.getText()+" "
							+InputsPath.toString()+" "
							+filesPath.toString()));

					ArrayList<String> result = new ArrayList<String>();
					ArrayList<String> resultE = new ArrayList<String>();

					TerminalOutput output= new TerminalOutput(process.getInputStream());
					TerminalOutput outputE= new TerminalOutput(process.getErrorStream()); 

					output.run(result);
					outputE.run(resultE);

					for (int i = 0 ; i < result.size(); i++){textArea.setText(result.get(i));}
					for (int i = 0 ; i < resultE.size(); i++){textArea.setText(resultE.get(i));}

				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}

		else if (cmd.equals("ValidWithThreeOutputs")){

			if (textFieldFileOutput1.getText().equals("")){
				textArea.append("Please, select a file from file browser 1 \n");
			}
			else if(textFieldFileOutput2.getText().equals("")){
				textArea.append("Please, select a file from file browser 2 \n");
			}
			else if(textFieldFileOutput3.getText().equals("")){
				textArea.append("Please, select a file from file browser 3 \n");
			}
			else if( textFieldJarFile.getText().equals("")){
				textArea.append("Please, select a jar file \n");
			}
			else {

				String filePath1= textFieldFileOutput1.getText();
				String filePath2= textFieldFileOutput2.getText();
				String filePath3= textFieldFileOutput3.getText();
				String filesPath = filePath1+" "+filePath2+" "+filePath3;

				Runtime runtime = Runtime.getRuntime();
				try {
					Process process = runtime.exec(new String("java -jar "+textFieldJarFile.getText()+" "
							+InputsPath.toString()+" "
							+filesPath.toString()));

					ArrayList<String> result = new ArrayList<String>();
					ArrayList<String> resultE = new ArrayList<String>();

					TerminalOutput output= new TerminalOutput(process.getInputStream());
					TerminalOutput outputE= new TerminalOutput(process.getErrorStream()); 

					output.run(result);
					outputE.run(resultE);

					for (int i = 0 ; i < result.size(); i++){textArea.setText("results  : "+result.get(i));}
					for (int i = 0 ; i < resultE.size(); i++){textArea.setText("Errors  : "+resultE.get(i));}

				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}

		else if (cmd.equals("ValidWithFourOutputs")){

			if (textFieldFileOutput1.getText().equals("")){
				textArea.append("Please, select a file from file browser 1 \n");
			}
			else if(textFieldFileOutput2.getText().equals("")){
				textArea.append("Please, select a file from file browser 2 \n");
			}
			else if(textFieldFileOutput3.getText().equals("")){
				textArea.append("Please, select a file from file browser 3 \n");
			}
			else if(textFieldFileOutput4.getText().equals("")){
				textArea.append("Please, select a file from file browser 4 \n");
			}
			else if( textFieldJarFile.getText().equals("")){
				textArea.append("Please, select a jar file \n");
			}
			else {

				String filePath1= textFieldFileOutput1.getText();
				String filePath2= textFieldFileOutput2.getText();
				String filePath3= textFieldFileOutput3.getText();
				String filePath4= textFieldFileOutput4.getText();
				String filesPath = filePath1+" "+filePath2+" "+filePath3+" "+filePath4;

				Runtime runtime = Runtime.getRuntime();
				try {
					Process process = runtime.exec(new String("java -jar "+textFieldJarFile.getText()+" "
							+InputsPath.toString()+" "
							+filesPath.toString()));

					ArrayList<String> result = new ArrayList<String>();
					ArrayList<String> resultE = new ArrayList<String>();

					TerminalOutput output= new TerminalOutput(process.getInputStream());
					TerminalOutput outputE= new TerminalOutput(process.getErrorStream());

					output.run(result);
					outputE.run(resultE);

					for (int i = 0 ; i < result.size(); i++){textArea.setText("results  : "+result.get(i));}
					for (int i = 0 ; i < resultE.size(); i++){textArea.setText("Errors  : "+resultE.get(i));}


				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}

		else if (cmd.equals("Browse")){
			textFieldJarFile.setText(Operator.selectFile());
		}

		else if (cmd.equals("Back")){
			frame.changePanel(new ExecuteJarView(frame));
		}
	}
}

