package ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

import persist.ReaderCSV;
import persist.WriterCSV;
import core.Reader;

public class HomePanel extends JFrame implements ActionListener {

	private JFrame frame;
	private JTextField textFieldFilePath;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomePanel window = new HomePanel();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public HomePanel() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 640, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);

		JButton btnHome = new JButton("Home");
		springLayout.putConstraint(SpringLayout.NORTH, btnHome, 6, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, btnHome, 0, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(btnHome);

		JLabel lblFilePath = new JLabel("File path:");
		springLayout.putConstraint(SpringLayout.NORTH, lblFilePath, 74, SpringLayout.SOUTH, btnHome);
		springLayout.putConstraint(SpringLayout.WEST, lblFilePath, 41, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(lblFilePath);

		textFieldFilePath = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textFieldFilePath, -6, SpringLayout.NORTH, lblFilePath);
		springLayout.putConstraint(SpringLayout.WEST, textFieldFilePath, 4, SpringLayout.EAST, lblFilePath);
		springLayout.putConstraint(SpringLayout.EAST, textFieldFilePath, -129, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(textFieldFilePath);
		textFieldFilePath.setColumns(10);

		JButton btnBrowse = new JButton("Browse");
		springLayout.putConstraint(SpringLayout.NORTH, btnBrowse, -5, SpringLayout.NORTH, lblFilePath);
		springLayout.putConstraint(SpringLayout.WEST, btnBrowse, 6, SpringLayout.EAST, textFieldFilePath);
		frame.getContentPane().add(btnBrowse);

		JButton btnValid = new JButton("Valid");
		springLayout.putConstraint(SpringLayout.NORTH, btnValid, 22, SpringLayout.SOUTH, textFieldFilePath);
		springLayout.putConstraint(SpringLayout.EAST, btnValid, 0, SpringLayout.EAST, textFieldFilePath);
		frame.getContentPane().add(btnValid);

		btnBrowse.addActionListener(this);
		btnValid.addActionListener(new TraitementValid());
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

	//@Override
	/*public void actionPerformed(ActionEvent e) {
		Object  source=e.getSource();
		// TODO Auto-generated method stub

		if (e.equals(btnBrowse)){
		}
		else if (e.equals(btnValid)){
			System.out.println("le chemin du fichier est : "+textFieldFilePath.getText());
		}
		System.out.println("coucou");
		System.out.println(source.toString());


	}*/

	public  class   TraitementValid implements   ActionListener
	{
		/**
		 * obligatoire car test implémente l'interface ActionListener
		 */
		public  void    actionPerformed(ActionEvent e)
		{
			System.out.println("le chemin du fichier est : "+textFieldFilePath.getText());
			ArrayList<String[]> test2 = new ArrayList<String[]>();
			ReaderCSV test1= new ReaderCSV();
			try {
				test1.readCsv(test2,textFieldFilePath.getText());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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

	@Override
	public void actionPerformed(ActionEvent e) {
		textFieldFilePath.setText(this.selectFile());
		String test = textFieldFilePath.getText();
		System.out.println("essaie "+test);

	}
}

