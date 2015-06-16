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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.LineBorder;

import persist.WriterCSV;
import core.Operator;
import core.Reader;
import core.Writer;


public class HomeView extends JPanel implements ActionListener{

	private MainView frame;
	private JTextField textFieldFilePath;


	/**
	 * Create the panel.
	 */
	public HomeView() {

		
		setBackground(Color.LIGHT_GRAY);
		

		setLayout(null);


		JLabel lblFilePath = new JLabel("File path: ");
		lblFilePath.setBounds(50, 55, 180, 16);
		
		add(lblFilePath);

		textFieldFilePath = new JTextField();
		textFieldFilePath.setBounds(120, 55, 180, 16);
		add(textFieldFilePath);
		textFieldFilePath.setColumns(10);

		JButton btnBrowse = new JButton("Browse");
		btnBrowse.setBounds(325, 56, 180, 16);
		btnBrowse.addActionListener(this);
		btnBrowse.setActionCommand("Browse");
		add(btnBrowse);

		

		JButton btnCalculate = new JButton("Calculate journey");
		btnCalculate.setBounds(50, 171, 180, 16);
		btnCalculate.addActionListener(this);
		btnCalculate.setActionCommand("Calculate");
		add(btnCalculate);

		JButton btnSortByJourney = new JButton("Sort by journey");
		btnSortByJourney.setBounds(50, 140, 180, 16);
		
		btnSortByJourney.addActionListener(this);
		btnSortByJourney.setActionCommand("SortByJourney");
		add(btnSortByJourney);
		
		JButton btnReturn = new JButton("Return to data type");
		btnReturn.addActionListener(this);
		btnReturn.setActionCommand("Return");
		btnReturn.setBounds(50, 199, 180, 16);
		add(btnReturn);
		
		JButton btnSort = new JButton("Sort");
		btnSort.setBounds(50, 110, 180, 16);
		btnSort.addActionListener(this);
		btnSort.setActionCommand("Sort");
		add(btnSort);


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
			MainView frame = new MainView();
			MainView.logger.log(Level.INFO, "In the file there are " + result + "as data");
			frame.setContentPane(new SortView(test2.get(0), test2));



		}
		else if(cmd.equals("SortByJourney")){

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

			MainView frame = new MainView();

			frame.setContentPane(new SortByJourneyView(test2));

		}
		else if(cmd.equals("Calculate")){

			ArrayList<String[]> test2 = new ArrayList<String[]>();
			String filePath= textFieldFilePath.getText();

			Reader myreader= new Reader(filePath);

			try {


				test2=myreader.readCsv(myreader.getMyFilePath());
				for (int i= 0; i<test2.get(0).length; i++){
					System.out.println(test2.get(0)[i]);
					//comboBoxColumnName.addItem(test2.get(0)[i]);
				}

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			/*CalculJourneyView journeyview= new CalculJourneyView(test2.get(0), test2);
				MainView.changePanel(journeyview);*/
			MainView frame = new MainView();
			frame.setContentPane(new CalculJourneyView(test2.get(0), test2));
			/*testPanel = new Test();
				changePanel(testPanel);*/
			setVisible(true);

		}
		else if(cmd.equals("Return")){
			MainView frame = new MainView();
			frame.setContentPane(new ChooseDataSourceView());
			setVisible(true);
		}




	}
}
