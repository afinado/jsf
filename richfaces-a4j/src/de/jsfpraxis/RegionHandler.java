package de.jsfpraxis;

import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class RegionHandler {
	
	private static Logger logger = Logger.getLogger("de.jsfpraxis.RegionHandler");

	private String text1;
	private String text2;
	private String text3;
	private String text4;
	
	
	public String action() {
		logger.info("RegionHandler.action() aufgerufen");
		logger.info("text1 = '" + text1 + "', text2 = '" + text2 + "', text3 = '" + text3 + "', text4 = '" + text4);
		return null;
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
	
	public String getText4() {
		return text4;
	}
	public void setText4(String text4) {
		this.text4 = text4;
	}
	
}
