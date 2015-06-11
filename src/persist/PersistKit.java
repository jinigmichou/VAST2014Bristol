package persist;

public abstract class PersistKit {

	/* The instance of our kit */
	private static PersistKit persistKit = null;

	/* types of persists which are possible */
	public static final int CSV = 1;
	public static final int JDBC = 2;
	public static final int SERIALIZABLE = 3;
	public static final int XML = 4;
	
    /* Method create which creates the class with the good type of persist */
	 //public abstract User createUser(); 
    /**
     * Method getInstance, return an instance of PersistKit based on the type of persist
     * @param type of persist
     * @return kit of persist selected
     */
    
    public static PersistKit createKit(int type) {

        if (persistKit == null) {

            if (type == JDBC) {
            	
                persistKit = new JDBCKit();

            } else  if (type == SERIALIZABLE) {

                persistKit = new SerializableKit();

            } else  if (type == XML) {

                persistKit = new XMLKit();
            } 
        }

        return persistKit;
    }
}
