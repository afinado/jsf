/*
 *  (c) Bernd Müller, www.jsfpraxis.de
 */
package de.jsfpraxis;

import java.util.Stack;
import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UICommand;
import javax.faces.event.ActionEvent;

@ManagedBean(name = "calc")
@SessionScoped
public class CalculatorHandler {
	
	private static final String KEYPAD = "keypad.xhtml";
	private static final String KEYPAD_HANDY = "keypad-handy.xhtml";

	private static Logger logger = Logger.getLogger("de.jsfpraxis.CalculatorHandler");

	private String input = "";
	private String error = "";
	private Stack<Double> stack = new Stack<Double>();
	private String zehnerOrHandy = KEYPAD;
	
	
	
	/**
	 * Zifferntaste wurde gedrückt.
	 */
	public void numberInput(ActionEvent ae) {
		logger.info("Eingegeben: " + ((UICommand) ae.getComponent()).getValue());
		input += ((UICommand) ae.getComponent()).getValue();
		error = "";
	}

	
	
	public String enter() {
		error = "";
		if (inputIsEmpty()) {
			logger.info("leere Eingabe");
			error = "Leere Eingabe";
			return null;
		}
		try {
			stack.push(getValidInput());
		} catch (NumberFormatException e) {
			logger.info("falsche Eingabe");
			error = "Falsche Eingabe";
		}
		input = "";
		return null;
	}

	
	private boolean inputIsEmpty() {
		return input.trim().equals("");
	}
	
	
	/**
	 * Liefert Eingabe als Double, falls möglich, sonst null.
	 */
	private Double getValidInput() throws NumberFormatException {
		try {
			// entweder Eingabe ist ein Integer:
			return (double) Integer.parseInt(input);
		} catch (NumberFormatException e) {
			// oder Eingabe ist ein Double:
			return Double.parseDouble(input);	
		}
	}
	
	
	
	public String clearInput() {
		input = "";
		error = "";
		return null;
	}
	
	public String clearStack() {
		stack = new Stack<Double>();
		error = "";
		return null;
	}
	
	
	public String xy() {
		checkForInput();
		Double first = stack.pop();
		Double second= stack.pop();
		stack.push(first);
		stack.push(second);
		error = "";
		return null;
	}
	
	
	public String add() {
		checkForInput();
		Double summand1 = stack.pop();
		Double summand2 = stack.pop();
		stack.push(summand1 + summand2);
		input = "";
		return null;
	}

	

	public String subtract() {
		checkForInput();
		Double minuend = stack.pop();
		Double subtrahend = stack.pop();
		stack.push(minuend - subtrahend);
		return null;
	}
	
	
	public String multiply() {
		checkForInput();
		Double multiplikant = stack.pop();
		Double multiplikator = stack.pop();
		stack.push(multiplikant * multiplikator);
		return null;
	}
	
	
	public String divide() {
		checkForInput();
		Double dividend = stack.pop();
		Double divisor = stack.pop();
		stack.push(dividend / divisor);
		checkRange();
		return null;
	}
	
	


	/**
	 * Prüft auf Eingabe und legt sie auf den Stack, um die
	 * Arithmetik danach zu vereinfachen.
	 */
	private void checkForInput() {
		if (!inputIsEmpty()) {
			try {
				stack.push(getValidInput());
			} catch (NumberFormatException e) {
				logger.info("falsche Eingabe 2");
			}
			input = "";
		}
	}
	
	
	private void checkRange() {
		if (stack.peek().equals(Double.POSITIVE_INFINITY) 
				|| stack.peek().equals(Double.NEGATIVE_INFINITY)
				|| stack.peek().equals(Double.NaN)) {
			error = "Zahlenbereich überschritten";
			stack.pop();
		}
	}
	
	
	/**
	 * Wechselt zwischen Ziffernblock und Handy-Layout
	 */
	public void changeNumericHandy() {
		if (zehnerOrHandy.equals(KEYPAD)) {
			zehnerOrHandy = KEYPAD_HANDY;
		} else {
			zehnerOrHandy = KEYPAD;
		}
	}
	
	
	public String getInput() {
		return input;
	}
	public void setInput(String input) {
		this.input = input;
	}

	
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}


	public String getStackString() {
		String text = "";
		for (Double entry : stack) {
			text = entry + "\n" + text;
		}
		return text;
	}
//	public void setStackString(String stackString) {
//		log.info("aufgerufen"); // wird nie aufgerufen, da Komponente disabled
//	}


	public Stack<Double> getStack() {
		return stack;
	}
	public void setStack(Stack<Double> stack) {
		this.stack = stack;
	}



	public String getZehnerOrHandy() {
		return zehnerOrHandy;
	}
	public void setZehnerOrHandy(String zehnerOrHandy) {
		this.zehnerOrHandy = zehnerOrHandy;
	}

	
	
}
