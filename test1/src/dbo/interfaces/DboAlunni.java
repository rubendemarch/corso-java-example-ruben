/**
 * 
 */
package dbo.interfaces;

import java.util.HashMap;
import java.util.List;

import bussinessObject.Alunno;

/**
 * @author ALFA403
 *
 */
public interface DboAlunni {
	/*
	 * 
	 * @return restituisce tutti gli alunni presenti nella tabella alunni
	 */
	public List<Alunno>readAlunni();
	
	/*
	 * 
	 * @return restituisce tutti gli alunni presenti nella tabella alunni
	 */
	public List<HashMap<String, Object>>dynamicReadAlunni();

}
