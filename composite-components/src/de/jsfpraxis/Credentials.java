package de.jsfpraxis;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="credentials")
@SessionScoped
public class Credentials implements Serializable {
	
	private static Logger log = Logger.getLogger("de.jsfpraxis.Credentials");

	private String name;
	private String password;
	
	public Credentials() {
		log.info("Credentials erzeugt");
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
