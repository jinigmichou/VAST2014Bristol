package ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class AssumptionView extends JPanel implements ActionListener {
	
	private MainView frame;
	private JTextField textFieldFilePath;

	private ArrayList<String[]> myFile;
	private JTextArea textArea;
	private JButton btnReturn; 

	public AssumptionView(MainView frame) {
		this.frame = frame;
		setBackground(Color.LIGHT_GRAY);
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);

		textArea = new JTextArea();

		JScrollPane scrollPane = new JScrollPane(textArea);
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, 542, SpringLayout.NORTH, this);
		add(scrollPane);
		
		btnReturn = new JButton("Return");
		springLayout.putConstraint(SpringLayout.NORTH, btnReturn, 10, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, btnReturn, -10, SpringLayout.EAST, this);
		btnReturn.addActionListener(this);
		btnReturn.setActionCommand("Return");
		add(btnReturn);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
