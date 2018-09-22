package de.jsfpraxis;

import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class LoginHandlerFT {
	
	private static Logger log = Logger.getLogger("de.jsfpraxis.LoginHandlerFT");
	
	private String name;
	private String password;
	
	public String login() {
		log.info("login() aufgerufen");
		log.info("name: " + name + ", password: " + password);
		if (name.equals(password)) {
			return "login-ok";	
		} else {
			return "login-not-ok";	
		}
	}

	
	
	public LoginHandlerFT() {
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
