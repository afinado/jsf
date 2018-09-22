package de.jsfpraxis;

import java.util.logging.Logger;

import javax.faces.component.UIInput;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;

public class MyLogger implements ActionListener {

	private static Logger log = Logger.getLogger("de.jsfpraxis.MyLogger");
	

	
	public MyLogger() {
		log.info("Konstruktor MyLogger aufgerufen");
	}


	@Override
	public void processAction(ActionEvent event)
			throws AbortProcessingException {
		log.info("processAction() aufgerufen");
		UIInput user = (UIInput) event.getComponent().getParent().findComponent("form:user"); 
		UIInput password = (UIInput) event.getComponent().getParent().findComponent("form:password"); 
		log.info("Benutzer:" + user.getValue() + ", Passwort: " + password.getValue());
	}

}
