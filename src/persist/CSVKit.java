package persist;

import core.DataSet;

public class CSVKit extends PersistKit {

	/**
	 * Method CSVKit
	 * @return a data set of type selected
	 */

	@Override
	public DataSet generateDataSet() {

		DataSet dataset = new DataSetJDBC();
		return dataset;

	}

}
