package ui;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class TabView extends JPanel {
	
	 private static final long serialVersionUID = -1572744239267031822L;
	    
	    private JTable table;

	    public TabView(final Object[] labels, final boolean editable){
	        setLayout(new GridLayout(1, 0));
	        Object[][] data = new Object[0][labels.length];
	        DefaultTableModel model = new DefaultTableModel(data, labels){
	            private static final long serialVersionUID = 8142475658077955728L;
	            public boolean isCellEditable(int rowIndex, int columnIndex){
	                return editable;
	            }
	        };
	        table=new JTable(model);
	        table.setOpaque(false);
	        table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	            public void valueChanged(ListSelectionEvent e)
	        	{
	        		if (! e.getValueIsAdjusting())
	        		{
	        			System.out.println(table.getValueAt(table.getSelectedRow(), 0).toString());
	        		}
	            }
	            /*    // do some actions here, for example
	                // print first column value from selected row
	            	System.out.println(table.getSelectedRow());
	                System.out.println(table.getValueAt(table.getSelectedRow(), 0).toString());
	            }*/
	        });
	        add(table);
	    }

	    public void add(){
	        ((DefaultTableModel)(table.getModel())).addRow(new String[table.getColumnCount()]);
	    }
	    
	    public void delete(){
	        ((DefaultTableModel)(table.getModel())).removeRow(table.getSelectedRow());
	    }
	    
	    public void addLine(Object[] data){
	        ((DefaultTableModel)(table.getModel())).addRow(data);
	    }

	    public JTable getTable() {
	        return table;
	    }


}
