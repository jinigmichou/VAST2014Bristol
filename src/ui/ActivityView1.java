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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import core.Reader;

public class ActivityView1 extends JPanel implements ActionListener {

	private MainView frame;
	private JTextField textFieldFile1;
	private JTextField textFieldFile2;
	private JTextArea textArea;

	public ActivityView1(MainView frame) {
		this.frame = frame;
		setSize(640, 480);
		SpringLayout springLayout = new SpringLayout();
		SpringLayout springLayout_1 = new SpringLayout();
		setLayout(springLayout_1);
		setBackground(Color.LIGHT_GRAY);

		JLabel lblTheOtherFile = new JLabel("file 1: ");
		springLayout_1.putConstraint(SpringLayout.WEST, lblTheOtherFile, 10, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.NORTH, lblTheOtherFile, 48, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblTheOtherFile, 10, SpringLayout.WEST, this);
		add(lblTheOtherFile);

		textFieldFile1 = new JTextField();
		springLayout_1.putConstraint(SpringLayout.WEST, textFieldFile1, 82, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.NORTH, textFieldFile1, -6, SpringLayout.NORTH, lblTheOtherFile);
		springLayout.putConstraint(SpringLayout.WEST, textFieldFile1, 17, SpringLayout.EAST, lblTheOtherFile);
		springLayout.putConstraint(SpringLayout.EAST, textFieldFile1, 222, SpringLayout.EAST, lblTheOtherFile);
		add(textFieldFile1);
		textFieldFile1.setColumns(10);

		JLabel lblFile = new JLabel("file 2: ");
		springLayout_1.putConstraint(SpringLayout.NORTH, lblFile, 113, SpringLayout.NORTH, this);
		springLayout_1.putConstraint(SpringLayout.SOUTH, lblTheOtherFile, -37, SpringLayout.NORTH, lblFile);
		springLayout_1.putConstraint(SpringLayout.EAST, lblFile, 0, SpringLayout.EAST, lblTheOtherFile);
		springLayout.putConstraint(SpringLayout.NORTH, lblFile, 34, SpringLayout.SOUTH, textFieldFile1);
		springLayout.putConstraint(SpringLayout.WEST, lblFile, 0, SpringLayout.WEST, lblTheOtherFile);
		add(lblFile);

		textFieldFile2 = new JTextField();
		springLayout_1.putConstraint(SpringLayout.SOUTH, textFieldFile1, -25, SpringLayout.NORTH, textFieldFile2);
		springLayout_1.putConstraint(SpringLayout.WEST, textFieldFile2, 0, SpringLayout.WEST, textFieldFile1);
		springLayout_1.putConstraint(SpringLayout.EAST, textFieldFile2, 0, SpringLayout.EAST, textFieldFile1);
		springLayout_1.putConstraint(SpringLayout.NORTH, textFieldFile2, 107, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.NORTH, textFieldFile2, 28, SpringLayout.SOUTH, textFieldFile1);
		springLayout.putConstraint(SpringLayout.WEST, textFieldFile2, 0, SpringLayout.WEST, textFieldFile1);
		springLayout.putConstraint(SpringLayout.EAST, textFieldFile2, 0, SpringLayout.EAST, textFieldFile1);
		add(textFieldFile2);
		textFieldFile2.setColumns(10);

		JButton btnBrowse1 = new JButton("Browse");
		springLayout_1.putConstraint(SpringLayout.WEST, btnBrowse1, 337, SpringLayout.WEST, this);
		springLayout_1.putConstraint(SpringLayout.EAST, textFieldFile1, -6, SpringLayout.WEST, btnBrowse1);
		springLayout_1.putConstraint(SpringLayout.NORTH, btnBrowse1, -5, SpringLayout.NORTH, lblTheOtherFile);
		springLayout.putConstraint(SpringLayout.NORTH, btnBrowse1, 0, SpringLayout.NORTH, textFieldFile1);
		springLayout.putConstraint(SpringLayout.WEST, btnBrowse1, 25, SpringLayout.EAST, textFieldFile1);
		btnBrowse1.addActionListener(this);
		btnBrowse1.setActionCommand("browse1");
		add(btnBrowse1);

		JButton btnBrowse2 = new JButton("Browse");
		springLayout_1.putConstraint(SpringLayout.NORTH, btnBrowse2, -5, SpringLayout.NORTH, lblFile);
		springLayout_1.putConstraint(SpringLayout.WEST, btnBrowse2, 0, SpringLayout.WEST, btnBrowse1);
		springLayout.putConstraint(SpringLayout.WEST, btnBrowse2, 0, SpringLayout.WEST, btnBrowse1);
		springLayout.putConstraint(SpringLayout.SOUTH, btnBrowse2, 0, SpringLayout.SOUTH, textFieldFile2);
		btnBrowse2.addActionListener(this);
		btnBrowse2.setActionCommand("browse2");
		add(btnBrowse2);

		JButton btnValid = new JButton("Valid");
		springLayout_1.putConstraint(SpringLayout.NORTH, btnValid, 26, SpringLayout.SOUTH, textFieldFile2);
		springLayout_1.putConstraint(SpringLayout.EAST, btnValid, 0, SpringLayout.EAST, textFieldFile1);
		springLayout.putConstraint(SpringLayout.NORTH, btnValid, 47, SpringLayout.SOUTH, btnBrowse2);
		springLayout.putConstraint(SpringLayout.WEST, btnValid, 0, SpringLayout.WEST, btnBrowse1);
		springLayout.putConstraint(SpringLayout.EAST, btnValid, 0, SpringLayout.EAST, btnBrowse1);
		btnValid.addActionListener(this);
		btnValid.setActionCommand("valid");
		add(btnValid);

		JButton btnBack = new JButton("Back");
		springLayout_1.putConstraint(SpringLayout.NORTH, btnBack, 0, SpringLayout.NORTH, btnValid);
		springLayout_1.putConstraint(SpringLayout.WEST, btnBack, 175, SpringLayout.WEST, this);
		springLayout_1.putConstraint(SpringLayout.EAST, btnBack, 250, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.NORTH, btnBack, 47, SpringLayout.SOUTH, textFieldFile2);
		springLayout.putConstraint(SpringLayout.WEST, btnBack, -109, SpringLayout.WEST, btnValid);
		springLayout.putConstraint(SpringLayout.EAST, btnBack, -21, SpringLayout.WEST, btnValid);
		btnBack.addActionListener(this);
		btnBack.setActionCommand("back");
		add(btnBack);


		textArea = new JTextArea();
		textArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(textArea);
		springLayout_1.putConstraint(SpringLayout.NORTH, scrollPane, 13, SpringLayout.SOUTH, btnValid);
		springLayout_1.putConstraint(SpringLayout.WEST, scrollPane, 10, SpringLayout.WEST, this);
		springLayout_1.putConstraint(SpringLayout.SOUTH, scrollPane, 177, SpringLayout.SOUTH, btnValid);
		springLayout_1.putConstraint(SpringLayout.EAST, scrollPane, 0, SpringLayout.EAST, btnBrowse1);
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 19, SpringLayout.SOUTH, btnValid);
		springLayout.putConstraint(SpringLayout.WEST, scrollPane, 10, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, 242, SpringLayout.SOUTH, textFieldFile2);
		springLayout.putConstraint(SpringLayout.EAST, scrollPane, 0, SpringLayout.EAST, btnBrowse1);
		add(scrollPane);
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
		if(cmd.equals("browse1")){ 
			textFieldFile1.setText(this.selectFile());

		}
		else if(cmd.equals("browse2")){ 
			textFieldFile2.setText(this.selectFile());

		}
		else if(cmd.equals("valid")){ 
			if (textFieldFile1.getText().equals("")){
				textArea.append("Please, select a file from file browser 1 \n");
			}
			else if(textFieldFile2.getText().equals("")){
				textArea.append("Please, select a file from file browser 2 \n");
			}
			ArrayList<String[]> myFile1 = new ArrayList<String[]>();
			ArrayList<String[]> myFile2 = new ArrayList<String[]>();

			String filePath1= textFieldFile1.getText();
			String filePath2= textFieldFile2.getText();


			Reader myreader1 = new Reader(filePath1);
			Reader myreader2 = new Reader(filePath2);

			//String result = "" ;

			try {


				myFile1 = myreader1.readCsv(myreader1.getMyFilePath());
				myFile2 = myreader2.readCsv(myreader2.getMyFilePath());
				/*for (int i= 0; i<test2.get(0).length; i++){
					result = result + test2.get(0)[i] + ", ";
					//comboBoxColumnName.addItem(test2.get(0)[i]);
				}*/

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}


			frame.changePanel(new ActivityView2(frame, myFile1, myFile2));


		}
		else if(cmd.equals("back")){ 
			frame.changePanel(new HomeView(frame));

		}
	}	
}


