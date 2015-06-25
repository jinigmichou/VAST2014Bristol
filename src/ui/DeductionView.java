package ui;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class DeductionView extends JPanel {
	
	private MainView frame;
	private JTextField textFieldFilePath;

	private ArrayList<String[]> myFile;
	private JTextArea textArea;

	public DeductionView(MainView frame) {
		this.frame = frame;
		setBackground(Color.LIGHT_GRAY);
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);

		textArea = new JTextArea();

		JScrollPane scrollPane = new JScrollPane(textArea);
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, 542, SpringLayout.NORTH, this);
		add(scrollPane);
	}
}
