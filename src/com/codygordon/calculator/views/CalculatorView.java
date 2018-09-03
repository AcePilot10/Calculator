package com.codygordon.calculator.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import com.codygordon.calculator.Settings;
import com.codygordon.calculator.controllers.CalculatorController;
import com.codygordon.calculator.controllers.CalculatorController.OperationType;

/**
 * Calculator panel to be added to the main frame.
 * @author Cody Gordon
 */
public class CalculatorView extends JPanel {

	private static final long serialVersionUID = 1L;

	private CalculatorDisplay display;
	private CalculatorButtonView buttons;
	
	private static CalculatorController controller;

	public CalculatorView() {
		super();
		setSize(Settings.WIDTH, Settings.HEIGHT);
		
		GridLayout layout = new GridLayout(2, 1);
		setLayout(layout);
		
		controller = new CalculatorController(this);
		
		display = new CalculatorDisplay();
		buttons = new CalculatorButtonView();
		
		add(display);
		add(buttons);
		
		setBackground(Settings.BACKGROUND_COLOR);
		setVisible(true);
	}
	
	/**
	 * @return the display view
	 */
	public CalculatorDisplay getDisplay() {
		return this.display;
	}
	
	/**
	 * @return the button view
	 */
	public CalculatorButtonView getView() {
		return this.buttons;
	}
	
	/**
	 * Button grid for calculator
	 * @author Cody Gordon
	 */
	public class CalculatorButtonView extends JPanel {
	
		private static final long serialVersionUID = 1L;
		
		public CalculatorButtonView() {
			super();
			setSize(Settings.WIDTH, Settings.HEIGHT);
			setBackground(Settings.BACKGROUND_COLOR);
			setVisible(true);
			initView();
		}
		
		/**
		 * Initiates the controls for the button grid
		 */
		private void initView() {
			setLayout(new GridLayout(4, 5));
			String[] buttons = new String[] {"1", "2", "3", "+", "()", "4", "5", "6", "-",
											 "()", "7", "8", "9", "/", "()", "C", "0", "=",
											 "*", "."};
			for(String btnText : buttons) {
				if(btnText == "()") {
					add(Box.createGlue());
				}
				else {
					JButton btn = new JButton();
					btn.setText(btnText);
					btn.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							handleButtonClicked(e);
						}
					});
					add(btn);
				}
			}
		}
		
		/**
		 * Handles action events from calculator buttons and executes controller method according to the clicked button
		 * @param event The event callback
		 */
		private void handleButtonClicked(ActionEvent event) {
			JButton btn = (JButton)event.getSource();
			String txt = btn.getText();
			if(isNumber(txt)) {
				addNumber(txt);
				return;
			} else {
				switch(txt) {
				case "+":
					controller.doOperation(OperationType.PLUS);
					break;
				case "-":
					controller.doOperation(OperationType.MINUS);
					break;
				case "/":
					controller.doOperation(OperationType.DIVIDE);
					break;
				case "*":
					controller.doOperation(OperationType.MULTIPLY);
					break;
				case "C":
					CalculatorView.controller.clear();
					break;
				case "=":
					controller.calculateNumber();
					break;
				case ".":
					addNumber(".");
					break;
				}
			}
		}
		
		/**
		 * Adds a number to the input
		 * @param number The number string to input
		 */
		private void addNumber(String number) {
			display.getDisplay().setText(display.getDisplay().getText() + number);
		}
		
		/**
		 * Checks if given string is a number
		 * @param txt the text inputed
		 * @return true if txt is a number (0 - 9)
		 */
		private boolean isNumber(String txt) {
			String[] numbers = new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
			for(String num : numbers) {
				if(num == txt) {
					return true;
				}
			}
			return false;
		}
	}
	
	/**
	 * The displays for the calculator
	 * @author Cody Gordon
	 */
	public class CalculatorDisplay extends JPanel {

		private static final long serialVersionUID = 1L;
		
		private JLabel lblDisplay;
		private JLabel lblTopDisplay;
		
		public CalculatorDisplay() {
			super();
			FlowLayout layout = new FlowLayout(FlowLayout.CENTER, 5, 5);
			setLayout(layout);
			setBackground(Settings.BACKGROUND_COLOR);
			initView();
		}
		
		/**
		 * Initiates the labels for the view
		 */
		private void initView() {
			lblDisplay = new JLabel();
			
			Border border = BorderFactory.createEmptyBorder(1, 1, 1, 1);

			lblDisplay.setBorder(border);
			lblDisplay.setOpaque(true);
			lblDisplay.setBackground(Color.GRAY);
			
			lblDisplay.setMinimumSize(new Dimension(Settings.DISPLAY_WIDTH, Settings.DISPLAY_HEIGHT));
			lblDisplay.setPreferredSize(new Dimension(Settings.DISPLAY_WIDTH, Settings.DISPLAY_HEIGHT));
			lblDisplay.setMaximumSize(new Dimension(Settings.DISPLAY_WIDTH, Settings.DISPLAY_HEIGHT));
			lblDisplay.setHorizontalAlignment(SwingConstants.RIGHT);		
			lblDisplay.setFont(new Font("Serif", Font.PLAIN, 25));
			
			lblTopDisplay = new JLabel();
			lblTopDisplay.setOpaque(true);
			lblTopDisplay.setBackground(Color.WHITE);
			
			lblTopDisplay.setMinimumSize(new Dimension(Settings.DISPLAY_WIDTH / 2, Settings.DISPLAY_HEIGHT / 2));
			lblTopDisplay.setPreferredSize(new Dimension(Settings.DISPLAY_WIDTH / 2, Settings.DISPLAY_HEIGHT / 2));
			lblTopDisplay.setMaximumSize(new Dimension(Settings.DISPLAY_WIDTH / 2, Settings.DISPLAY_HEIGHT / 2));
			lblTopDisplay.setHorizontalAlignment(SwingConstants.CENTER);
			lblTopDisplay.setFont(new Font("Serif", Font.PLAIN, 25));
			
			add(lblTopDisplay);
			add(lblDisplay);
			setVisible(true);
		}
		
		/**
		 * @return The bottom display for the view
		 */
		public JLabel getDisplay() {
			return this.lblDisplay;
		}
		
		/**
		 * @return The top display for the view
		 */
		public JLabel getTopDisplay() {
			return this.lblTopDisplay;
		}
	}
}