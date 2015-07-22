package core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * This class allows to read flux and error flux during execution of a jar file using runtime.exec command 
 * @author jacquez
 *
 */
public class TerminalOutput extends Thread {  

	private InputStream flux;

	public TerminalOutput(InputStream flux){
		this.flux = flux;
	}

	@Override
	/**
	 * This method permits to display stream about jar execution
	 */
	public void run(){
		try {    
			InputStreamReader reader = new InputStreamReader(flux);
			BufferedReader br = new BufferedReader(reader);
			String ligne=null;
			while ( (ligne = br.readLine()) != null){
				System.out.println(ligne);
			}
		}
		catch (IOException ioe){
			ioe.printStackTrace();
		}
	}


	/**
	 * This method only allows to put stream into a file to display it into a textArea for example
	 * @param myResult
	 */
	public void run(ArrayList<String> myResult){
		try {    
			InputStreamReader reader = new InputStreamReader(flux);
			BufferedReader br = new BufferedReader(reader);
			String ligne=null;

			while ( (ligne = br.readLine()) != null){
				myResult.add(ligne);
			}
		}
		catch (IOException ioe){
			ioe.printStackTrace();
		}
	}
}