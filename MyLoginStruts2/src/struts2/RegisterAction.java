/**
 * 
 */
package struts2;

import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

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
	private MyLogger log;
	private String userName;
	private String password;
	private String passwordConfirm;
	private Date birthDate;
	private String name;
	private String surname;
	private String email;
	private String phone;
	private String mobilePhone;
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

	/**
	 * @return the birthDate
	 */
	public Date getBirthDate() {
		return birthDate;
	}

	/**
	 * @param birthDate the birthDate to set
	 */
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * @param surname the surname to set
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * @return the eMail
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param eMail the eMail to set
	 */
	public void setEmail(String eMail) {
		this.email = eMail;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the mobilePhone
	 */
	public String getMobilePhone() {
		return mobilePhone;
	}

	/**
	 * @param mobilePhone the mobilePhone to set
	 */
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	/**
	 * @return the idRole
	 */
	public String getIdRole() {
		return "idRole";
	}

	public String getMd5password(){// quando mybatis cercherà 'md5password' gliela darà questo metodo detto 'finto getter'
		return DigestUtils.md5Hex(password);
	}

	public Date getRegisterDate(){
		return new Date();
	}
	
	public String getIdUser() {
		long now = Calendar.getInstance().getTimeInMillis();
		return now + "" + new Random(now).nextInt(1000);
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
		System.out.println(getBirthDate());
		System.out.println(new Date());
		System.out.println(getBirthDate().compareTo(new Date()));
		if (getName().length() == 0) {
			addFieldError("name", getText("requiredstring",new String[]{getText("label.name")}));}
		if (getSurname().length() == 0) {
			addFieldError("surname", getText("requiredstring",new String[]{getText("label.surname")}));} 
		if (!getEmail().matches("^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,4})$")) {
			addFieldError("email", getText("error.email"));}
		/*Calendar date = Calendar.getInstance();
		date.setTimeInMillis(date.getTimeInMillis()-3600000*24);//va indietro di un giorno
		if(getBirthDate().compareTo(new Date(date.getTimeInMillis()))>=0){*/
		if(getBirthDate().compareTo(new Date())>=0){
			addFieldError("birthDate", getText("error.birthDate"));}
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
