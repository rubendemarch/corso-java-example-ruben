package bussinessObject;

public enum Sesso {
	M("M"), F("F");
	/**
	 * i seguenti sono sottoattributi x ogni campo del vettore enum, x questo non sono public,private,...
	 * vengono richiamati di fianco agli elementi di enum ("M","F",....)
	 */
	String label;
	private Sesso(String label){
		this.label=label;
	}
	/**
	 * @return the val
	 */
	public String getLabelSesso() {
		return label;
	}
	
}