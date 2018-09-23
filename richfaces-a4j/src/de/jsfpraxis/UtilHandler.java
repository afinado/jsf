/*
 *  (c) Bernd Müller, www.jsfpraxis.de
 */
package de.jsfpraxis;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

//import org.hibernate.validator.Email;

@ManagedBean
@SessionScoped
public class UtilHandler {

	
	private static final int MIN = 0;
	private static final int MAX = 10;
	
	private String text;
	private Integer ganzzahl;
	//@Email(message = "Bitte geben Sie eine korrekte E-Mail-Adresse ein.")
	private String email;
	
	
	public UtilHandler() {
		Logger.getAnonymousLogger().log(Level.INFO, "Konstruktor UtilHandler() aufgerufen");
	}

	
	/**
	 * Einfache Action-Methode, die über AJAX aufgerufen wird.
	 */
	public String ajaxAction() {
		// auskommentieren für a4j:commandLink-Beispiel:
		//text = "ajaxAction() aufgerufen mit '" + text + "'";
		Logger.getAnonymousLogger().log(Level.INFO, "UtilHandler.ajaxAction() aufgerufen");
		
		// Verzögerung für Test von eventsQueue und requestDelay
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e1) {
//			e1.printStackTrace();
//		}
		return null;
	}

	
	/**
	 * 
	 */
	public void validateMinMax(FacesContext context, UIComponent component, Object value) 
		throws ValidatorException {
		if (((Integer) value) < MIN || ((Integer) value) > MAX) {
			throw new ValidatorException(new FacesMessage("Wert nicht zwischen " + MIN + " und " + MAX));
		}
	}
	
	
	public String getText() {
		//Logger.getAnonymousLogger().log(Level.INFO, "UtilHandler.getText() aufgerufen: " + text);
		return text;
	}
	public void setText(String text) {
		//Logger.getAnonymousLogger().log(Level.INFO, "UtilHandler.setText(" + text + ") aufgerufen");
		this.text = text;
	}


	public Integer getGanzzahl() {
		Logger.getAnonymousLogger().log(Level.INFO, "UtilHandler.getGanzzahl() aufgerufen: " + ganzzahl);
		return ganzzahl;
	}
	public void setGanzzahl(Integer ganzzahl) {
		Logger.getAnonymousLogger().log(Level.INFO, "UtilHandler.setGanzzahl(" + ganzzahl + ") aufgerufen");
		this.ganzzahl = ganzzahl;
	}


	public String getEmail() {
		Logger.getAnonymousLogger().log(Level.INFO, "UtilHandler.getEmail() aufgerufen: " + email);
		return email;
	}
	public void setEmail(String email) {
		Logger.getAnonymousLogger().log(Level.INFO, "UtilHandler.setEmail(" + email+ ") aufgerufen");
		this.email = email;
	}
	
	
}
