/**
 * 
 */
package struts2;

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
		if (this.userName.equals("admin") && this.password.equals("admin123")) {
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
		if (getUserName().length() == 0) {
			addFieldError("userName", getText("requiredstring"));
			} else if (!getUserName().equals("Eswar")) {
			addFieldError("userName", "error.user");
			}
			if (getPassword().length() == 0) {
			addFieldError("password", getText("requiredstring"));
			}
	}
	
}
