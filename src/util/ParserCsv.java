package util;

import java.util.ArrayList;
import java.util.List;

public class ParserCsv {
	
	 private static final ParserCsv instance = new ParserCsv();

	    private final String SEPARATEUR =";";

	    private ParserCsv(){
	        super();
	    }

	    public List<Object[]> parserCsv(List<String> lines){
	        List<Object[]> res = new ArrayList<Object[]>();
	        for(String s : lines){
	            res.add(s.split(SEPARATEUR));
	        }
	        return res;
	    }

	    public static ParserCsv getInstance() {
	        return instance;
	    }

}
