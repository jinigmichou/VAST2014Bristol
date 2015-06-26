package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import util.ManageCsv;

public class CheckListener implements ActionListener{

	private JTable table;
	private String path;
	private MainView frame;
	
	

	public CheckListener(String path, JTable table) {
		this.table = table;
		this.path=path;
	}
	

	public void actionPerformed(ActionEvent arg0) {
		try{
			List<String> lines = new ArrayList<String>();
			StringBuilder sb0 = new StringBuilder();
			for(int i=0; i<table.getTableHeader().getColumnModel().getColumnCount()-1; i++){
				sb0.append(table.getTableHeader().getColumnModel().getColumn(i).getHeaderValue()).append(";");
			}
			sb0.append(table.getTableHeader().getColumnModel().getColumn(table.getTableHeader().getColumnModel().getColumnCount()-1).getHeaderValue());
			lines.add(sb0.toString());
			for(int i=0; i<table.getRowCount(); i++){
				StringBuilder sb = new StringBuilder();
				for(int j=0; j<table.getColumnCount()-1; j++){
					sb.append(table.getValueAt(i, j)).append(";");
				}
				sb.append(table.getValueAt(i, table.getColumnCount()-1));
				lines.add(sb.toString());

			}
			ManageCsv.getInstance().writeFile(path, lines);
			JOptionPane.showMessageDialog(frame, "Modifications recorded", "Recording", JOptionPane.INFORMATION_MESSAGE);

		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, "Recording failed", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

}
