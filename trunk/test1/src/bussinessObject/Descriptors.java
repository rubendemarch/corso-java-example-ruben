/**
 * 
 */
package bussinessObject;

import java.util.ArrayList;
import java.util.List;

import bussinessObject.interfaces.ColumnDescriptorInterface;
import bussinessObject.interfaces.DescriptorsInterface;

/**
 * @author ALFA403
 *
 */
public class Descriptors implements DescriptorsInterface {

	private List<ColumnDescriptorInterface> descriptors;
	/**
	 * 
	 */
	public Descriptors() {
		this.descriptors = new ArrayList<ColumnDescriptorInterface>();
	}

	public void addColumnDescriptor(ColumnDescriptorInterface columnDescriptorInterface){
		this.descriptors.add(columnDescriptorInterface);
	}

	/**
	 * @return the descriptors
	 */
	public List<ColumnDescriptorInterface> getDescriptors() {
		return descriptors;
	}
}
