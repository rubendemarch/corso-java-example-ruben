/**
 * 
 */
package dbo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import bussinessObject.interfaces.ColumnDescriptorInterface;
import bussinessObject.interfaces.DescriptorsInterface;

import util.MyLogger;
import dbo.connection.Connessione;

/**
 * @author ALFA403
 *
 */
public class RootDbo {
	protected Connessione connessione;
	//ovviamente nn sar� public
	private MyLogger logger;
	/**
	 * @param connessione
	 * in questo modo abbiamo un'unica connessione capo
	 *  che possiamo spegnere e accendere
	 *   e passare a tutti i dbo
	 */
	public RootDbo(Connessione connessione) {
		logger=new MyLogger(this.getClass());
		final String metodo="costruttore";
		logger.start(metodo);
		this.connessione = connessione;
		logger.end(metodo);
	}

	protected void close(PreparedStatement ps, ResultSet rs){
		final String metodo="close";
		logger.start(metodo);
		
		if (rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				logger.warn(metodo, "ResulSet.close", e);
				e.printStackTrace();
			}
		}
		if (ps!=null) {
			try {
				ps.close();
			} catch (SQLException e) {
				logger.warn(metodo, "PreparedStatement.close", e);
				e.printStackTrace();
			}
		}
		logger.end(metodo);
	}
	
	/**
	 * @param sql
	 * @return
	 * @throws SQLException
	 */
	protected PreparedStatement getPreparedStatement(String sql) throws SQLException {
		final String metodo="getPreparedStatement";
		try {
					return connessione
							.getConnection()
								.prepareStatement(sql);
				} catch (SQLException e) {
					logger.error(metodo, "creo PreparedStatement", e);
					throw e;
				}
	}
	
	/**
	 * @param ps
	 * @return
	 * @throws SQLException
	 */
	protected ResultSet executeQuery(PreparedStatement ps) throws SQLException {
		final String metodo="executeQuery";
		try {
				return ps.executeQuery();
			} catch (SQLException e) {
				logger.error(metodo, "eseguo query", e);
				throw e;
		}
	}
	
	public List<HashMap<String, Object>> dynamicRead(
			DescriptorsInterface di,
			String tables,
			String whereCond,
			String orderByCond,
			String groupByCond){
		final String metodo="dynamicRead";
		logger.start(metodo);
		StringBuilder sql=new StringBuilder("select ");
		for(ColumnDescriptorInterface cdi : di.getDescriptors()){
			sql.append(cdi.getColumnName()).append(",");
		}
		sql=sql.deleteCharAt(sql.length()-1);
		sql.append(" from ").append(tables)
			.append(whereCond)
			//TODO mancano gli spazi
			.append(orderByCond)
			.append(groupByCond);
		PreparedStatement ps=null;
		ResultSet rs=null;
		ArrayList<HashMap<String, Object>> res=null;
		try {
			ps = getPreparedStatement(sql.toString());
			rs = executeQuery(ps);
			res = new ArrayList<HashMap<String, Object>>();
			if(rs!=null){
				try {
					while (rs.next()){
						HashMap<String, Object> row=new HashMap<String, Object>();
						for (ColumnDescriptorInterface cdi  : di.getDescriptors()){
							row.put(cdi.getColumnName(), rs.getObject(cdi.getColumnName()));
						}
						res.add(row);
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
		return res;
	}


}