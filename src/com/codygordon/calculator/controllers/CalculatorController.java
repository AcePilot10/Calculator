package com.codygordon.calculator.controllers;

import com.codygordon.calculator.models.CalculatorModel;
import com.codygordon.calculator.views.CalculatorView;

/**
 * Controller for the calculator view
 * @author Cody Gordon
 *
 */
public class CalculatorController {

	private CalculatorModel model;
	private CalculatorView view;
	
	public enum OperationType {
		PLUS, MINUS, DIVIDE, MULTIPLY
	}
	
	public CalculatorController(CalculatorView view) { 
		this.view = view;
		model = new CalculatorModel();
	}
	
	/**
	 * Processes an operation and saves the first number to the model
	 * @param op The operation to execute
	 */
	public void doOperation(OperationType op) {
		try {
			model.operation = op;
			model.num1 = Float.parseFloat(view.getDisplay().getDisplay().getText());
			view.getDisplay().getDisplay().setText(""); 
			view.getDisplay().getTopDisplay().setText(formatNumber(model.num1) + getOperationText(op));
		}
		catch(Exception e) { }
	}
	
	/***
	 * Attempts to process the numbers given and displays the result to the user.
	 */
	public void calculateNumber() {
		try {
			model.num2 = Float.parseFloat(view.getDisplay().getDisplay().getText());
			float outcome = 0;
			switch(model.operation) {
			case DIVIDE:
				outcome = model.num1 / model.num2;
				break;
			case MULTIPLY:
				outcome = model.num1 * model.num2;
				break;
			case MINUS:
				outcome = model.num1 - model.num2;
				break;
			case PLUS:
				outcome = model.num1 + model.num2;
				break;
			}
			model.outcome = outcome;
			view.getDisplay().getDisplay().setText(formatNumber(model.num1) + getOperationText(model.operation) + formatNumber(model.num2) + "=" + formatNumber(model.outcome));
			view.getDisplay().getTopDisplay().setText("");
		}
		catch(Exception e) { }
	}
	
	/**
	 * Clear what is currently inputed
	 */
	public void clear() {
		model.num1 = 0;
		model.num2 = 0;
		model.outcome = 0;
		model.operation = null;
		view.getDisplay().getDisplay().setText("");
		view.getDisplay().getTopDisplay().setText("");
	}
	
	/**
	 * Returns a string format of a given operation for displaying.
	 * @param op The operation 
	 * @return a string format of the operation
	 */
	private String getOperationText(OperationType op) {
		switch(op) {
		case DIVIDE:
			return "/";
		case MINUS:
			return "-";
		case MULTIPLY:
			return "*";
		case PLUS:
			return "+";
		}
		return "";
	}

	/**
	 * Formats a floating decimal number and remove unnecessary decimals for displaying.
	 * @param d the floating point to format
	 * @return formatted number
	 */
	public String formatNumber(float d)
	{
	    if(d == (long) d)
	        return String.format("%d",(long)d);
	    else
	        return String.format("%s",d);
	}
}