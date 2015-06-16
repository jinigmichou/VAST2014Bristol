package ui;

import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import java.awt.CardLayout;

/**
 * 
 * @author All 
 *
 */

@SuppressWarnings("serial")
public class MainView extends JFrame implements ActionListener {
	
	//persist choosen
	//private int persistType;
	
	private static JPanel panelDisplay;
	private SpringLayout springLayout;
	
	private ChooseDataSourceView chooseDataSourceView;
	private Test testPanel;
	//private AccountView accountPanel;
	
	/**
	 * Create the application.
	 */
	public static void main(String[] args){
		new MainView();
	}
	public MainView() {
		//this.persistType = persistType;
		//On instancie une facadeUser pour la vue
		setTitle("Welcome");
		setResizable(false);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setBounds(100, 100, 800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		
		panelDisplay = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panelDisplay, 169, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, panelDisplay, 10, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panelDisplay, -10, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panelDisplay, -10, SpringLayout.EAST, getContentPane());
		getContentPane().add(panelDisplay);
		
		JButton homeButton = new JButton("Manage DB");
		chooseDataSourceView = new ChooseDataSourceView();
		springLayout.putConstraint(SpringLayout.NORTH, homeButton, 10, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, homeButton, 10, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, homeButton, -613, SpringLayout.EAST, getContentPane());
		homeButton.addActionListener(this);
		homeButton.setActionCommand("Manage DB");
		getContentPane().add(homeButton);
		
		JButton btnTest = new JButton("test");
		testPanel = new Test();
		springLayout.putConstraint(SpringLayout.NORTH, btnTest, 0, SpringLayout.NORTH, homeButton);
		springLayout.putConstraint(SpringLayout.WEST, btnTest, 32, SpringLayout.EAST, homeButton);
		btnTest.addActionListener(this);
		btnTest.setActionCommand("test");
		getContentPane().add(btnTest);
		
		setVisible(true);
		
	}
	
	/**
	 * Methode qui change le panel en fonction des actions
	 * @param panel
	 */
	public static void changePanel(JPanel panel){
		//remove ancient JPanel if exist
		panelDisplay.removeAll();
		panelDisplay.repaint();
		panelDisplay.revalidate();
		//add the new JPanel
		panelDisplay.add(panel);
		panelDisplay.repaint();
		panelDisplay.revalidate();
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		String cmd = e.getActionCommand();
		if(cmd.equals("Manage DB")){ 
			chooseDataSourceView = new ChooseDataSourceView();
			changePanel(chooseDataSourceView);
			setVisible(true);
		}
		if(cmd.equals("test")){ 
			testPanel = new Test();
			changePanel(testPanel);
			setVisible(true);
		}
		
	}

}