package enumeratori;

public enum Numeri {

	ZERO("0"),UNO("1"),DUE("2"),TRE("3"),QUATTRO("4"),
	CINQUE("5"),SEI("6"),SETTE("7"),OTTO("8"),NOVE("9");
	
	private String value;
	
	private Numeri(String val)
	{
		value = val;
	}

	
	
	
	public String getValue() {
		return value;
	}
	
	
}
