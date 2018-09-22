package de.jsfpraxis;

import java.util.logging.Logger;

import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;

public class MyActionListener implements ActionListener {

	private static Logger log = Logger.getLogger("de.jsfpraxis.MyActionListener");
	

	
	public MyActionListener() {
		log.info("Konstruktor MyActionListener aufgerufen");
	}


	@Override
	public void processAction(ActionEvent event)
			throws AbortProcessingException {
		log.info("MyActionListner.processAction() aufgerufen");
	}

}
