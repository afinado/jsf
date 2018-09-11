/*
 *  (c) Bernd Müller, www.jsfpraxis.de
 */
package de.jsfpraxis;




public class BrettImpl implements java.io.Serializable, Brett {
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int GUTE_ZUEGE[] = {4, 0, 2, 6, 8, 1, 3, 5, 7};
	private static final int[][] GEWONNEN = new int[][] 
	  {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {6, 4, 2}};
	
	private Boolean[] feld;
	private boolean fertig = false; // Spiel ist fertig (gewonnen oder verloren)
	
	
	public BrettImpl() {
		super();
		feld = new Boolean[] { null, null, null, null, null, null, null, null, null};
	}


	/* (non-Javadoc)
	 * @see de.pdbm.jsf.atis.model.Brett#isGewonnen()
	 */
	public boolean isGewonnen() {
		for (int i = 0; i < GEWONNEN.length; i++) {
			int[] gewonnen = GEWONNEN[i];
			if (feld[gewonnen[0]] == KREIS && feld[gewonnen[1]] == KREIS && feld[gewonnen[2]] == KREIS) {
				fertig = true;
				return true;
			}
		}
		return false;
	}
	

	/* (non-Javadoc)
	 * @see de.pdbm.jsf.atis.model.Brett#isVerloren()
	 */
	public boolean isVerloren() {
		for (int i = 0; i < GEWONNEN.length; i++) {
			int[] gewonnen = GEWONNEN[i];
			if (feld[gewonnen[0]] == KREUZ && feld[gewonnen[1]] == KREUZ && feld[gewonnen[2]] == KREUZ) {
				fertig = true;
				return true;
			}
		}
		return false;
	}
	

	/*
	 * Computer wählt den nächsten freien 'guten' Zug.
	 */
	/* (non-Javadoc)
	 * @see de.pdbm.jsf.atis.model.Brett#waehleZug()
	 */
	public void waehleZug() {
		for (int i = 0; i < GUTE_ZUEGE.length; i++) {
			//log.info("versuche Feld " + GUTE_ZUEGE[i]);
			if (feld[GUTE_ZUEGE[i]] == LEER){
				feld[GUTE_ZUEGE[i]] = KREIS;
				//log.info("nehme Feld " + GUTE_ZUEGE[i]);
				return;
			}
		}
	}

	
	/* (non-Javadoc)
	 * @see de.pdbm.jsf.atis.model.Brett#setze(int)
	 */
	public void setze(int i) throws IllegalArgumentException{
		if (feld[i] == LEER) {
			feld[i] = KREUZ;	
		} else {
			throw new IllegalArgumentException("Feld bereits belegt");
		}
	}

	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < feld.length; i++) {
			sb.append(feld[i] + " ");
		}
		return sb.toString();
	}

	/* (non-Javadoc)
	 * @see de.pdbm.jsf.atis.model.Brett#getFeld()
	 */
	public Boolean[] getBrett() {
		return feld;
	}


	/* (non-Javadoc)
	 * @see de.pdbm.jsf.atis.model.Brett#isFertig()
	 */
	public boolean isFertig() {
		return fertig;
	}

}