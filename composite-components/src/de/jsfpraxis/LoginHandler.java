package de.jsfpraxis;

import java.util.logging.Logger;

import javax.el.ELContext;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;


@ManagedBean
public class LoginHandler {
	
	private static Logger log = Logger.getLogger("de.jsfpraxis.LoginHandler");
	
	
	// Zugriff über ManagedProperty für credentials hat nicht geklappt
	// Bug oder Feature? In Spec nichts gefunden, dass es in CCs nicht gehen soll
	
	public String login() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ELContext elc =  fc.getELContext();
		Credentials credentials = (Credentials) fc.getApplication().getExpressionFactory().createValueExpression(elc, "#{credentials}", Credentials.class).getValue(elc);
		log.info("LoginHandler.login() aufgerufen. Credentials: " + credentials.getName() + " " + credentials.getPassword());
		if (credentials.getName().equals(credentials.getPassword())) {
			return "login-ok";	
		} else {
			return "login-not-ok";	
		}
	}

	
	public LoginHandler() {
		log.info("LoginHandler erzeugt");
	}
	
}
