package ui;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.filechooser.FileFilter;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.logging.*;


@SuppressWarnings("serial")
public class MainView extends JFrame implements ActionListener {

	//persist choosen
	//private int persistType;

	private static JPanel panelDisplay;
	private SpringLayout springLayout;

	private ChooseDataSourceView chooseDataSourceView;
	private Test testPanel;

	protected static Logger logger=
			Logger.getLogger("ui.MainView");



	//private AccountView accountPanel;

	/**
	 * Create the application.
	 */
	public static void main(String[] args){
		new MainView(1);
	}
	public MainView(int log) {

		//this.persistType = persistType;
		//On instancie une facadeUser pour la vue
		if(log==1){

			try {
				Handler fh = new FileHandler("log.log", true);
				fh.setFormatter(new SimpleFormatter());
				logger.addHandler(fh);
				logger.log(Level.INFO, "Start up of the program");


			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			setTitle("Csv Application");
			setResizable(false);
			initialize();
		}
	}

	private void createWindow(String path){
		JFrame f = new JFrame("Display the data file");
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.add(new DisplayCsv(path));
		f.setSize(new Dimension(700, 500));
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}

	void chooseFile(){
		JFileChooser jf = new JFileChooser();
		jf.setFileFilter(new FileFilter() {
			private String getExtension(File f) {
				String ext = null;
				String s = f.getName();
				int i = s.lastIndexOf('.');

				if (i > 0 &&  i < s.length() - 1) {
					ext = s.substring(i+1).toLowerCase();
				}
				return ext;
			}

			public String getDescription() {
				return "Files csv *.csv";
			}

			@Override
			public boolean accept(File f) {
				if (f.isDirectory()) {
					return true;
				}
				String extension = getExtension(f);
				if (extension != null) {
					if (extension.equals("csv")){
						return true;
					} 
					else {
						return false;
					}
				}
				return false;
			}
		});
		int returnVal = jf.showOpenDialog(null);
		if(returnVal==JFileChooser.APPROVE_OPTION){
			createWindow(jf.getSelectedFile().getPath());

		}
	}

	void applyLookAndFeel(){
		try{
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		}
		catch(Exception e){}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setSize( 640 , 480 );
		setBounds(100, 100, 800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);

		getContentPane().setBackground(Color.LIGHT_GRAY);
		panelDisplay = new JPanel();
		panelDisplay.setBackground(Color.LIGHT_GRAY);
		springLayout.putConstraint(SpringLayout.NORTH, panelDisplay, 169, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, panelDisplay, 10, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panelDisplay, -10, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panelDisplay, -10, SpringLayout.EAST, getContentPane());
		getContentPane().add(panelDisplay);

		JButton homeButton = new JButton("Click to Start");
		chooseDataSourceView = new ChooseDataSourceView(this);
		springLayout.putConstraint(SpringLayout.NORTH, homeButton, 10, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, homeButton, 10, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, homeButton, -613, SpringLayout.EAST, getContentPane());
		homeButton.addActionListener(this);
		homeButton.setActionCommand("Manage DB");
		getContentPane().add(homeButton);



		setVisible(true);

	}

	/**
	 * Methode qui change le panel en fonction des actions
	 * @param panel
	 */

	public void changePanel(JPanel panel){
		//remove ancient JPanel if exist
		/*panelDisplay.removeAll();
		panelDisplay.repaint();
		panelDisplay.revalidate();*/
		//add the new JPanel
		/*panelDisplay.add(panel);
		panelDisplay.repaint();
		panelDisplay.revalidate();*/

		this.setContentPane(panel);
		setVisible(true);	
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		String cmd = e.getActionCommand();
		if(cmd.equals("Manage DB")){ 
			logger.log(Level.INFO, "Click on the buttom start application");
			changePanel(new ChooseDataSourceView(this));

			//setVisible(true);


		}


	}

}