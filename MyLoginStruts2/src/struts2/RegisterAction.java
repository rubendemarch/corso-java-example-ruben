/**
 * 
 */
package struts2;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.TransactionIsolationLevel;

import util.log.MyLogger;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author ALFA403
 * 
 */
public class RegisterAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8807034165591599215L;
	private String userName;
	private String password;
	private String passwordConfirm;
	private MyLogger log;
	
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

	/**
	 * @return the passwordConfirm
	 */
	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	/**
	 * @param passwordConfirm the passwordConfirm to set
	 */
	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public String getMd5password(){// quando mybatis cercherà 'md5password' gliela darà questo metodo detto 'finto getter'
		return DigestUtils.md5Hex(password);
	}
	
	public String execute() {//si chiama sempre execute(), che di default returna sempre "success"
		String metodo="execute";
		log=new MyLogger(getClass());
		log.start(metodo);
		SqlSessionFactory sqlSessionFactory = null;
		InputStream inputStream = null;
		try {
			inputStream = Resources.getResourceAsStream("mybatis/config/mybatis-config.xml");
		} catch (IOException e) {
			log.fatal(metodo, "Fallita SqlSessionFactoryBuilder", e);
		}
		try {
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (Exception e) {
			log.fatal(metodo, "Fallita SqlSessionFactoryBuilder", e);
		}
		SqlSession sql = sqlSessionFactory.openSession();
		int count=0;
		try {
			count = sql.selectOne("Users.count", this);
		} catch (Exception e) {
			log.error(metodo, sqlSessionFactory.toString(), e);
		} finally{
			sql.close();
		}
		
		if (count==0) {
			sql = sqlSessionFactory.openSession(TransactionIsolationLevel.READ_COMMITTED);
			int rowsAffected =0;
			try {
				rowsAffected = sql.insert("Users.add", this);
				sql.commit();
			} catch (Exception e) {
				log.error(metodo, sqlSessionFactory.toString(), e);
				sql.rollback();
			} finally{
				sql.close();
			}
			if(rowsAffected>0){
				log.info(metodo, "NEW USER");
				log.info("Username", userName);
				log.info("Password", password);
				log.end(metodo+" - "+SUCCESS);
				return SUCCESS;
			}else{
				addActionError(getText("error.database"));
				log.end(metodo+" - "+ERROR);
				return ERROR;
			}
		} else {
			addActionError(getText("error.register"));
			log.end(metodo+" - "+ERROR);
			return ERROR;
		}
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ActionSupport#validate()
	 */
	@Override
	public void validate() {
		String metodo="validate";
		log=new MyLogger(getClass());
		log.start(metodo);
		int minPasswLenght = 8;
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
		}if (!getPassword().equals(getPasswordConfirm())) {
			addActionError(getText("error.password.notConfirm"));}
		log.end(metodo);
	}
}
