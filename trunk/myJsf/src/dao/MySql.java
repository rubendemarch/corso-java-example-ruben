/**
 * 
 */
package dao;

import java.io.IOException;
import java.io.InputStream;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * @author ALFA403
 *
 */
@ManagedBean(eager=true,name="mySql")
@ApplicationScoped
public class MySql {

	private static SqlSessionFactory sqlSessionFactory;

	static{
		String resource = "mybatis-config.xml";
		InputStream inputStream=null;
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(inputStream!=null){
			try {
				sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @return the sqlSessionFactory
	 */
	public static SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

	/**
	 * @param sqlSessionFactory the sqlSessionFactory to set
	 */
	public static void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		MySql.sqlSessionFactory = sqlSessionFactory;
	}
}
