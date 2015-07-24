package persist;

import core.DataSet;

public abstract class PersistKit {

	/* The instance of our kit */
	private static PersistKit persistKit = null;

	/* types of persists which are possible */
	public static final int CSV = 1;
	
    /* Method create which creates the class with the good type of persist */
	 //public abstract User createUser(); 
    /**
     * Method getInstance, return an instance of PersistKit based on the type of persist
     * @param type 
     * @return
     */
	
	/* Method generate which creates one class with the good type of persist */
    public abstract /*DataSet*/ void generateDataSet(); 
    
    public static PersistKit createKit(int type) {

        if (persistKit == null) {

            if (type == CSV) {
            	
                persistKit = new CSVKit();
            }  
        }

        return persistKit;
    }
}
