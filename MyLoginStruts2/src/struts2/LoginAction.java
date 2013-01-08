/**
 * 
 */
package struts2;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author ALFA403
 * 
 */
public class LoginAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -218343389796005612L;
	private String userName;
	private String password;

	//getters e setters ci devono essere (a volte sono costruiti in automatico)
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	public String execute() {//si chiama sempre execute(), che di default returna sempre "success"
		SqlSessionFactory sqlSessionFactory = null;
		InputStream inputStream = null;
		try {
			inputStream = Resources.getResourceAsStream("mybatis/config/mybatis-config.xml");
		} catch (IOException e) {
			e.printStackTrace();
			//log.fatal(metodo, "Fallita SqlSessionFactoryBuilder", e);
		}
		try {
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (Exception e) {
			e.printStackTrace();
			//log.fatal(metodo, "Fallita SqlSessionFactoryBuilder", e);
		}
		/*HashMap<String, Object> map = new HashMap<String,Object>();
		map.put("colName","USERNAME");
		map.put("table","UTENTI");
		map.put("colPw","PASSWORD");
		map.put("userName",getUserName());
		map.put("password", getPassword());*/
		SqlSession sql = sqlSessionFactory.openSession();
		int count=0;
		try {
			count = sql.selectOne("Users.count", this);
		} catch (Exception e) {
			e.printStackTrace();
			//log.error(metodo, request.getSession().getId(), e);
		} finally{
			sql.close();
		}
		if (count>0) {
			return SUCCESS;
		} else {
			addActionError(getText("error.login"));
			return ERROR;
		}
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ActionSupport#validate()
	 */
	@Override
	public void validate() {
		final int minPasswLenght = 8;
		if (getUserName().length() == 0) {
			addFieldError("userName", getText("requiredstring",new String[]{getText("label.username")}));
		} 
		if (getPassword().length() == 0) {
			addFieldError("password", getText("requiredstring",new String[]{getText("label.password")}));
		}else if (getPassword().length() < minPasswLenght) {
			addFieldError("password", getText("error.password",new String[]{minPasswLenght+" "+getText("error.password.long")}));
		}else{
			if (!getPassword().matches(".*\\d.*")) {
				addFieldError("password", getText("error.password",new String[]{getText("error.password.number")}));}
			if (!getPassword().matches(".*[A-Z].*")) {
			addFieldError("password", getText("error.password",new String[]{getText("error.password.upper")}));}
			if (!getPassword().matches(".*[a-z].*")) {
			addFieldError("password", getText("error.password",new String[]{getText("error.password.lower")}));}
		}
	}
}
