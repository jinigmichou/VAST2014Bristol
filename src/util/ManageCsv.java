package util;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import exception.FileException;

public class ManageCsv extends JPanel {
	
private static final long serialVersionUID = 7354456748439411793L;
	
/**
 * Unique instance of singleton
 */
private static final ManageCsv instance = new ManageCsv();

/**
 * Access Method of ManageCsv
 * @return instance of ManageCsv
 */
public static final ManageCsv getInstance(){
    return instance;
}

/**
 * Constructor private of singleton
 */
private ManageCsv(){
    super();
}

/**
 * read file
 * @param nameFile
 *     nameFile in input
 * @return List of lines
 */
public List<String> readFile(String nameFile) throws FileException{
    BufferedReader inputStream=null;
    String lineRead;
    List<String> lines = new ArrayList<String>();
    try{
        inputStream = new BufferedReader(new FileReader(nameFile));
        lineRead = inputStream.readLine();
        while(lineRead!=null){
            lines.add(lineRead);
            lineRead = inputStream.readLine();
        }
    }
    catch(IOException exc){
        throw new FileException("Impossible to read into the file "+nameFile, exc);
    }
    try{
        inputStream.close();
    }
    catch(IOException e){
        throw new FileException("Impossible to read into the file  "+nameFile, e);
    }
    return lines;
}

public void writeFile(String nameFile, List<String> lines) throws FileException {
    PrintWriter out=null;
    try{
        out = new PrintWriter(new BufferedWriter(new FileWriter(nameFile)));
        for(String s : lines){
            out.println(s);
        }
    }
    catch(IOException exc){
        throw new FileException("Impossible to write into the file "+nameFile, exc);
    }
    out.close();
}

}
