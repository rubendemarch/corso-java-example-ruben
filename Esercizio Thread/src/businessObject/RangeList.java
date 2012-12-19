package businessObject;

import java.util.ArrayList;
import java.util.List;


public class RangeList {

	private List<Range> rangeList;
	
	//ricerca in un solo insieme,false complessa(Tutto alfabeto, alfanumerico..)
	private boolean ricercaEasy = true;
	private String regEX = null;
	

	

	public RangeList(Range range, boolean easy, String pattern) {
		ricercaEasy = easy;
		regEX = pattern;
		rangeList = new ArrayList<Range>();
		rangeList.add(range);
	}
		
	public void addRange(Range range) {
		rangeList.add(range);
	}

	public List<Range> getRangeList() {
		return rangeList;
	}
	
	public boolean isRicercaEasy() {
		return ricercaEasy;
	}

	public String getRegEX() {
		return regEX;
	}
}
