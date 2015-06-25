package ui;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import util.ManageCsv;
import util.ParserCsv;
import exception.FileException;

public class DisplayCsv extends JPanel {
	

    private static final long serialVersionUID = 7354456748439411793L;

    private TabView table;
    private JButton record;

    public DisplayCsv(String path){
        try {
            setLayout(new BorderLayout());
            List<String> line;
            line = ManageCsv.getInstance().readFile(path);
            List<Object[]> rows = ParserCsv.getInstance().parserCsv(line);
            Object[] labels = rows.remove(0);
            table = new TabView(labels, true);

            for(Object[] s : rows){
                table.addLine(s);
                
            }
            add(table.getTable().getTableHeader(), BorderLayout.NORTH);
            add(new JScrollPane(table), BorderLayout.CENTER);
            record = new JButton("Recording of modifications");
            record.addActionListener(new CheckListener(path, table.getTable()));
            add(record, BorderLayout.SOUTH);
            
        } 
        catch (FileException e) {
            e.printStackTrace();
        }
    }

}
