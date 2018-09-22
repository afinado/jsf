/*
 *  (c) Bernd Müller, www.jsfpraxis.de
 */
package de.jsfpraxis;

import java.util.List;
import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class TagHandler {

	private static Logger logger = Logger.getLogger("de.jsfpraxis.TagHandler");
	
	private UIComponent component;
	private String text;
	
	
	
	public TagHandler() {
		logger.info("TagHandler-Instanz erzeugt");
	}

	
	public String logTree() {
		logger.info("action() aufgerufen\nWert von 'text' ist '" + text + "'");
		UIViewRoot view = FacesContext.getCurrentInstance().getViewRoot();
		logger.info("root: " + view.getId() + " " + view.getClientId() + " " + view.getClass().getCanonicalName());
		List<UIComponent> children = view.getChildren();
		for (UIComponent uiComponent : children) {
			logComponent(4, uiComponent);
		}
		return null;
	}
	
	private void logComponent(int indent, UIComponent uiComponent) {
		logger.info(getBlanks(indent) + "Component: " + uiComponent.getId() + " " 
				+ uiComponent.getClientId() + " " + uiComponent.getClass().getCanonicalName());
		for (UIComponent comp : uiComponent.getChildren()) {
			logComponent(indent + 4, comp);
		}
	}


	public String[] getDigits() {
		return new String[] {"eins", "zwei", "drei", "vier", "fünf", "sechs", "sieben", "acht", "neun", "null"};
	}
	
	public String[] getColors() {
		return new String[] {"rot", "gelb", "grün", "blau"};
	}

	
	
	public UIComponent getComponent() {
		return component;
	}
	public void setComponent(UIComponent component) {
		logger.info("setComponent() aufgerufen " + component + " " + component.getClass().getCanonicalName());
		this.component = component;
	}


	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	public boolean getInProduction() {
		logger.info("getInProduction() aufgerufen"); 
		return true;
	}
	
	private String getBlanks(int i) {
		StringBuilder sb = new StringBuilder(i);
		for (int j = 0; j < i; j++) {
			sb.append(" ");
		}
		return sb.toString();
	}
	
	
}
