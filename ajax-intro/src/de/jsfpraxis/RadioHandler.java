/*
 *  (c) Bernd Müller, www.jsfpraxis.de
 */
package de.jsfpraxis;

import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.AjaxBehaviorEvent;

@ManagedBean
@RequestScoped
public class RadioHandler {

	private static Logger logger = Logger.getLogger("de.jsfpraxis.RadioHandler");

	private Integer radio = 1;
	private String kontoinhaber;
	private String kontonummer;
	private String blz; 
	private String kkart;
	private String gueltigBis;
	private String nummer;
	private String name;
	private String strasseNr;
	private String plzOrt;
	
	

	
	public void ajaxBehaviorListener(AjaxBehaviorEvent abe) {
		logger.info("'ajaxBehaviorListener(" + abe.getClass().getCanonicalName() + ")' aufgerufen");
		logger.info("Behavior-Objekt: " + abe.getBehavior().getClass().getCanonicalName());
	}




	public Integer getRadio() {
		return radio;
	}
	public void setRadio(Integer radio) {
		this.radio = radio;
	}


	public String getKontoinhaber() {
		return kontoinhaber;
	}
	public void setKontoinhaber(String kontoinhaber) {
		this.kontoinhaber = kontoinhaber;
	}


	public String getKontonummer() {
		return kontonummer;
	}
	public void setKontonummer(String kontonummer) {
		this.kontonummer = kontonummer;
	}


	public String getBlz() {
		return blz;
	}
	public void setBlz(String blz) {
		this.blz = blz;
	}


	public String getKkart() {
		return kkart;
	}
	public void setKkart(String kkart) {
		this.kkart = kkart;
	}


	public String getGueltigBis() {
		return gueltigBis;
	}
	public void setGueltigBis(String gueltigBis) {
		this.gueltigBis = gueltigBis;
	}


	public String getNummer() {
		return nummer;
	}
	public void setNummer(String nummer) {
		this.nummer = nummer;
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


	public String getStrasseNr() {
		return strasseNr;
	}
	public void setStrasseNr(String strasseNr) {
		this.strasseNr = strasseNr;
	}


	public String getPlzOrt() {
		return plzOrt;
	}
	public void setPlzOrt(String plzOrt) {
		this.plzOrt = plzOrt;
	}
	

	
}
