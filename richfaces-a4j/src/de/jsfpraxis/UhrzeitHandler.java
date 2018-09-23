package de.jsfpraxis;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class UhrzeitHandler {
	
	private static Logger logger = Logger.getLogger("de.jsfpraxis.UhrzeitHandler");

	private boolean pollen = true;

	
	public boolean getPollen() {
		return pollen;
	}
	public void setPollen(boolean pollen) {
		this.pollen = pollen;
	}
	
	public String umschalten() {
		logger.info("umschalten() aufgerufen. pollen ist " + pollen);
		pollen = ! pollen;
		return null;
	}

	public String getZeit() {
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		String zeit= dateFormat.format(new Date());
		logger.info("UhrzeitHandler.getZeit() aufgerufen: " + zeit);
		return zeit;
	}
}
