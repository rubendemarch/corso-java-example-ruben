/**
 * 
 */
package dbo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.MyLogger;
import dbo.connection.Connessione;

/**
 * @author ALFA403
 *
 */
public class RootDbo {
	protected Connessione connessione;
	//ovviamente nn sarà public
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

}
