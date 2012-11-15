/**
 * 
 */
package enums;


/**
 * @author ALFA403
 * 
 * anziché enum si potrebbe usare xml per le properties, ma così è meglio xk funge lato java
 * è come un file di configurazione
 *
 *è indifferente maiusc o minusc quando leggiamo le colonne dal db
 *
 *l'ordine dei values dell'enum sarà l'ordine di scrittura delle colonne
 */

public enum Alunno {
	USER_ID(			"USER_ID", 				36, 50, true, ' ', ";",""),
	NOME(				"nome", 				35, 50, true, ' ', ";",""),
	COGNOME(			"cognome", 				35, 50, true, ' ', ";",""),
	DATA_NASCITA(		"DATA_NASCITA", 		0, 50, true, ' ', ";","YYYYMMdd"),//0 perché non è una stringa ma calendar
	SESSO(				"SESSO", 				1, 1, true, ' ', ";",""),
	CF(					"CF", 					16, 16, true, ' ', ";",""),
	STATO_NASCITA(		"STATO_NASCITA", 		35, 50, true, ' ', ";",""),
	COD_STATO_NASCITA(	"COD_STATO_NASCITA", 	 4, 50, true, ' ', ";",""),
	COMUNE_NASCITA(		"COMUNE_NASCITA", 		35, 50, true, ' ', ";",""),
	COD_COMUNE_NASCITA(	"COD_COMUNE_NASCITA", 	 4, 50, true, ' ', ";",""),
	;
	
	private final String columnName;
	private final int dbSize;
	
	//per fare righe di lunghezza fissa:
	private final int fileSize;
	private final boolean isLeftAlign;
	private final char padChar; //<---il carattere da inserire per riempire la riga
	
	//per CVS, i(l) caratter(e)i di separazione:
	private final String separetor;
	
//	private final Class clazz; //non ci serve sapere di che classe è il valore, tanto ce la dice lui
	
	private final String pattern;
	
	
	/**
	 * 
	 * @param columnName
	 * @param dbSize
	 * @param fileSize
	 * @param isLeftAlign
	 * @param padChar
	 * @param separetor
	 * @param clazz
	 */
	 
	private Alunno(String columnName, int dbSize, int fileSize,
			boolean isLeftAlign, char padChar, String separetor, String pattern) {
		this.columnName = columnName;
		this.dbSize = dbSize;
		this.fileSize = fileSize;
		this.isLeftAlign = isLeftAlign;
		this.padChar = padChar;
		this.separetor = separetor;
		this.pattern = pattern;
	}
	/**
	 * @return the columnName
	 */
	public String getColumnName() {
		return columnName;
	}
	/**
	 * @return the dbSize
	 */
	public int getDbSize() {
		return dbSize;
	}
	/**
	 * @return the fileSize
	 */
	public int getFileSize() {
		return fileSize;
	}
	/**
	 * @return the isLeftAlign
	 */
	public boolean isLeftAlign() {
		return isLeftAlign;
	}
	/**
	 * @return the padChar
	 */
	public char getPadChar() {
		return padChar;
	}
	/**
	 * @return the separetor
	 */
	public String getSeparetor() {
		return separetor;
	}
	/**
	 * @return the clazz
	 */
//	public Class getClazz() {
//		return clazz;
//	}
//	
	/**
	 * @return the pattern
	 */
	public String getPattern() {
		return pattern;
	}
	
}
