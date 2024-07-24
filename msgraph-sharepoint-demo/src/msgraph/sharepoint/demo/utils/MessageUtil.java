package msgraph.sharepoint.demo.utils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class MessageUtil {

	public static void addMessage(FacesMessage.Severity severity, String summary, String detail) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, summary, detail));
	}

	public static void showInfo() {
		addMessage(FacesMessage.SEVERITY_INFO, "Info Message", "Action successfully");
	}

	public static void showWarning() {
		addMessage(FacesMessage.SEVERITY_WARN, "Warning Message", "Action warning");
	}

	public static void showError() {
		addMessage(FacesMessage.SEVERITY_ERROR, "Error Message", "Action has an error");
	}

	public static void showInfoWithMessage(String message) {
		addMessage(FacesMessage.SEVERITY_INFO, message, null);
	}
}
