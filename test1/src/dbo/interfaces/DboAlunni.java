/**
 * 
 */
package dbo.interfaces;

import java.util.HashMap;
import java.util.List;

import bussinessObject.Alunno;
import bussinessObject.interfaces.DescriptorsInterface;

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
	
	/*
	 * 
	 * @return restituisce tutti gli alunni presenti nella tabella alunni
	 */
	public List<HashMap<String, Object>>dynamicReadAlunni(DescriptorsInterface di);
	
	/*
	 * 
	 * @return il numero di alunni inseriti
	 */
	public int dynamicInsertAlunno(DescriptorsInterface di, HashMap<String, Object> alunno);
	/*
	 * 
	 * @return il numero di alunni aggiornati
	 */
	public int dynamicUpdateAlunno(DescriptorsInterface di, HashMap<String, Object> alunno);
}


