/**
 * 
 */
package bussinessObject;

import bussinessObject.interfaces.ColumnDescriptorInterface;

/**
 * @author ALFA403
 *
 */
public class ColumnDescriptor implements ColumnDescriptorInterface {

	private final String columnName;
	private final int dbSize;
	
	//per fare righe di lunghezza fissa:
	private final int fileSize;
	private final boolean isLeftAlign;
	private final char padChar; //<---il carattere da inserire per riempire la riga
	
	//per CVS, i(l) caratter(e)i di separazione:
	private final String separetor;
	
	private final String pattern;
	
	/**
	 * @param columnName
	 * @param dbSize
	 * @param fileSize
	 * @param isLeftAlign
	 * @param padChar
	 * @param separetor
	 * @param pattern
	 */
	public ColumnDescriptor(String columnName, int dbSize, int fileSize,
			boolean isLeftAlign, char padChar, String separetor, String pattern) {
		super();
		this.columnName = columnName;
		this.dbSize = dbSize;
		this.fileSize = fileSize;
		this.isLeftAlign = isLeftAlign;
		this.padChar = padChar;
		this.separetor = separetor;
		this.pattern = pattern;
	}


	public String getColumnName() {
		return columnName;
	}


	public int getDbSize() {
		return dbSize;
	}

	public int getFileSize() {
		return fileSize;
	}


	public boolean isLeftAlign() {
		return isLeftAlign;
	}

	public char getPadChar() {
		return padChar;
	}


	public String getSeparetor() {
		return separetor;
	}


	public String getPattern() {
		return pattern;
	}



}
