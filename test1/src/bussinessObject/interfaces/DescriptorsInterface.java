/**
 * 
 */
package bussinessObject.interfaces;

import java.util.List;

/**
 * @author ALFA403
 *
 */
public interface DescriptorsInterface {
	public void addColumnDescriptor(ColumnDescriptorInterface columnDescriptorInterface);
	public List<ColumnDescriptorInterface> getDescriptors();

}
