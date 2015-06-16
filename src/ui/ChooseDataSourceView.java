package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.Color;
import java.util.logging.Level;

public class ChooseDataSourceView extends JPanel implements ActionListener{

	private HomeView homeView;
	private Test testPanel;
	private static JPanel panelDisplay;
	private MainView mainView;

	public ChooseDataSourceView() {
		SpringLayout springLayout = new SpringLayout();

		JLabel lblChooseDataSource = new JLabel("Choose Data Source");
		springLayout.putConstraint(SpringLayout.NORTH, lblChooseDataSource, 10, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblChooseDataSource, 153, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, lblChooseDataSource, 26, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, lblChooseDataSource, 287, SpringLayout.WEST, this);
		add(lblChooseDataSource);

		JButton csv = new JButton("CSV");
		springLayout.putConstraint(SpringLayout.NORTH, csv, 65, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, csv, -300, SpringLayout.EAST, this);
		csv.addActionListener(this);
		csv.setActionCommand("CSV");
		add(csv);
		
		JButton sql = new JButton("SQL");

		springLayout.putConstraint(SpringLayout.NORTH, sql, 0, SpringLayout.NORTH, csv);
		springLayout.putConstraint(SpringLayout.WEST, sql, 16, SpringLayout.EAST, csv);
		springLayout.putConstraint(SpringLayout.EAST, sql, -4, SpringLayout.EAST, lblChooseDataSource);
		add(sql);

		JButton xml = new JButton("XML");
		xml.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, xml, 65, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, xml, 16, SpringLayout.EAST, xml);
		add(xml);
	}

	/**
	 * Methode qui change le panel en fonction des actions
	 * @param panel
	 */

	@Override
	public void actionPerformed(ActionEvent e) {

		String cmd = e.getActionCommand();
		if(cmd.equals("CSV")){ 
			MainView.logger.log(Level.INFO, "Click on the buttom CSV, choice of CSV database");
			homeView = new HomeView();
			MainView.changePanel(homeView);
			setVisible(true);
		}
		if(cmd.equals("SQL")){ 
			MainView.logger.log(Level.INFO, "Click on the buttom SQL, choice of SQL database");
			testPanel = new Test();
			MainView.changePanel(testPanel);
			setVisible(true);
		}
		if(cmd.equals("XML")){
			MainView.logger.log(Level.INFO, "Click on the buttom XML, choice of XML database");
			testPanel = new Test();
			MainView.changePanel(testPanel);
			setVisible(true);
		}
	}
}