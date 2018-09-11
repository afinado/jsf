/*
 *  (c) Bernd Müller, www.jsfpraxis.de
 */

package de.jsfpraxis;

import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

@ManagedBean(name = "tttHandler", eager = true)
@SessionScoped
public class TicTacToeHandler {

	private static Logger logger = Logger.getLogger("de.jsfpraxis.TicTacToeHandler");
	
	private static final String KREIS = "/images/kreis.png";
	private static final String KREUZ = "/images/kreuz.png";
	private static final String LEER  = "/images/leer.png";
	
	private Brett brett;
	private String meldung;
	

	public TicTacToeHandler() {
		brett = new BrettImpl();
	}
	

	public void zug(ActionEvent ae) {
		logger.info("zug(ActionEvent) gedrückt");
		if (brett.isFertig())
			return;
		try {
			String pos = ae.getComponent().getId().split("-")[1];
			int intPos = Integer.parseInt(pos);
			brett.setze(intPos);
			if (brett.isVerloren()) {
				meldung = "Herzlichen Glückwunsch, Sie haben gewonnen !";
				return;
			}
			brett.waehleZug(); 
			if (brett.isGewonnen()) {
				meldung = "Sie haben leider verloren";
			}
		} catch (Exception e) {
			logger.info("Kein Spielerzug ausgeführt");
		}
	}

	public String[] getImage() {
		String[] feld = new String[9];
		for (int i = 0; i < brett.getBrett().length; i++) {
			if (brett.getBrett()[i] == Brett.KREIS) {
				feld[i] = KREIS;
			} else if (brett.getBrett()[i] == Brett.KREUZ) {
				feld[i] = KREUZ;
			} else {
				feld[i] = LEER;
			}
		}
		return feld;
	}

	public String neuesSpiel() {
		logger.info("neuesSpiel()");
		brett = new BrettImpl();
		meldung = "";
		return "success";
	}
	
	public String getMeldung() {
		return meldung;
	}


	public void setMeldung(String meldung) {
		this.meldung = meldung;
	}
	
}
