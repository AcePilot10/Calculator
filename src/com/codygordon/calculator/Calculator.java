package com.codygordon.calculator;

import com.codygordon.calculator.views.CalculatorView;

/**
 * Basic UI calculator application
 * @author Cody Gordon
 */
public class Calculator {

	public static void main(String[] args) {
		Frame frame = new Frame();
		CalculatorView view = new CalculatorView();
		frame.add(view);
		frame.setVisible(true);
	}
}