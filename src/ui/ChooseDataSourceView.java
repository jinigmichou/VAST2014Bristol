package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.Color;
import java.util.logging.Level;
/**
 * This view allows to choice initial data format (Csv, Xml, ...)
 * @author jacquez
 *
 */
public class ChooseDataSourceView extends JPanel implements ActionListener{

	private static JPanel panelDisplay;
	private MainView frame;

	public ChooseDataSourceView(MainView frame) {

		this.frame = frame;
		setBackground(Color.LIGHT_GRAY);
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		setSize(640, 480);

		JLabel lblChooseDataSource = new JLabel("Choose Data Source");
		springLayout.putConstraint(SpringLayout.NORTH, lblChooseDataSource, 31, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblChooseDataSource, 90, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, lblChooseDataSource, 47, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, lblChooseDataSource, -180, SpringLayout.EAST, this);
		add(lblChooseDataSource);

		JButton csv = new JButton("CSV");
		springLayout.putConstraint(SpringLayout.NORTH, csv, 21, SpringLayout.SOUTH, lblChooseDataSource);
		springLayout.putConstraint(SpringLayout.WEST, csv, 0, SpringLayout.WEST, lblChooseDataSource);
		springLayout.putConstraint(SpringLayout.SOUTH, csv, 50, SpringLayout.SOUTH, lblChooseDataSource);
		springLayout.putConstraint(SpringLayout.EAST, csv, 164, SpringLayout.WEST, lblChooseDataSource);
		csv.addActionListener(this);
		csv.setActionCommand("CSV");
		add(csv);

		JButton sql = new JButton("SQL");
		springLayout.putConstraint(SpringLayout.WEST, sql, 90, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, sql, 254, SpringLayout.WEST, this);
		add(sql);

		JButton xml = new JButton("XML");
		springLayout.putConstraint(SpringLayout.NORTH, sql, 3, SpringLayout.SOUTH, xml);
		springLayout.putConstraint(SpringLayout.SOUTH, sql, 32, SpringLayout.SOUTH, xml);
		springLayout.putConstraint(SpringLayout.NORTH, xml, 6, SpringLayout.SOUTH, csv);
		springLayout.putConstraint(SpringLayout.SOUTH, xml, 35, SpringLayout.SOUTH, csv);
		springLayout.putConstraint(SpringLayout.EAST, xml, 254, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.WEST, xml, 90, SpringLayout.WEST, this);
		xml.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		add(xml);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String cmd = e.getActionCommand();
		if(cmd.equals("CSV")){ 
			MainView.logger.log(Level.WARNING, "Click on the buttom CSV, choice of CSV database");
			this.frame.changePanel(new HomeView(frame));
		}
		else if(cmd.equals("SQL")){ 
			MainView.logger.log(Level.WARNING, "Click on the buttom SQL, choice of SQL database");
		}
		else if(cmd.equals("XML")){
			MainView.logger.log(Level.WARNING, "Click on the buttom XML, choice of XML database");
		}
	}
}