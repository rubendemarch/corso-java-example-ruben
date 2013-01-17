/**
 * 
 */
package bean;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.ResourceBundle;

import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.TransactionIsolationLevel;

import util.log.MyLogger;
import dao.MySql;

/**
 * @author ALFA403
 *
 */
public class User {
	
	@ManagedProperty (value="#{mySql}",name="mySql")
	private MySql mySql;
	private String userName;
	private String loggedUserName;
	private String password;
	private boolean isLogged;
	private boolean registered;
	private String idUser;
	private String name;
	private String surname;
	private Integer wrongTriesCount;
	private String email;
	private String phone;
	private String mobilePhone;
	private Date birthDay;
	private Date registerDay;
	private Date lastLogin;
	private String idRole;
	
	private String colorPassword;
	private ResourceBundle bundle = MySql.getBundle();
	private MyLogger log;
	/**
	 * @param userName
	 * @param password
	 * @param idUser
	 * @param name
	 * @param surname
	 * @param wrongTriesCount
	 * @param email
	 * @param phone
	 * @param mobilePhone
	 * @param birthDay
	 * @param registerDay
	 * @param lastLogin
	 * @param idRole
	 */
	public User() {
		log = new MyLogger(getClass());
	}
	public String getColorPassword(){
		return colorPassword;
	}
	/**
	 * @param colorPassword the colorPassword to set
	 */
	public void setColorPassword(String colorPassword) {
		this.colorPassword = colorPassword;
	}
	
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
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
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the md5 hex password
	 */
	public String getMd5Password() {
		return DigestUtils.md5Hex(password);
	}
	/**
	 * @return the isLogged
	 */
	public boolean isLogged() {
		return isLogged;
	}
	/**
	 * @param isLogged the isLogged to set
	 */
	public void setLogged(boolean isLogged) {
		this.isLogged = isLogged;
		if(isLogged)setLoggedUserName(this.userName);
	}

	/**
	 * @return the loggedUserName
	 */
	public String getLoggedUserName() {
		return loggedUserName;
	}
	/**
	 * @param loggedUserName the loggedUserName to set
	 */
	public void setLoggedUserName(String loggedUserName) {
		this.loggedUserName = loggedUserName;
	}
	/**
	 * @return the mySql
	 */
	public MySql getMySql() {
		return mySql;
	}
	/**
	 * @param mySql the mySql to set
	 */
	public void setMySql(MySql mySql) {
		this.mySql = mySql;
	}

	/**
	 * @param idUser the idUser to set
	 */
	public void setIdUser(String idUser) {
		this.idUser = idUser;
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
	 * @return the wrongTriesCount
	 */
	public Integer getWrongTriesCount() {
		return wrongTriesCount;
	}
	/**
	 * @param wrongTriesCount the wrongTriesCount to set
	 */
	public void setWrongTriesCount(Integer wrongTriesCount) {
		this.wrongTriesCount = wrongTriesCount;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
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
	 * @return the birthDay
	 */
	public Date getBirthDay() {
		return birthDay;
	}
	/**
	 * @param birthDay the birthDay to set
	 */
	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}
	/**
	 * @return the registerDay
	 */
	public Date getRegisterDay() {
		return new Date();
	}
	/**
	 * @param registerDay the registerDay to set
	 */
	public void setRegisterDay(Date registerDay) {
		this.registerDay = registerDay;
	}
	/**
	 * @return the lastLogin
	 */
	public Date getLastLogin() {
		return new Date();
	}
	/**
	 * @param lastLogin the lastLogin to set
	 */
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}
	/**
	 * @param idRole the idRole to set
	 */
	public void setIdRole(String idRole) {
		this.idRole = idRole;
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
	
	public String getIdUser() {
		long now = Calendar.getInstance().getTimeInMillis();
		return now + "" + new Random(now).nextInt(1000);
	}
	/**
	 * @return the registered
	 */
	public boolean getRegistered() {
		if (userName!=null){
		SqlSessionFactory sqlFactory = MySql.getSqlSessionFactory();
		SqlSession sql=sqlFactory.openSession();
		int count = sql.selectOne("Users.count",this);
		sql.close();
		setRegistered(count>0);
		}
		else setRegistered(false);
		return registered;
	}
	
	public void register() {
		final String metodo = "register";
		SqlSessionFactory sqlFactory = MySql.getSqlSessionFactory();
		SqlSession sql=sqlFactory.openSession();
		int rowsAffected = 0;
		try {
			rowsAffected = sql.insert("Users.add", this);
			sql.commit();
		} catch (Exception e) {
			log.error(metodo, sqlFactory.toString(), e);
			sql.rollback();
		} finally{
			sql.close();
		}
		FacesContext facesContext = FacesContext.getCurrentInstance();
		String redirect = (rowsAffected>0)?"registered":"error";// define the navigation rule that must be used in order to redirect the user to the adequate page...
		facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext, null, redirect);
	}
	
	public void login() {
		final String metodo = "login";
		log.start(metodo);
		SqlSessionFactory sqlFactory = MySql.getSqlSessionFactory();
		SqlSession sql=sqlFactory.openSession();
		int count=0;
		String redirect="welcome";
		log.info("Username", userName);
		log.info("Password", password);
		try {
			count = sql.selectOne("Users.lookfor", this);
		} catch (Exception e) {
			log.error(metodo, sqlFactory.toString(), e);
			redirect="error";
		} finally{
			sql.close();
		}
		
		if (count>0) {
			sql = sqlFactory.openSession(TransactionIsolationLevel.READ_COMMITTED);
			int rowsAffected =0;
			try {
				rowsAffected = sql.insert("Users.login", this);
				sql.commit();
			} catch (Exception e) {
				log.error(metodo, sqlFactory.toString(), e);
				sql.rollback();
				redirect="error";
			} finally{
				sql.close();
			}
			if(rowsAffected>0){
				setLogged(true);
				log.end(metodo+" - "+redirect);
			}else{
				redirect="error";
				log.end(metodo+" - "+redirect);
			}
		} else {
			redirect="error";
			log.end(metodo+" - "+redirect);
		}
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext, null, redirect);
	}
	/**
	 * @param isRegistered the isRegistered to set
	 */
	public void setRegistered(boolean isRegistered) {
		this.registered = isRegistered;
	}
	public void logout(){
		setLogged(false);
		setPassword(null);
		setLoggedUserName(null);
		setBirthDay(null);
		setEmail(null);
		setIdRole(null);
		setIdUser(null);
		setLastLogin(null);
		setMobilePhone(null);
		setName(null);
		setPhone(null);
		setRegisterDay(null);
		setSurname(null);
	}
	public String getCheckUserName(){
		if (userName!=null){
			return(getRegistered()? bundle.getString("userNameExist") : bundle.getString("userNameOk"));
		}else return "";
	}
	public String getColorUserName(){
		if (userName!=null){
			return(getRegistered()? "#FF0000" : "#00CC00");
		}else return "black";
	}
	
	public String getCheckPassword(){
		if (password!=null){
			if(password.matches("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!£$%&#òèéàù§*-+]).{6,120})")) {
				setColorPassword("#66FF33");
				return bundle.getString("ottima");
			}
			else if (password.matches("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,120})")){
				setColorPassword("#FFFF33");
				return bundle.getString("buona");
			}
			else {
				setColorPassword("#FF3333");
				return bundle.getString("scarsa");
			}
		}else {
			setColorPassword("#FFFFFF");
			return "";
		}
	}
}
