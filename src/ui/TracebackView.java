package ui;

import java.util.ArrayList;
import java.util.logging.Level;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.UIManager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.filechooser.FileFilter;
import javax.swing.SpringLayout;

public class TracebackView extends JPanel implements ActionListener{


	private MainView frame;
	private JTextField textFieldFilePath;

	private ArrayList<String[]> myFile;
	private JTextArea textArea;
	private JButton btnReturn;

	public TracebackView(MainView frame) {

		this.frame = frame;
		setBackground(Color.LIGHT_GRAY);

		textArea = new JTextArea();

		SpringLayout springLayout = new SpringLayout();

		JScrollPane scrollPane = new JScrollPane(textArea);
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 158, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, scrollPane, 13, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, -10, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, scrollPane, 643, SpringLayout.WEST, this);
		add(scrollPane);



		textFieldFilePath = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textFieldFilePath, 72, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, textFieldFilePath, 0, SpringLayout.WEST, scrollPane);
		springLayout.putConstraint(SpringLayout.EAST, textFieldFilePath, -479, SpringLayout.EAST, this);
		add(textFieldFilePath);
		textFieldFilePath.setColumns(10);

		JButton btnLog = new JButton("Display Log");
		springLayout.putConstraint(SpringLayout.NORTH, btnLog, 112, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, btnLog, 10, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, btnLog, -17, SpringLayout.NORTH, scrollPane);
		btnLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				FileReader flux= null;
				BufferedReader input= null;
				String str;

				try{ 
					flux= new FileReader (textFieldFilePath.getText());

					input= new BufferedReader( flux);

					int cpt = 1; 

					while((str=input.readLine())!=null)
					{
						String[] warning = str.split("WARNING: ");
						if(warning.length > 1 ){
							textArea.append("Step " + cpt + ": " + warning[1]);
							textArea.append("\n");
							cpt += 1;
						}

					}

					MainView.logger.log(Level.INFO, "Displaying content of the file : " + flux); 
				}catch (IOException e1)
				{
					System.out.println("It is impossible to open the file required : " + e1.toString()); 
				}
				finally
				{
					try {
						flux.close();
					} catch (IOException e1) {
						System.out.println("It is impossible to close the file");
						e1.printStackTrace();
					}
				}				
			}
		});
		
		btnLog.setActionCommand("Display Log");
		add(btnLog);

		JButton button = new JButton("Browse");
		springLayout.putConstraint(SpringLayout.NORTH, button, 1, SpringLayout.NORTH, textFieldFilePath);
		springLayout.putConstraint(SpringLayout.WEST, button, 11, SpringLayout.EAST, textFieldFilePath);
		springLayout.putConstraint(SpringLayout.EAST, button, -363, SpringLayout.EAST, this);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cmd = e.getActionCommand();
				if(cmd.equals("Browse")){ 
					textFieldFilePath.setText(this.selectFile());
					String get;
					get = textFieldFilePath.getText();
					MainView.logger.log(Level.INFO, "Click on the button Browse"); 
				}
			}

			private String selectFile() {
				String file= new String();

				File currentDirectory = null;
				try {

					currentDirectory = new File("./src/logFiles").getCanonicalFile();

				} catch(IOException e) {}

				JFileChooser dialog = new JFileChooser(currentDirectory);

				dialog.showOpenDialog(null);

				file= dialog.getSelectedFile().toString();
				return file;
			}
		});
		button.setActionCommand("Browse");
		add(button);


		JButton btnTraceback = new JButton("Traceback");
		springLayout.putConstraint(SpringLayout.WEST, btnTraceback, 0, SpringLayout.WEST, btnLog);
		springLayout.putConstraint(SpringLayout.SOUTH, btnTraceback, -6, SpringLayout.NORTH, textFieldFilePath);
		springLayout.putConstraint(SpringLayout.EAST, btnTraceback, 204, SpringLayout.WEST, this);
		btnTraceback.addActionListener(this);
		btnTraceback.setActionCommand("Traceback");
		setLayout(springLayout);

		add(btnTraceback);

		btnReturn = new JButton("Return");
		springLayout.putConstraint(SpringLayout.NORTH, btnReturn, 10, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, btnReturn, -25, SpringLayout.EAST, this);
		btnReturn.addActionListener(this);
		btnReturn.setActionCommand("Return");
		add(btnReturn);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String cmd = e.getActionCommand();
		if(cmd.equals("Return")){
			frame.changePanel(new HomeView(frame));
		}
		else if(cmd.equals("Traceback")){
			frame.applyLookAndFeel();
			frame.chooseFile();
		}
	}
}
