/*
 *  (c) Bernd Müller, www.jsfpraxis.de
 */

package de.jsfpraxis;

import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


/**
 *  Einfache Managed-Bean zur Verwaltung von Comedians
 */
@ManagedBean
@SessionScoped
public class ComedianHandler implements Serializable {

	private static final long serialVersionUID = 1L;

	private EntityManagerFactory emf;
	
	private EntityManager em;
	
	private EntityTransaction utx;
	

	private DataModel<Comedian> comedians;
	private Comedian aktuellerComedian = new Comedian();

		
	public ComedianHandler() {
		Logger.getAnonymousLogger().log(Level.INFO, "Konstruktor ComedianHandler() aufgerufen");
		emf = Persistence.createEntityManagerFactory("comediansPU");
		em = emf.createEntityManager();
		utx = em.getTransaction();
	}

	
	public String speichern() {
		Logger.getAnonymousLogger().log(Level.INFO, "speichern() [1] mit " + aktuellerComedian +  "' aufgerufen");
		try {
			utx.begin();
			aktuellerComedian = em.merge(aktuellerComedian);
			Logger.getAnonymousLogger().log(Level.INFO, "speichern() [2] mit " + aktuellerComedian+  "' aufgerufen");
			em.persist(aktuellerComedian);
			comedians.setWrappedData(em.createNamedQuery("SelectComedians").getResultList());
			utx.commit();
		} catch (Exception e) {
			Logger.getAnonymousLogger().log(Level.SEVERE, "'speichern()' nicht geklappt", e.getMessage());
		}
		return "/anzeige-comedians.xhtml";
	}

	
	public String aendern() {
		aktuellerComedian = comedians.getRowData();
		Logger.getAnonymousLogger().log(Level.INFO, "aendern() mit " + aktuellerComedian);
		return "/comedian.xhtml";
	}
	
	public String neuanlage() {
		aktuellerComedian = new Comedian();
		Logger.getAnonymousLogger().log(Level.INFO, "neuanlage()");
		return "/comedian.xhtml";
	}
	
	public String loeschen() {
		aktuellerComedian = comedians.getRowData();
		Logger.getAnonymousLogger().log(Level.INFO, "loeschen() mit " + aktuellerComedian);
		try {
			utx.begin();
			aktuellerComedian = em.merge(aktuellerComedian);
			em.remove(aktuellerComedian);
			comedians.setWrappedData(em.createNamedQuery("SelectComedians").getResultList());
			utx.commit();
		} catch (Exception e) {
			Logger.getAnonymousLogger().log(Level.SEVERE, "'loeschen()' nicht geklappt", e.getMessage());
		}
		return null;
	}
	
	
	/*
	 * Bekannter Fehler: Methode wird pro Session aufgerufen, d.h. Daten werden
	 * für JEDE Session in die DB geschrieben.
	 * Mögliche Abhilfe: In MB mit Application-Scope auslagern.
	 */
	@PostConstruct
	public void init() {
		Logger.getAnonymousLogger().log(Level.INFO, "'init()' aufgerufen");
		try {
			utx.begin();
			em.persist(new Comedian("Mario", "Barth", new GregorianCalendar(1972, 10, 1).getTime()));
			em.persist(new Comedian("Atze", "Schröder", new GregorianCalendar(1965, 8, 27).getTime()));
			em.persist(new Comedian("Dieter", "Nuhr", new GregorianCalendar(1960, 9, 29).getTime()));
			em.persist(new Comedian("Anke", "Engelke", new GregorianCalendar(1965, 11, 21).getTime()));
			em.persist(new Comedian("Kaya", "Yanar", new GregorianCalendar(1973, 4, 20).getTime()));
			comedians = new ListDataModel<Comedian>();
			comedians.setWrappedData(em.createNamedQuery("SelectComedians").getResultList());
			utx.commit();			
		} catch (Exception e) {
			Logger.getAnonymousLogger().log(Level.SEVERE, "'init()' nicht geklappt", e.getMessage());
		}
	}
	
	
	public DataModel<Comedian> getComedians() {
		return comedians;
	}


	public Comedian getAktuellerComedian() {
		return aktuellerComedian;
	}
	public void setAktuellerComedian(Comedian aktuellerComedian) {
		this.aktuellerComedian = aktuellerComedian;
	}
	
	
}
