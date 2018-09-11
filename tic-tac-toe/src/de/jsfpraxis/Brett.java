/*
 *  (c) Bernd Müller, www.jsfpraxis.de
 */
package de.jsfpraxis;

/**
 *  Eine Instanz der Klasse Brett stellt eine Spielsituation auf dem Brett dar.
 *  Der Computer ist durch den Kreis dargestellt, der Spieler durch das Kreuz.
 *  
 *  Die Methoden sind aus der Sicht des Computers definiert, z.B. liefert
 *  {@code isGewonnen()} {@code true}, falls der Computer gewonnen hat. 
 *  
 *  Layout:
 *  0 1 2
 *  3 4 5
 *  6 7 8
 *
 *  @author Bernd Müller
 *  @version 1.1
 */
public interface Brett {

	public static final Boolean KREIS = Boolean.TRUE; // Rechner
	public static final Boolean KREUZ = Boolean.FALSE; // Spieler
	public static final Boolean LEER = null;

	public boolean isGewonnen();
	public boolean isVerloren();
	public boolean isFertig();

	/*
	 * Computer wählt den nächsten freien 'guten' Zug.
	 */
	public void waehleZug();

	/**
	 * Spieler setzt Feld i
	 * @param i Brett-Index, zwischen 0 und 8
	 * @exception IllegalArgumentException Feld bereits belegt
	 */
	public void setze(int i) throws IllegalArgumentException;

	public Boolean[] getBrett();



}