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
		springLayout.putConstraint(SpringLayout.WEST, btnBrowse, 24, SpringLayout.EAST, textFieldFilePath);
		springLayout.putConstraint(SpringLayout.SOUTH, btnBrowse, 79, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, btnBrowse, 204, SpringLayout.EAST, textFieldFilePath);
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
		springLayout.putConstraint(SpringLayout.WEST, btnReturn, 0, SpringLayout.WEST, lblFilePath);
		springLayout.putConstraint(SpringLayout.EAST, btnReturn, 0, SpringLayout.EAST, lblFilePath);
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

		JButton btnAccess = new JButton("Building Access");
		springLayout.putConstraint(SpringLayout.NORTH, btnAccess, 0, SpringLayout.NORTH, btnSort);
		springLayout.putConstraint(SpringLayout.WEST, btnAccess, 21, SpringLayout.EAST, btnSort);
		springLayout.putConstraint(SpringLayout.EAST, btnAccess, 201, SpringLayout.EAST, btnSort);
		btnAccess.addActionListener(this);
		btnAccess.setActionCommand("Access");	
		add(btnAccess);

		JButton btnDate = new JButton("Tranform Date");
		springLayout.putConstraint(SpringLayout.NORTH, btnReturn, 6, SpringLayout.SOUTH, btnDate);
		springLayout.putConstraint(SpringLayout.SOUTH, btnReturn, 35, SpringLayout.SOUTH, btnDate);
		springLayout.putConstraint(SpringLayout.NORTH, btnDate, 6, SpringLayout.SOUTH, btnCalculate);
		springLayout.putConstraint(SpringLayout.SOUTH, btnDate, 32, SpringLayout.SOUTH, btnCalculate);
		springLayout.putConstraint(SpringLayout.WEST, btnDate, 50, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, btnDate, 0, SpringLayout.EAST, lblFilePath);
		btnDate.addActionListener(this);
		btnDate.setActionCommand("Transform");
		add(btnDate);

		JButton btnMergeCsvFiles = new JButton("Merge Csv files");
		springLayout.putConstraint(SpringLayout.NORTH, btnMergeCsvFiles, 6, SpringLayout.SOUTH, btnAccess);
		springLayout.putConstraint(SpringLayout.WEST, btnMergeCsvFiles, 251, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, btnMergeCsvFiles, 35, SpringLayout.SOUTH, btnAccess);
		springLayout.putConstraint(SpringLayout.EAST, btnMergeCsvFiles, 431, SpringLayout.WEST, this);
		btnMergeCsvFiles.addActionListener(this);
		btnMergeCsvFiles.setActionCommand("Merge");
		add(btnMergeCsvFiles);

		textArea = new JTextArea();
		textArea.setEditable(false);
		JScrollPane scrollpane = new JScrollPane(textArea);
		springLayout.putConstraint(SpringLayout.NORTH, scrollpane, 29, SpringLayout.SOUTH, btnReturn);
		springLayout.putConstraint(SpringLayout.WEST, scrollpane, 0, SpringLayout.WEST, lblFilePath);
		springLayout.putConstraint(SpringLayout.SOUTH, scrollpane, 180, SpringLayout.SOUTH, btnReturn);
		springLayout.putConstraint(SpringLayout.EAST, scrollpane, 566, SpringLayout.WEST, this);
		add(scrollpane);

		JButton btnDeleteAColumn = new JButton("Delete a column");
		springLayout.putConstraint(SpringLayout.NORTH, btnDeleteAColumn, 5, SpringLayout.SOUTH, btnMergeCsvFiles);
		springLayout.putConstraint(SpringLayout.WEST, btnDeleteAColumn, 10, SpringLayout.WEST, btnMergeCsvFiles);
		springLayout.putConstraint(SpringLayout.EAST, btnDeleteAColumn, 0, SpringLayout.EAST, btnAccess);
		btnDeleteAColumn.addActionListener(this);
		btnDeleteAColumn.setActionCommand("delete");
		add(btnDeleteAColumn);


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

			MainView.logger.log(Level.INFO, "Click on the buttom Browse, choice of the file "+ recup);


		}

		else if(cmd.equals("Sort")){ 



			if (textFieldFilePath.getText().equals("")){
				textArea.append("Please, select file from file browser \n");
			}

			else{
				ArrayList<String[]> test2 = new ArrayList<String[]>();
				String filePath= textFieldFilePath.getText();

				MainView.logger.log(Level.INFO, "Click on the buttom Sort, it sorted the file " + filePath);

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


				MainView.logger.log(Level.INFO, "Click on the buttom SortByJourney, choice of the file "+ filePath);


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


		else if(cmd.equals("Access")){
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

	}
}
