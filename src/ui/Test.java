package ui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SpringLayout;

public class Test extends JPanel {

	/**
	 * Create the panel.
	 */
	public Test() {
		
		SpringLayout springLayout = new SpringLayout();
		//setLayout(springLayout);
		
		JLabel lblNewLabel = new JLabel("TESTTTETTETE");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel, 140, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.SOUTH, lblNewLabel, -144, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel, -176, SpringLayout.EAST, this);
		add(lblNewLabel);
		setVisible(true);

	}
}
