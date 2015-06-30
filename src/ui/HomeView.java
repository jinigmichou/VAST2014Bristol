package ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.LineBorder;

import persist.WriterCSV;
import core.Operator;
import core.Reader;
import core.Writer;

import javax.swing.JTextArea;


public class HomeView extends JPanel implements ActionListener{

	private MainView frame;
	private JTextField textFieldFilePath;
	private JTextArea textArea;


	/**
	 * Create the panel.
	 */
	public HomeView(MainView frame) {

		this.frame = frame;
		setBackground(Color.LIGHT_GRAY);
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		//this.setSize(frame.getSize());
		setSize(640, 480);


		JLabel lblFilePath = new JLabel("File path: ");
		springLayout.putConstraint(SpringLayout.NORTH, lblFilePath, 55, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblFilePath, 50, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, lblFilePath, 230, SpringLayout.WEST, this);

		add(lblFilePath);

		textFieldFilePath = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textFieldFilePath, 55, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, textFieldFilePath, 120, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, textFieldFilePath, 71, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, textFieldFilePath, 300, SpringLayout.WEST, this);
		add(textFieldFilePath);
		textFieldFilePath.setColumns(10);

		JButton btnBrowse = new JButton("Browse");
		springLayout.putConstraint(SpringLayout.NORTH, btnBrowse, -5, SpringLayout.NORTH, lblFilePath);
		springLayout.putConstraint(SpringLayout.WEST, btnBrowse, 6, SpringLayout.EAST, textFieldFilePath);
		springLayout.putConstraint(SpringLayout.EAST, btnBrowse, 150, SpringLayout.EAST, textFieldFilePath);
		btnBrowse.addActionListener(this);
		btnBrowse.setActionCommand("Browse");
		add(btnBrowse);



		JButton btnCalculate = new JButton("Calculate journey");
		springLayout.putConstraint(SpringLayout.NORTH, btnCalculate, 171, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, btnCalculate, 50, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, btnCalculate, 197, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, btnCalculate, 230, SpringLayout.WEST, this);
		btnCalculate.addActionListener(this);
		btnCalculate.setActionCommand("Calculate");
		add(btnCalculate);

		JButton btnSortByJourney = new JButton("Sort by journey");
		springLayout.putConstraint(SpringLayout.WEST, btnSortByJourney, 50, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, btnSortByJourney, 230, SpringLayout.WEST, this);

		btnSortByJourney.addActionListener(this);
		btnSortByJourney.setActionCommand("SortByJourney");
		add(btnSortByJourney);

		JButton btnReturn = new JButton("Return to data type");
		springLayout.putConstraint(SpringLayout.NORTH, btnReturn, 10, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, btnReturn, 535, SpringLayout.WEST, lblFilePath);
		springLayout.putConstraint(SpringLayout.EAST, btnReturn, -55, SpringLayout.EAST, this);
		btnReturn.addActionListener(this);
		btnReturn.setActionCommand("Return");
		add(btnReturn);

		JButton btnSort = new JButton("Sort");
		springLayout.putConstraint(SpringLayout.NORTH, btnSortByJourney, 6, SpringLayout.SOUTH, btnSort);
		springLayout.putConstraint(SpringLayout.SOUTH, btnSortByJourney, 37, SpringLayout.SOUTH, btnSort);
		springLayout.putConstraint(SpringLayout.SOUTH, btnSort, 128, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.NORTH, btnSort, 99, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, btnSort, 50, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, btnSort, 230, SpringLayout.WEST, this);
		btnSort.addActionListener(this);
		btnSort.setActionCommand("Sort");
		add(btnSort);

		JButton btnSelect = new JButton("Select Data");
		springLayout.putConstraint(SpringLayout.NORTH, btnSelect, 0, SpringLayout.NORTH, btnSort);
		springLayout.putConstraint(SpringLayout.WEST, btnSelect, 21, SpringLayout.EAST, btnSort);
		springLayout.putConstraint(SpringLayout.EAST, btnSelect, 201, SpringLayout.EAST, btnSort);
		btnSelect.addActionListener(this);
		btnSelect.setActionCommand("Select");	
		add(btnSelect);

		JButton btnDate = new JButton("Tranform Date");
		springLayout.putConstraint(SpringLayout.SOUTH, btnReturn, -190, SpringLayout.SOUTH, btnDate);
		springLayout.putConstraint(SpringLayout.NORTH, btnDate, 6, SpringLayout.SOUTH, btnCalculate);
		springLayout.putConstraint(SpringLayout.SOUTH, btnDate, 32, SpringLayout.SOUTH, btnCalculate);
		springLayout.putConstraint(SpringLayout.WEST, btnDate, 50, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, btnDate, 0, SpringLayout.EAST, lblFilePath);
		btnDate.addActionListener(this);
		btnDate.setActionCommand("Transform");
		add(btnDate);

		JButton btnMergeCsvFiles = new JButton("Merge Csv files");

		springLayout.putConstraint(SpringLayout.NORTH, btnMergeCsvFiles, 6, SpringLayout.SOUTH, btnSelect);
		springLayout.putConstraint(SpringLayout.WEST, btnMergeCsvFiles, 251, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, btnMergeCsvFiles, 35, SpringLayout.SOUTH, btnSelect);
		springLayout.putConstraint(SpringLayout.EAST, btnMergeCsvFiles, 431, SpringLayout.WEST, this);
		btnMergeCsvFiles.addActionListener(this);
		btnMergeCsvFiles.setActionCommand("Merge");
		add(btnMergeCsvFiles);

		textArea = new JTextArea();
		textArea.setEditable(false);

		JScrollPane scrollpane = new JScrollPane(textArea);
		springLayout.putConstraint(SpringLayout.WEST, scrollpane, -40, SpringLayout.WEST, lblFilePath);
		springLayout.putConstraint(SpringLayout.SOUTH, scrollpane, -10, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, scrollpane, 630, SpringLayout.WEST, this);
		add(scrollpane);

		JButton btnLog = new JButton("Log Application");
		springLayout.putConstraint(SpringLayout.NORTH, btnLog, 0, SpringLayout.NORTH, btnSort);
		springLayout.putConstraint(SpringLayout.WEST, btnLog, 23, SpringLayout.EAST, btnSelect);
		springLayout.putConstraint(SpringLayout.EAST, btnLog, 203, SpringLayout.EAST, btnSelect);
		btnLog.addActionListener(this);
		btnLog.setActionCommand("Log");
		add(btnLog);

		JButton btnTraceback = new JButton("Traceback");
		springLayout.putConstraint(SpringLayout.WEST, btnTraceback, 23, SpringLayout.EAST, btnMergeCsvFiles);
		springLayout.putConstraint(SpringLayout.NORTH, btnTraceback, 1, SpringLayout.NORTH, btnSortByJourney);
		springLayout.putConstraint(SpringLayout.EAST, btnTraceback, 203, SpringLayout.EAST, btnMergeCsvFiles);
		btnTraceback.addActionListener(this);
		btnTraceback.setActionCommand("Traceback");
		add(btnTraceback);

		JButton btnDeductions = new JButton("Deductions");
		springLayout.putConstraint(SpringLayout.NORTH, btnDeductions, -1, SpringLayout.NORTH, btnCalculate);
		springLayout.putConstraint(SpringLayout.WEST, btnDeductions, 0, SpringLayout.WEST, btnLog);
		springLayout.putConstraint(SpringLayout.EAST, btnDeductions, 4, SpringLayout.EAST, scrollpane);
		btnDeductions.addActionListener(this);
		btnDeductions.setActionCommand("Deductions");
		add(btnDeductions);

		JButton btnAppendFileTo = new JButton("Append file to an other");
		springLayout.putConstraint(SpringLayout.NORTH, scrollpane, 62, SpringLayout.SOUTH, btnAppendFileTo);
		springLayout.putConstraint(SpringLayout.NORTH, btnAppendFileTo, -1, SpringLayout.NORTH, btnDate);
		springLayout.putConstraint(SpringLayout.WEST, btnAppendFileTo, 0, SpringLayout.WEST, btnSelect);
		springLayout.putConstraint(SpringLayout.EAST, btnAppendFileTo, 0, SpringLayout.EAST, btnSelect);
		btnAppendFileTo.addActionListener(this);
		btnAppendFileTo.setActionCommand("append");
		add(btnAppendFileTo);

		JButton btnDeleteAColumn = new JButton("Delete a column");

		springLayout.putConstraint(SpringLayout.NORTH, btnDeleteAColumn, 5, SpringLayout.SOUTH, btnMergeCsvFiles);


		springLayout.putConstraint(SpringLayout.NORTH, btnDeleteAColumn, -1, SpringLayout.NORTH, btnCalculate);

		springLayout.putConstraint(SpringLayout.WEST, btnDeleteAColumn, 0, SpringLayout.WEST, btnSelect);
		springLayout.putConstraint(SpringLayout.EAST, btnDeleteAColumn, 0, SpringLayout.EAST, btnSelect);
		btnDeleteAColumn.addActionListener(this);
		btnDeleteAColumn.setActionCommand("delete");
		add(btnDeleteAColumn);
		

		JButton btnAddActivities = new JButton("Add Activities");
		springLayout.putConstraint(SpringLayout.NORTH, btnAddActivities, 8, SpringLayout.SOUTH, btnAppendFileTo);
		springLayout.putConstraint(SpringLayout.WEST, btnAddActivities, 0, SpringLayout.WEST, btnSelect);
		springLayout.putConstraint(SpringLayout.EAST, btnAddActivities, 0, SpringLayout.EAST, btnSelect);
		btnAddActivities.addActionListener(this);
		btnAddActivities.setActionCommand("Activities");
		add(btnAddActivities);

		JButton btnAssumptions = new JButton("Assumptions");


		springLayout.putConstraint(SpringLayout.NORTH, btnAssumptions, -1, SpringLayout.NORTH, btnDate);
		springLayout.putConstraint(SpringLayout.WEST, btnAssumptions, 0, SpringLayout.WEST, btnLog);
		springLayout.putConstraint(SpringLayout.EAST, btnAssumptions, 0, SpringLayout.EAST, btnLog);
		btnAssumptions.addActionListener(this);
		btnAssumptions.setActionCommand("Assumptions");
		add(btnAssumptions);

		/*JButton btnDeleteAColumn = new JButton("Delete a column");
		springLayout.putConstraint(SpringLayout.NORTH, btnDeleteAColumn, 0, SpringLayout.NORTH, btnReturn);
		springLayout.putConstraint(SpringLayout.WEST, btnDeleteAColumn, 0, SpringLayout.WEST, scrollpane);

		btnDeleteAColumn.addActionListener(this);
		btnDeleteAColumn.setActionCommand("delete");
		add(btnDeleteAColumn);
		 */
		setVisible(true);
	}

	public String selectFile(){
		String file= new String();
		// Exemple numéro 2
		// Boîte de sélection de fichier à partir du répertoire courant
		File repertoireCourant = null;
		try {
			// obtention d'un objet File qui désigne le répertoire courant. Le
			// "getCanonicalFile" n'est pas absolument nécessaire mais permet
			// d'éviter les /Truc/./Chose/ ...
			repertoireCourant = new File(".").getCanonicalFile();
			//System.out.println("Répertoire courant : " + repertoireCourant);
		} catch(IOException e) {}

		// création de la boîte de dialogue dans ce répertoire courant
		// (ou dans "home" s'il y a eu une erreur d'entrée/sortie, auquel
		// cas repertoireCourant vaut null)
		JFileChooser dialogue = new JFileChooser(repertoireCourant);

		// affichage
		dialogue.showOpenDialog(null);

		// récupération du fichier sélectionné
		//System.out.println("Fichier choisi : " + dialogue.getSelectedFile());
		file= dialogue.getSelectedFile().toString();
		return file;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String cmd = e.getActionCommand();
		if(cmd.equals("Browse")){ 

			textFieldFilePath.setText(this.selectFile());
			String recup;
			recup = textFieldFilePath.getText();

			MainView.logger.log(Level.INFO, "Click on the button Browse, choice of the file "+ recup);


		}

		else if(cmd.equals("Sort")){ 



			if (textFieldFilePath.getText().equals("")){
				textArea.append("Please, select file from file browser \n");
			}

			else{
				ArrayList<String[]> test2 = new ArrayList<String[]>();
				String filePath= textFieldFilePath.getText();

				MainView.logger.log(Level.INFO, "Click on the button Sort, it sorted the file " + filePath);

				Reader myreader= new Reader(filePath);

				String result = "" ;

				try {


					test2=myreader.readCsv(myreader.getMyFilePath());
					for (int i= 0; i<test2.get(0).length; i++){
						result = result + test2.get(0)[i] + ", ";
						//comboBoxColumnName.addItem(test2.get(0)[i]);
					}

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				MainView.logger.log(Level.INFO, "In the file there are " + result + "as data");

				frame.changePanel(new SortView(frame,test2.get(0), test2));


			}
		}

		else if(cmd.equals("SortByJourney")){
			if (textFieldFilePath.getText().equals("")){
				textArea.append("Please, select file from file browser  \n");
			}
			else{
				ArrayList<String[]> test2 = new ArrayList<String[]>();
				String filePath= textFieldFilePath.getText();


				MainView.logger.log(Level.INFO, "Click on the button SortByJourney, choice of the file "+ filePath);


				Reader myreader= new Reader(filePath);

				try {


					test2=myreader.readCsv(myreader.getMyFilePath());



				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}



				frame.changePanel(new SortByJourneyView(frame,test2));

			}
		}
		else if(cmd.equals("Calculate")){

			if (textFieldFilePath.getText().equals("")){
				textArea.append("Please, select file from file browser  \n");
			}
			else{
				ArrayList<String[]> test2 = new ArrayList<String[]>();
				String filePath= textFieldFilePath.getText();
				
				MainView.logger.log(Level.INFO, "Click on the button Calculate, choice of the file "+ filePath);

				Reader myreader= new Reader(filePath);

				try {


					test2=myreader.readCsv(myreader.getMyFilePath());


				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}


				frame.changePanel(new CalculJourneyView(frame, test2.get(0), test2));
			}


		}


		else if(cmd.equals("Select")){
			if (textFieldFilePath.getText().equals("")){
				textArea.append("Please, select file from file browser  \n");
			}
			else{

				ArrayList<String[]> test2 = new ArrayList<String[]>();
				String filePath= textFieldFilePath.getText();

				Reader myreader= new Reader(filePath);
				try {
					test2=myreader.readCsv(myreader.getMyFilePath());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frame.changePanel(new AccessView1(frame, test2));
			}
		}
		else if(cmd.equals("Transform")){
			if (textFieldFilePath.getText().equals("")){
				textArea.append("Please, select file from file browser  \n");
			}
			else{
				ArrayList<String[]> myTab = new ArrayList<String[]>();
				String filePath= textFieldFilePath.getText();

				Reader myreader= new Reader(filePath);
				try {
					myTab = myreader.readCsv(myreader.getMyFilePath());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frame.changePanel(new DateView(frame, myTab));
			}
		}
		else if(cmd.equals("Merge")){


			frame.changePanel(new MergeView(frame));
		}
		else if(cmd.equals("Return")){

			frame.changePanel(new ChooseDataSourceView(frame));

		}
		else if(cmd.equals("delete")){

			if (textFieldFilePath.getText().equals("")){
				textArea.append("Please, select file from file browser  \n");
			}
			else{

				ArrayList<String[]> myTab = new ArrayList<String[]>();
				String filePath= textFieldFilePath.getText();
				MainView.logger.log(Level.INFO, "Click on the button delete column, the file selected is : " + filePath );

				Reader myreader= new Reader(filePath);
				try {
					myTab = myreader.readCsv(myreader.getMyFilePath());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frame.changePanel(new DeleteColumnView(frame, myTab));
			}
		}
		else if(cmd.equals("append")){
			frame.changePanel(new AppendView(frame));
			MainView.logger.log(Level.INFO, "Click on the button Append file to an other");
		}
		else if (cmd.equals("Log")){
			frame.changePanel(new LogView(frame));
			MainView.logger.log(Level.INFO, "Click on the button Log Application");
		}
		else if (cmd.equals("Traceback")){
			frame.changePanel(new TracebackView(frame));
			MainView.logger.log(Level.INFO, "Click on the button Traceback");
		}
		else if (cmd.equals("Deductions")){
			frame.changePanel(new DeductionView(frame));
			MainView.logger.log(Level.INFO, "Click on the button Traceback");
		}
		else if (cmd.equals("Assumptions")){
			frame.changePanel(new AssumptionView(frame));
			MainView.logger.log(Level.INFO, "Click on the button Traceback");
		}
		else if (cmd.equals("Activities")){
			frame.changePanel(new ActivityView1(frame));
		}

	}
}
