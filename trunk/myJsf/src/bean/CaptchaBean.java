package bean;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class CaptchaBean {

	public void submit() {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Correct", "Correct");

		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
}