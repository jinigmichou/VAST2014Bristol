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
		setBackground(Color.LIGHT_GRAY);
		
		
		setLayout(null);
		
		JLabel lblChooseDataSource = new JLabel("Choose Data Source");
		lblChooseDataSource.setBounds(90, 33, 180, 16);
		
		add(lblChooseDataSource);

		JButton csv = new JButton("CSV");
		csv.setBounds(90, 70, 180, 16);
		csv.addActionListener(this);
		csv.setActionCommand("CSV");
		add(csv);
		
		JButton sql = new JButton("SQL");
		sql.setBounds(90, 98, 180, 16);
		add(sql);

		JButton xml = new JButton("XML");
		xml.setBounds(90, 130, 180, 16);
		xml.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
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
			MainView frame = new MainView(0);
			frame.setContentPane(new HomeView());
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