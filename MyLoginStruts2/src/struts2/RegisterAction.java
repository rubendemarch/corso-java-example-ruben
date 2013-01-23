/**
 * 
 */
package struts2;

import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

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

	
	/**
	 * @param log
	 */
	public RegisterAction() {
		log = new MyLogger(getClass());
	}

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
		String ret=SUCCESS;
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
				try {
					sendMail();
				} catch (Exception e) {
					addActionError(getText("error.mail"));
					log.error(metodo, "sendMail", e);
				}
			}else{
				addActionError(getText("error.database"));
				ret=ERROR;
			}
		} else {
			addActionError(getText("error.register"));
			ret=ERROR;
		}
		log.end(metodo+" - "+ret);
		return ret;
	}

	private void sendMail() throws Exception {
		final String metodo = "sendMail";
		log.start(metodo);
		final Properties properties = new Properties();
		InputStream in = getClass().getResourceAsStream("/email.properties");
		properties.load(in);
		in.close();
		Session session = Session.getDefaultInstance(properties,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(properties.getProperty("address"), properties.getProperty("password"));
					}
				});

		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(properties.getProperty("address")));
		message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(email));
		message.setSubject(getSubject());
		message.setContent(getBody(), "text/html");
		Transport.send(message);
		log.end(metodo);
	}


	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ActionSupport#validate()
	 */
	@Override
	public void validate() {
		String metodo="validate";
		log.start(metodo);
		if (getName().length() == 0) {
			addFieldError("name", getText("requiredstring",new String[]{getText("label.name")}));}
		if (getSurname().length() == 0) {
			addFieldError("surname", getText("requiredstring",new String[]{getText("label.surname")}));} 
		if (!getEmail().matches("^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,4})$")) {
			addFieldError("email", getText("error.email"));}
		/*Calendar date = Calendar.getInstance();
		date.setTimeInMillis(date.getTimeInMillis()-3600000*24);//va indietro di un giorno
		if(getBirthDate().compareTo(new Date(date.getTimeInMillis()))>=0){*/
		if(getBirthDate()!=null && getBirthDate().compareTo(new Date())>=0){
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

	public String getSubject() {
		return getText("Complimenti")+" "+name;
	}

	public String getBody() {
		StringBuilder builder = new StringBuilder();
		builder.append(getText("Sei.un.utente.registrato")+"<br>")
			.append(getText("label.username")+":")
			.append("<div style='font-weight: bold;'>").append(userName).append("</div><br>")
			.append(getText("label.password")+":")
			.append("<div style='font-weight: bold;'>").append(password).append("</div><br><br>")
			.append(getText("Per.entrare.puoi.accedere"))
			.append("<p align='center'><a href='"+util.Constants.SITE_URL_LOGIN+"'>"+getText("body.link")+"</a></p>");
		return builder.toString();
	}
}
