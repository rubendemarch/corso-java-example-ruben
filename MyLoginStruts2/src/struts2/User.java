/**
 * 
 */
package struts2;

import java.util.Date;

/**
 * @author ALFA403
 *
 */
public class User {

	private String idUser;
	private String userName;
	private String name;
	private String surname;
	private String password;
	private Integer wrongTriesCount;
	private String email;
	private String phone;
	private String mobilePhone;
	private Date birthDay;
	private Date registerDay;
	private Date lastLogin;
	private String idRole;
	
	public User(){
		
	}
	/**
	 * @param idUser
	 * @param userName
	 * @param name
	 * @param surname
	 * @param password
	 * @param wrongTriesCount
	 * @param email
	 * @param phone
	 * @param mobilePhone
	 * @param birthDay
	 * @param registerDay
	 * @param lastLogin
	 * @param idRole
	 * @return 
	 */
	public User(String idUser, String userName, String name, String surname,
			String password, Integer wrongTriesCount, String email, String phone,
			String mobilePhone, Date birthDay, Date registerDay,
			Date lastLogin, String idRole) {
		this.idUser = idUser;
		this.userName = userName;
		this.name = name;
		this.surname = surname;
		this.password = password;
		this.wrongTriesCount = wrongTriesCount;
		this.email = email;
		this.phone = phone;
		this.mobilePhone = mobilePhone;
		this.birthDay = birthDay;
		this.registerDay = registerDay;
		this.lastLogin = lastLogin;
		this.idRole = idRole;
	}
	/**
	 * @return the idUser
	 */
	public String getIdUser() {
		return idUser;
	}
	/**
	 * @param idUser the idUser to set
	 */
	public void setIdUser(String idUser) {
		this.idUser = idUser;
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
		return registerDay;
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
		return lastLogin;
	}
	/**
	 * @param lastLogin the lastLogin to set
	 */
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}
	/**
	 * @return the idRole
	 */
	public String getIdRole() {
		return idRole;
	}
	/**
	 * @param idRole the idRole to set
	 */
	public void setIdRole(String idRole) {
		this.idRole = idRole;
	}
}
