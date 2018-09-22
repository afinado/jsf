/*
 *  (c) Bernd Müller, www.jsfpraxis.de
 */
package de.jsfpraxis;

import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.PartialResponseWriter;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;

/**
 * @author bernd
 *
 */
@ManagedBean
@RequestScoped
public class SimpleHandler {

	private static Logger logger = Logger.getLogger("de.jsfpraxis.SimpleHandler");

	private String text1;
	private String text2;
	private String text3;
	
	public void action() {
		logger.info("'action()' aufgerufen");
		replizieren();
	}
	
	public void actionListener(ActionEvent ae) {
		//logger.info("'actionListener(" + ae.getClass().getCanonicalName() + ")' aufgerufen");
		//logger.info("test1: " + text1);
	}
	
	public void prependIdListener(ActionEvent ae) {
		logger.info("prependidListener() aufgerufen");
		logger.info("getClientId(): " + ((UIComponent) ae.getSource()).getClientId());
		logger.info("getId(): " + ((UIComponent) ae.getSource()).getId());
		logger.info("getContainerId(): " + ((UIComponent) ae.getSource()).getContainerClientId(FacesContext.getCurrentInstance()));
	}
	
	public void ajaxBehaviorListener(AjaxBehaviorEvent abe) {
		logger.info("'ajaxBehaviorListener(" + abe.getClass().getCanonicalName() + ")' aufgerufen");
		logger.info(abe.getSource().getClass().getCanonicalName());
		//logger.info("Behavior-Objekt: " + abe.getBehavior().getClass().getCanonicalName());
		replizieren();
	}
	
	
	
	/**
	 *  Gibt die Eingabe in 'text1' in einem JavaScript-Fenster aus.
	 */
	public void javaScriptWindow() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        ExternalContext extContext = ctx.getExternalContext();
        if (ctx.getPartialViewContext().isAjaxRequest()) {
            try {
                extContext.setResponseContentType("text/xml");
                extContext.addResponseHeader("Cache-Control", "no-cache");
                PartialResponseWriter writer =
                      ctx.getPartialViewContext().getPartialResponseWriter();
                writer.startDocument();
                writer.startEval();
                writer.write("alert('Die Eingabe ist \\'" + text1 + "\\'');");
                
                // Achtung Cross-Site Scripting-Gefahr
                // z.B. Eingabe 'document.URL'
                //writer.write("alert('Die Eingabe ist ' + " + text1 + ");");
                writer.endEval();
                writer.endDocument();
                writer.flush();
                ctx.responseComplete();
            } catch (Exception e) {
                logger.warning("Fehler in 'javaScriptWindow()'");
            }
        }
	}

	
	
	private void replizieren() {
		text2 = text1 + " " + text1;
		text3 = text1 + " " +text1 + " " + text1;
	}

	
	
	public String getText1() {
		return text1;
	}
	public void setText1(String text1) {
		this.text1 = text1;
	}

	public String getText2() {
		return text2;
	}
	public void setText2(String text2) {
		this.text2 = text2;
	}

	public String getText3() {
		return text3;
	}
	public void setText3(String text3) {
		this.text3 = text3;
	}
	
}
