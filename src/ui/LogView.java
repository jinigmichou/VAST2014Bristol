package ui;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextArea;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.SpringLayout;

public class LogView extends JPanel implements ActionListener {

	private MainView frame;
	private JTextField textFieldFilePath;

	private ArrayList<String[]> myFile;
	private JTextArea textArea;

	public LogView(MainView frame) {
		this.frame = frame;
		setBackground(Color.LIGHT_GRAY);
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);

		textArea = new JTextArea();

		JScrollPane scrollPane = new JScrollPane(textArea);
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, 542, SpringLayout.NORTH, this);
		add(scrollPane);


		JButton btnLog = new JButton("Display Log");
		springLayout.putConstraint(SpringLayout.WEST, scrollPane, 0, SpringLayout.WEST, btnLog);
		springLayout.putConstraint(SpringLayout.WEST, btnLog, 23, SpringLayout.WEST, this);
		btnLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				FileReader flux= null;
				BufferedReader input= null;
				String str;

				try{ 
					flux= new FileReader (textFieldFilePath.getText());

					input= new BufferedReader( flux);
					while((str=input.readLine())!=null)
					{
						textArea.append(str);
						textArea.append("\n"); 
					}
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

		textFieldFilePath = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, btnLog, 13, SpringLayout.SOUTH, textFieldFilePath);
		springLayout.putConstraint(SpringLayout.NORTH, textFieldFilePath, 61, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, textFieldFilePath, 23, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, textFieldFilePath, 203, SpringLayout.WEST, this);
		add(textFieldFilePath);
		textFieldFilePath.setColumns(10);

		JButton button = new JButton("Browse");
		springLayout.putConstraint(SpringLayout.NORTH, button, 1, SpringLayout.NORTH, textFieldFilePath);
		springLayout.putConstraint(SpringLayout.WEST, button, 13, SpringLayout.EAST, textFieldFilePath);
		springLayout.putConstraint(SpringLayout.EAST, button, -404, SpringLayout.EAST, this);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cmd = e.getActionCommand();
				if(cmd.equals("Browse")){ 
					textFieldFilePath.setText(this.selectFile());
					String get;
					get = textFieldFilePath.getText();
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

		JButton CancelLog = new JButton("Erase Content");
		springLayout.putConstraint(SpringLayout.SOUTH, btnLog, -6, SpringLayout.NORTH, CancelLog);
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 11, SpringLayout.SOUTH, CancelLog);
		springLayout.putConstraint(SpringLayout.EAST, btnLog, 0, SpringLayout.EAST, CancelLog);
		springLayout.putConstraint(SpringLayout.WEST, CancelLog, 23, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.NORTH, CancelLog, 48, SpringLayout.SOUTH, textFieldFilePath);
		CancelLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				textArea.setText("");
			}
		});
		CancelLog.setActionCommand("Erase content");
		add(CancelLog);

		JLabel lblNewLabel = new JLabel("File Path : ");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel, 33, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel, 62, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, lblNewLabel, 48, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel, 149, SpringLayout.WEST, this);
		add(lblNewLabel);

		JButton btnReturn= new JButton("Return");
		springLayout.putConstraint(SpringLayout.NORTH, btnReturn, 27, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, btnReturn, 487, SpringLayout.EAST, lblNewLabel);
		springLayout.putConstraint(SpringLayout.SOUTH, btnReturn, -121, SpringLayout.NORTH, scrollPane);
		springLayout.putConstraint(SpringLayout.EAST, btnReturn, -22, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.EAST, scrollPane, 0, SpringLayout.EAST, btnReturn);
		btnReturn.addActionListener(this);
		btnReturn.setActionCommand("Return");
		add(btnReturn);

		JButton btnDeleteFiles = new JButton("Delete Files");
		springLayout.putConstraint(SpringLayout.NORTH, btnDeleteFiles, 137, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, CancelLog, -26, SpringLayout.WEST, btnDeleteFiles);
		springLayout.putConstraint(SpringLayout.WEST, btnDeleteFiles, 216, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, btnDeleteFiles, 0, SpringLayout.EAST, button);
		btnDeleteFiles.addActionListener(this);
		btnDeleteFiles.setActionCommand("delete");
		add(btnDeleteFiles);



	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String cmd = e.getActionCommand();
		if(cmd.equals("delete")){
			File MyFile = new File(textFieldFilePath.getText());

			String name = JOptionPane.showInputDialog(frame,
					"Do you want to delete this file?", textFieldFilePath.getText());
			MyFile.delete();
			textFieldFilePath.setText("");



			System.out.println("Delete Files");

		}
		else if(cmd.equals("Return")){
			frame.changePanel(new HomeView(frame));

		}

	}
}
