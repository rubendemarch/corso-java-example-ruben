package dbo.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import configuration.MyProperties;
import exception.config.Config;

import util.MyLogger;

public class Connessione {
	private MyLogger logger;
	private Connection connection=null;//così lo inizializziamo
	private  String url = null;
	private  String dbName = null;
	private  String driver = null;
	private  String userName = null; 
	private  String password = null;
	
	
	/**
	 * @throws ReflectiveOperationException 
	 * @throws SQLException 
	 * il costruttore solleva l'eccezione 
	 * e ritorna il tipo intrinseco, cioè la classe stessa
	 * 
	 */
	 public Connessione(MyProperties mP) throws ReflectiveOperationException, SQLException {
		logger=new MyLogger(this.getClass());
		final String metodo="costruttore";
		logger.start(metodo);
		
		try {
			this.url = mP.getPropertyValue("url");
			this.dbName = mP.getPropertyValue("dbName");
			this.driver = mP.getPropertyValue("driver");
			this.userName =mP.getPropertyValue("userName");
			this.password =mP.getPropertyValue("password");
		} catch (Config e) {
			logger.fatal(metodo, "non leggo i dati", e);
			e.printStackTrace();
		}
		try {
			Class.forName(driver).newInstance();
		} catch (InstantiationException 
				| IllegalAccessException
				| ClassNotFoundException e) {
			logger.fatal(metodo, "tentativo recupero driver", e);
		//	e.printStackTrace(); tanto non stampiamo a schermo
			throw e;
		}
		
		try {
			connection=DriverManager.getConnection(url+dbName, userName, password);
			//prova ad aprire la connessione
		} catch (SQLException e) {
			logger.fatal(metodo,
					"tentativo connessione db",
					e);
			throw e;
		}
		
		logger.end(metodo);
	
	}

	 @Deprecated //ciò serve x farlo usare solo a noi quando serve
	 public void closeConnection(){
	//	logger=new MyLogger(this.getClass()); //tengo lo stesso logger di prima
		System.out.println("distruttore");
		final String metodo="closeConnection";
		logger.start(metodo);
		 if (connection!=null) {
			try {
				connection.close();
			} catch (SQLException e) {
				logger.warn(metodo,//warn perché ormai il programma ha finito, però è successo qualcosa
						"tentativo chiusura connessione db", e);
				e.printStackTrace();
			}
		}
		logger.end(metodo);
	 }
	 
	public void finalize(){
//		final String metodo="finalize";//questa stringa non verrà letta comunque
//		logger.start(metodo);//questa stringa non verrà letta comunque
		closeConnection();//nel finalize non viene letta quest'istruzione
//		logger.end(metodo);//questa stringa non verrà letta comunque
		
	}

	public void close(){//non sovrascrive nulla, Object non ha il metodo close()
		//l'abbiamo fatto giusto per scrivere il commento sopra
	}

	/**
	 * @return the connection
	 */
	public Connection getConnection() {
		return connection;
	}
	 
}
