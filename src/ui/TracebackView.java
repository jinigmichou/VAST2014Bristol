package ui;

import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.UIManager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.filechooser.FileFilter;
import javax.swing.SpringLayout;

public class TracebackView extends JPanel implements ActionListener{


	private MainView frame;
	private JTextField textFieldFilePath;

	private ArrayList<String[]> myFile;
	private JTextArea textArea;

	public TracebackView(MainView frame) {

		this.frame = frame;
		setBackground(Color.LIGHT_GRAY);

		JButton btnTraceback = new JButton("Traceback");
		btnTraceback.addActionListener(this);
		btnTraceback.setActionCommand("Traceback");        
		        
		
		SpringLayout springLayout = new SpringLayout();
		springLayout.putConstraint(SpringLayout.NORTH, btnTraceback, 50, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, btnTraceback, 92, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, btnTraceback, 286, SpringLayout.WEST, this);
		setLayout(springLayout);
		
		add(btnTraceback);

		JButton btnDeductionsassumtions = new JButton("Deductions/Assumptions");
		springLayout.putConstraint(SpringLayout.SOUTH, btnDeductionsassumtions, 0, SpringLayout.SOUTH, btnTraceback);
		springLayout.putConstraint(SpringLayout.NORTH, btnDeductionsassumtions, 50, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, btnDeductionsassumtions, 384, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, btnDeductionsassumtions, 578, SpringLayout.WEST, this);
		btnDeductionsassumtions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		btnDeductionsassumtions.setActionCommand("Dependencies");
		add(btnDeductionsassumtions);

		JButton button = new JButton("Return");
		springLayout.putConstraint(SpringLayout.NORTH, button, 50, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, button, 635, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, button, 0, SpringLayout.SOUTH, btnTraceback);
		springLayout.putConstraint(SpringLayout.EAST, button, 740, SpringLayout.WEST, this);
		button.addActionListener(this);
		button.setActionCommand("Return");
		add(button);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String cmd = e.getActionCommand();
		if(cmd.equals("Return")){
			frame.changePanel(new HomeView(frame));
		}
		else if(cmd.equals("Traceback")){
			frame.applyLookAndFeel();
	        frame.chooseFile();
		}
	}
}
