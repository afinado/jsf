/*
 *  (c) Bernd Müller, www.jsfpraxis.de
 */
package de.jsfpraxis;

import java.util.logging.Logger;

import javax.faces.FactoryFinder;
import javax.faces.application.ApplicationFactory;
import javax.faces.bean.ManagedBean;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;


@ManagedBean
public class ImplTestHandler {
	
	private static Logger logger = Logger.getLogger("de.jsfpraxis.ImplTestHandler");

	private String input;
	

	
	public String action() {
		HtmlInputText inputText = (HtmlInputText) FacesContext.getCurrentInstance().getViewRoot().findComponent("form:input");
		logger.info("Komponentenklasse Input: " + inputText.getClass().getCanonicalName());
		logger.info("RendererType Input: " + inputText.getRendererType());
		logger.info("Family Input: " + inputText.getFamily());
		
		ApplicationFactory factory = (ApplicationFactory) FactoryFinder.getFactory(FactoryFinder.APPLICATION_FACTORY);
		logger.info("DefaultRenderKitId: " + factory.getApplication().getDefaultRenderKitId());
		logger.info("RenderKitId: " + FacesContext.getCurrentInstance().getViewRoot().getRenderKitId());
		
		return null;
	}

	
	public String getInput() {
		return input;
	}
	public void setInput(String input) {
		this.input = input;
	}
	
}
