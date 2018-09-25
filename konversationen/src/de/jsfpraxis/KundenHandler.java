/*
 *  (c) Bernd Müller, www.jsfpraxis.de
 */

package de.jsfpraxis;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Remove;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;


@Named
@ConversationScoped
public class KundenHandler implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(KundenHandler.class.getCanonicalName());
	
	@Inject
	private Conversation conversation;
	
	private String vorname;
	private String nachname;
	private Date geburtstag;
	
	
	public KundenHandler() {
		logger.log(Level.INFO, KundenHandler.class.getName() + "-Instanz erzeugt");
		// Proxy zeigen:
		//logger.log(Level.INFO, "Hash: " + this.hashCode() + " Class: " + this.getClass().getCanonicalName());
	}

	
	public String neuanlage() {
		logger.log(Level.INFO, "neuanlage()");
		logger.log(Level.INFO, "transient: " + conversation.isTransient());
		conversation.begin();
		logger.log(Level.INFO, "transient: " + conversation.isTransient());
		return "vorname?faces-redirect=true";
	}
	
	
	public String vorname() {
		logger.log(Level.INFO, "vorname()");
		return "vorname?faces-redirect=true";
	}
	
	
	public String nachname() {
		logger.log(Level.INFO, "nachname()");
		return "nachname?faces-redirect=true";
	}

	
	public String geburtstag() {
		logger.log(Level.INFO, "geburtstag()");
		return "geburtstag?faces-redirect=true";
	}

	
	public String anlegen() {
		logger.log(Level.INFO, "anlegen()");
		logger.log(Level.INFO, "Gesammelte Info:");
		logger.log(Level.INFO, "Vorname: " + vorname + ", Nachname:" + nachname + ", Geburtstag: " + geburtstag());
		logger.log(Level.INFO, "transient: " + conversation.isTransient());
		conversation.end();
		logger.log(Level.INFO, "conversation.end()");
		logger.log(Level.INFO, "transient: " + conversation.isTransient());
		//sessionInfo();
		return "fertig?faces-redirect=true";		
	}

	
	public String abbrechen() {
		logger.log(Level.INFO, "abbrechen()");
		conversation.end();
		return "abgebrochen?faces-redirect=true";		
	}


	@SuppressWarnings("unused")
	private void sessionInfo() {
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		Set<Entry<String, Object>> o = sessionMap.entrySet();
		for (Entry<String, Object> entry : o) {
			logger.log(Level.INFO, "Key: " + entry.getKey() + " Value: " + entry.getValue() + " Value Class: " + entry.getValue().getClass().getCanonicalName());
		}
	}
	
	
    public void checkConversation(ComponentSystemEvent cse) {
    	FacesContext context = FacesContext.getCurrentInstance();
        if (conversation.isTransient()) {
        	logger.info("Nicht in Konversation für View: " + context.getViewRoot().getViewId()); 
        	context.getApplication().getNavigationHandler().handleNavigation(context, null, "/neuanlage.xhtml?faces-redirect=true");
        }
    }
	
	
	@Remove
	public void destroy() {
		logger.log(Level.INFO, "destroy()");
		
	}

	
	// Getter/Setter
	
	public String getVorname() {
		return vorname;
	}
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}


	public String getNachname() {
		return nachname;
	}
	public void setNachname(String nachname) {
		this.nachname = nachname;
	}


	public Date getGeburtstag() {
		return geburtstag;
	}
	public void setGeburtstag(Date geburtstag) {
		this.geburtstag = geburtstag;
	}
	
}
