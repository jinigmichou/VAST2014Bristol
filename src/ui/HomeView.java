package ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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
	
	private MainView mainView;
	private JTextField textFieldFilePath;

	/**
	 * Create the panel.
	 */
	public HomeView() {
		System.out.println("je vais y arriver");
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBackground(Color.WHITE);
		SpringLayout springLayout = new SpringLayout();
		SpringLayout springLayout_1 = new SpringLayout();
		


		JLabel lblFilePath = new JLabel("File path: ");
		springLayout_1.putConstraint(SpringLayout.NORTH, lblFilePath, 12, SpringLayout.NORTH, this);
		springLayout_1.putConstraint(SpringLayout.WEST, lblFilePath, 24, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.NORTH, lblFilePath, 70, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblFilePath, 25, SpringLayout.WEST, this);
		add(lblFilePath);

		textFieldFilePath = new JTextField();
		springLayout_1.putConstraint(SpringLayout.NORTH, textFieldFilePath, -6, SpringLayout.NORTH, lblFilePath);
		springLayout_1.putConstraint(SpringLayout.WEST, textFieldFilePath, 6, SpringLayout.EAST, lblFilePath);
		springLayout.putConstraint(SpringLayout.NORTH, textFieldFilePath, 70, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, textFieldFilePath, 6, SpringLayout.EAST, lblFilePath);
		springLayout.putConstraint(SpringLayout.EAST, textFieldFilePath, 214, SpringLayout.EAST, lblFilePath);
		add(textFieldFilePath);
		textFieldFilePath.setColumns(10);

		JButton btnBrowse = new JButton("Browse");
		springLayout_1.putConstraint(SpringLayout.NORTH, btnBrowse, -5, SpringLayout.NORTH, lblFilePath);
		springLayout_1.putConstraint(SpringLayout.WEST, btnBrowse, 6, SpringLayout.EAST, textFieldFilePath);
		springLayout.putConstraint(SpringLayout.WEST, btnBrowse, 6, SpringLayout.EAST, textFieldFilePath);
		springLayout.putConstraint(SpringLayout.SOUTH, btnBrowse, 0, SpringLayout.SOUTH, textFieldFilePath);
		btnBrowse.addActionListener(this);
		btnBrowse.setActionCommand("Browse");
		add(btnBrowse);

		JButton btnSort = new JButton("Sort");
		springLayout_1.putConstraint(SpringLayout.NORTH, btnSort, 15, SpringLayout.SOUTH, textFieldFilePath);
		springLayout_1.putConstraint(SpringLayout.WEST, btnSort, 0, SpringLayout.WEST, textFieldFilePath);
		springLayout.putConstraint(SpringLayout.NORTH, btnSort, 18, SpringLayout.SOUTH, textFieldFilePath);
		springLayout.putConstraint(SpringLayout.EAST, btnSort, 0, SpringLayout.EAST, textFieldFilePath);
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
			String test = textFieldFilePath.getText();
			System.out.println("essaie "+test);
			
		}
		else if(cmd.equals("Sort")){ 

			System.out.println("le chemin du fichier est : "+textFieldFilePath.getText());
			ArrayList<String[]> test2 = new ArrayList<String[]>();
			String filePath= textFieldFilePath.getText();
			System.out.println("file path : "+filePath);
			Reader myreader= new Reader(filePath);
			System.out.println("le chemin du fichier est 2: "+myreader.getMyFilePath());
			try {
				System.out.println("array "+myreader.getMyFile());
				System.out.println("File path "+textFieldFilePath.getText());
				
				test2=myreader.readCsv(myreader.getMyFilePath());
				for (int i= 0; i<test2.get(0).length; i++){
					System.out.println(test2.get(0)[i]);
					//comboBoxColumnName.addItem(test2.get(0)[i]);
				}

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			SortView sortview= new SortView(test2.get(0));
			MainView.changePanel(sortview);
			test2.remove(0);

			//tri
			/*
			Operator myOperator= new Operator(test2);
			myOperator.sortColumn(test2, 1);
			myOperator.tranformDate(test2, 0);
			test2=myOperator.sortTimestamp(test2, 0);
			test2=myOperator.journeyCalculation(test2);
			myOperator.verifyJourney(test2);

			WriterCSV test3= new WriterCSV();
			String pathnewfile=new String("testFichiercsv1.csv");
			try {
				test3.writeCsv(test2, pathnewfile);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
			
		*/}

	}
	

}
