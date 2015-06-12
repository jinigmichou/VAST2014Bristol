package ui;

import java.awt.BorderLayout;
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

import core.Reader;
import persist.ReaderCSV;
import persist.WriterCSV;

public class HomeView extends JPanel implements ActionListener{
	
	private JPanel homePanel;
	private JTextField textFieldFilePath;
	/**
	 * Create the panel.
	 */
	public HomeView() {
		System.out.println("je vais y arriver");
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBackground(Color.WHITE);
		SpringLayout springLayout = new SpringLayout();


		JLabel lblFilePath = new JLabel("File path: ");
		springLayout.putConstraint(SpringLayout.NORTH, lblFilePath, 70, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblFilePath, 25, SpringLayout.WEST, this);
		add(lblFilePath);

		textFieldFilePath = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textFieldFilePath, 70, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, textFieldFilePath, 6, SpringLayout.EAST, lblFilePath);
		springLayout.putConstraint(SpringLayout.EAST, textFieldFilePath, 214, SpringLayout.EAST, lblFilePath);
		add(textFieldFilePath);
		textFieldFilePath.setColumns(10);

		JButton btnBrowse = new JButton("Browse");
		springLayout.putConstraint(SpringLayout.WEST, btnBrowse, 6, SpringLayout.EAST, textFieldFilePath);
		springLayout.putConstraint(SpringLayout.SOUTH, btnBrowse, 0, SpringLayout.SOUTH, textFieldFilePath);
		btnBrowse.addActionListener(this);
		btnBrowse.setActionCommand("Browse");
		add(btnBrowse);

		JButton btnValid = new JButton("Valid");
		springLayout.putConstraint(SpringLayout.NORTH, btnValid, 18, SpringLayout.SOUTH, textFieldFilePath);
		springLayout.putConstraint(SpringLayout.EAST, btnValid, 0, SpringLayout.EAST, textFieldFilePath);
		btnValid.addActionListener(this);
		btnValid.setActionCommand("Valid");
		add(btnValid);

		
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
		else if(cmd.equals("Valid")){ 

			System.out.println("le chemin du fichier est : "+textFieldFilePath.getText());
			ArrayList<String[]> test2 = new ArrayList<String[]>();
			Reader test1= new Reader(textFieldFilePath.getText());
			try {
				System.out.println("array "+test1.getMyFile());
				System.out.println("File path "+textFieldFilePath.getText());
				
				test1.readCsv(test1.getMyFilePath());
				for (int i= 0; i<test2.get(0).length; i++){
					System.out.println(test2.get(0)[i]);
					//comboBoxColumnName.addItem(test2.get(0)[i]);
				}

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			test1.sortColumn(test2, 1);
			

			WriterCSV test3= new WriterCSV();
			String pathnewfile=new String("testFichiercsv.csv");
			try {
				test3.writeCsv(test2, pathnewfile);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
			
		}

	}
	

}