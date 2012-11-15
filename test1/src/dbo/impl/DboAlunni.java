/**
 * 
 */
package dbo.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import util.MyLogger;
import util.dbo.Convert;

import bussinessObject.Alunno;
import bussinessObject.TitoloDiStudio;
import dbo.RootDbo;
import dbo.connection.Connessione;

/**
 * @author ALFA403
 *
 */
public class DboAlunni
	extends RootDbo 
	implements dbo.interfaces.DboAlunni {
	private MyLogger logger;
	/**
	 * @param connessione
	 */
	public DboAlunni(Connessione connessione) {
		super(connessione);
		logger=new MyLogger(this.getClass());
		final String metodo="costruttore";
		logger.start(metodo);
		logger.end(metodo);
	}

	/* (non-Javadoc)
	 * @see dbo.interfaces.DboAlunniInterface#readAlunni()
	 */
	@Override
	public List<Alunno> readAlunni(){
		final String metodo="readAlunni";
		logger.start(metodo);
		StringBuilder sql=
				/*SELECT USER_ID,NOME,COGNOME,DATA_NASCITA,SESSO,CF,STATO_NASCITA,COD_STATO_NASCITA,
COMUNE_NASCITA,COD_COMUNE_NASCITA,E_MAIL,DATA_ISCRIZIONE,TITOLO_DI_STUDIO,TELEFONO
FROM ALUNNI order by cognome asc*/
				new StringBuilder("select ")//imp lo spazio dopo select
					.append("USER_ID,NOME,COGNOME,DATA_NASCITA,SESSO,CF,STATO_NASCITA,COD_STATO_NASCITA,")
					.append("COMUNE_NASCITA,COD_COMUNE_NASCITA,E_MAIL,DATA_ISCRIZIONE,TITOLO_DI_STUDIO ")
					.append("from alunni ")
					.append("")//where.. cioè la condizione, ma qui non la richiediamo (vedi non-Java-doc)
					.append("order by cognome asc");//asc o disc per ascendente o...
		PreparedStatement ps=null;
		ResultSet rs=null;
		ArrayList<Alunno> alunnoList=null;
		try {
			/**
			 * questi sono metodi del padre, così nn li deve richiamare qui ogni volta
			 * in questo modo possiamo generalizzarli, centralizzandoli al padre
			 * ciò è più comodo x debug, mantenimento,...
			 */
			ps = getPreparedStatement(sql.toString());
			rs = executeQuery(ps);
			alunnoList = new ArrayList<Alunno>();
			if(rs!=null){
				try {
					while (rs.next()){
						alunnoList.add(
							new Alunno(rs.getString("user_id"),
									rs.getString("nome"), 
									rs.getString("cognome"),
									Convert.convert(rs.getDate("data_Nascita")),
									Convert.convert(rs.getString("sesso")),
									rs.getString("cf"),
									rs.getString("stato_Nascita"),
									rs.getString("cod_Stato_Nascita"),
									rs.getString("comune_Nascita"),
									rs.getString("cod_Comune_Nascita"),
									rs.getString("e_mail"),
									Convert.convert(rs.getDate("data_Iscrizione")),
									new TitoloDiStudio(rs.getString("titolo_Di_Studio"))));
					}
				} catch (SQLException e) {
					logger.error(metodo, "scorro risultato", e);
					throw e;
				} catch (Exception e) {
					logger.error(metodo, "COSTRUZIONE ALUNNO", e);
					throw e;
				}
			}
		} catch (SQLException e) {
			logger.error(metodo, "", e);//il primo try e questo catch servono solo x far eseguire sempre il seguente finally
		} finally{
			close(ps, rs);
		}
		
		logger.end(metodo);
		return alunnoList;
	}



	public List<HashMap<String, Object>> dynamicReadAlunni(){
		final String metodo="readAlunni";
		logger.start(metodo);
		StringBuilder sql=new StringBuilder("select ");
		for(enums.Alunno a : enums.Alunno.values()){
			sql.append(a.getColumnName()).append(",");
		}
		//devo cancellare l'ultima virgola
//		sql=sql.deleteCharAt(sql.lastIndexOf(","));//oppure:
		sql=sql.deleteCharAt(sql.length()-1);
		
		sql.append(" from alunni ").append("").append("order by cognome asc");
		PreparedStatement ps=null;
		ResultSet rs=null;
		ArrayList<HashMap<String, Object>> alunnoList=null;
		try {
			ps = getPreparedStatement(sql.toString());
			rs = executeQuery(ps);
			alunnoList = new ArrayList<HashMap<String, Object>>();
			if(rs!=null){
				try {
					while (rs.next()){
						HashMap<String, Object> alunno=new HashMap<String, Object>();
						for (enums.Alunno a:enums.Alunno.values()){
							alunno.put(a.getColumnName(), Convert.convert(a, rs));
						}
						alunnoList.add(
								alunno
//							new Alunno(rs.getString("user_id"),
//									rs.getString("nome"), 
//									rs.getString("cognome"),
//									Convert.convert(rs.getDate("data_Nascita")),
//									Convert.convert(rs.getString("sesso")),
//									rs.getString("cf"),
//									rs.getString("stato_Nascita"),
//									rs.getString("cod_Stato_Nascita"),
//									rs.getString("comune_Nascita"),
//									rs.getString("cod_Comune_Nascita"),
//									rs.getString("e_mail"),
//									Convert.convert(rs.getDate("data_Iscrizione")),
//									new TitoloDiStudio(rs.getString("titolo_Di_Studio")))
							);
					}
				} catch (SQLException e) {
					logger.error(metodo, "scorro risultato", e);
					throw e;
				} catch (Exception e) {
					logger.error(metodo, "COSTRUZIONE ALUNNO", e);
					throw e;
				}
			}
		} catch (SQLException e) {
			logger.error(metodo, "", e);//il primo try e questo catch servono solo x far eseguire sempre il seguente finally
		} finally{
			close(ps, rs);
		}
		
		logger.end(metodo);
		return alunnoList;
	}


}

