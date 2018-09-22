/*
 *  (c) Bernd Müller, www.jsfpraxis.de
 */
package de.jsfpraxis;

import java.io.IOException;
import java.util.logging.Logger;

import javax.faces.FacesException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class RedirectHandler {

	private static Logger logger = Logger.getLogger("de.jsfpraxis.RedirectHandler");

	/**
	 *  Redirect mit Ajax.
	 *  
	 *  Quelle:
	 *  http://weblogs.java.net/blog/2009/05/14/redirecting-jsf-20-ajax-request
	 */
	public void action() {
		logger.info("'action()' aufgerufen");
        FacesContext ctx = FacesContext.getCurrentInstance();

        ExternalContext extContext = ctx.getExternalContext();
        String url = extContext.encodeActionURL(ctx.getApplication().getViewHandler().getActionURL(ctx, "/simple-button.xhtml"));
        try {
            extContext.redirect(url);
        } catch (IOException ioe) {
            throw new FacesException(ioe);

        }
	}
	
}
