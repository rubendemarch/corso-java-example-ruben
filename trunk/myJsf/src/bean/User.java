/**
 * 
 */
package bean;

import javax.faces.bean.ManagedProperty;

import org.apache.commons.codec.digest.DigestUtils;

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

	public void logout(){
		setLogged(false);
		setPassword(null);
		setUserName(null);
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
}
