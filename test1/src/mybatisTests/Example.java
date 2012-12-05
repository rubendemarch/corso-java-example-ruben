/**
 * 
 */
package mybatisTests;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * @author ALFA403
 *
 */
public class Example {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String resource = "config/mybatis-config.xml";
		InputStream inputStream = null;
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SqlSessionFactory sqlSessionFactory = null;
		try {
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	SqlSession sql = sqlSessionFactory.openSession();
	List<HashMap<String, Object>> list=null;
	try {
		//SUper trucco:
		list =(List<HashMap<String, Object>>)(List<?>)sql.selectList("language.languageList");
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	sql.close();//fa qualcosa di utile???
	System.out.println("fine");
	}

}
