/**
 * 
 */
package struts2;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import util.log.MyLogger;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author ALFA403
 * 
 */
public class ListAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8251215163029958542L;
	private MyLogger log;
	private ArrayList<User> table;
	
	/**
	 * @return the table
	 */
	public ArrayList<User> getTable() {
		return table;
	}

	/**
	 * @param table the table to set
	 */
	public void setTable(ArrayList<User> table) {
		this.table = table;
	}

	public String execute() {
		String metodo="execute";
		log=new MyLogger(getClass());
		log.start(metodo);
		String ret=SUCCESS;
		SqlSessionFactory sqlSessionFactory = null;
		InputStream inputStream = null;
		try {
			inputStream = Resources.getResourceAsStream("mybatis/config/mybatis-config.xml");
		} catch (IOException e) {
			log.fatal(metodo, "Fallita SqlSessionFactoryBuilder", e);
			addActionError(getText("error.database"));
			return ERROR;
		}
		try {
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (Exception e) {
			log.fatal(metodo, "Fallita SqlSessionFactoryBuilder", e);
			addActionError(getText("error.database"));
			return ERROR;
		}
		SqlSession sql = sqlSessionFactory.openSession();
		setTable((ArrayList<User>)(ArrayList<?>)sql.selectList("Users.list"));
		sql.close();
		log.end(metodo);
		return ret;
	}
}
