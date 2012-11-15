/**
 * 
 */
package bussinessObject.interfaces;

/**
 * @author ALFA403
 *
 */
public interface ColumnDescriptorInterface {
	/**
	 * @return the columnName
	 */
	public String getColumnName();

	/**
	 * @return the dbSize
	 */
	public int getDbSize();

	/**
	 * @return the fileSize
	 */
	public int getFileSize();

	/**
	 * @return the isLeftAlign
	 */
	public boolean isLeftAlign() ;

	/**
	 * @return the padChar
	 */
	public char getPadChar();

	/**
	 * @return the separetor
	 */
	public String getSeparetor();

	/**
	 * @return the pattern
	 */
	public String getPattern();

}
