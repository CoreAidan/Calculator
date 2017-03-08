package infamousone.main;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Calculator extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private static final int NONE = 0;
	private static final int ADD = 1;
	private static final int SUB = 2;
	private static final int MULT = 3;
	private static final int DIV = 4;
	
	private JButton[] buttons;
	private JButton addButton;
	private JButton subButton;
	private JButton multButton;
	private JButton divButton;
	private JButton equalButton;
	private JButton clearButton;
	private JButton decimalButton;
	
	private JTextField output;
	
	private double numberOne;
	private double numberTwo;
	
	private int action;
	
	private boolean decimal = false;
	
	public Calculator (){
		//setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel buttonPad = new JPanel();
		buttonPad.setLayout(new GridLayout(4, 3));
		buttonPad.setPreferredSize(new Dimension(171, 170));
		
		JPanel mathFunc = new JPanel();
		mathFunc.setLayout(new GridLayout(5, 1));
		mathFunc.setPreferredSize(new Dimension(171 / 3, 170));
		
		numberOne = 0;
		numberTwo = 0;
		
		buttons = new JButton[10];
		addButton = new JButton();
		subButton = new JButton();
		multButton = new JButton();
		divButton = new JButton();
		clearButton = new JButton();
		equalButton = new JButton();
		decimalButton = new JButton();
		
		output = new JTextField(20);
		
		addButton.setText("+");
		addButton.addActionListener(new ButtonListener());
		subButton.setText("-");
		subButton.addActionListener(new ButtonListener());
		multButton.setText("x");
		multButton.addActionListener(new ButtonListener());
		divButton.setText("/");
		divButton.addActionListener(new ButtonListener());
		clearButton.setText(" CE ");
		clearButton.addActionListener(new ButtonListener());
		equalButton.setText("=");
		equalButton.addActionListener(new ButtonListener());
		decimalButton.setText(".");
		decimalButton.addActionListener(new ButtonListener());
		
		for(int i = 0; i < buttons.length; i++){
			buttons[i] = new JButton();
			buttons[i].setText(Integer.toString(i));
			buttons[i].addActionListener(new ButtonListener());
		}
		//adding the components
		buttonPad.add(buttons[7]);
		buttonPad.add(buttons[8]);
		buttonPad.add(buttons[9]);
		buttonPad.add(buttons[4]);
		buttonPad.add(buttons[5]);
		buttonPad.add(buttons[6]);
		buttonPad.add(buttons[1]);
		buttonPad.add(buttons[2]);
		buttonPad.add(buttons[3]);
		buttonPad.add(buttons[0]);
		buttonPad.add(decimalButton);
		buttonPad.add(equalButton);
		
		mathFunc.add(clearButton);
		mathFunc.add(divButton);
		mathFunc.add(multButton);
		mathFunc.add(subButton);
		mathFunc.add(addButton);
		
		//equalAndClear.add(clearButton);
		//equalAndClear.add(equalButton);
		
		add(output);
		add(buttonPad);
		add(mathFunc);
		setPreferredSize(new Dimension(250, 275));
	}
	
	private class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent event) {
			for(int i = 0; i < buttons.length; i++){
				if(event.getSource().equals(buttons[i])){
					output.setText(output.getText()  + Integer.toString(i));
					if(action == ADD || action == SUB || action == MULT || action == DIV ){
						numberTwo = Double.parseDouble(output.getText());
					}
				}
			}
			if(event.getSource().equals(decimalButton) && !decimal){
				output.setText(output.getText() + decimalButton.getText());
				decimal = true;
			}else if(event.getSource().equals(addButton)){
				numberOne = Double.parseDouble(output.getText());
				action = ADD;
				output.setText("");
			}else if(event.getSource().equals(subButton)){
				numberOne = Double.parseDouble(output.getText());
				action = SUB;
				output.setText("");
			}else if(event.getSource().equals(multButton)){
				numberOne = Double.parseDouble(output.getText());
				action = MULT;
				output.setText("");
			}else if(event.getSource().equals(divButton)){
				numberOne = Double.parseDouble(output.getText());
				action = DIV;
				output.setText("");
			}else if(event.getSource().equals(equalButton)){
				if(action == ADD){
					numberOne += numberTwo;
					output.setText(Double.toString(numberOne));
				}else if(action == SUB){
					numberOne -= numberTwo;
					output.setText(Double.toString(numberOne));
				}else if(action == MULT){
					numberOne *= numberTwo;
					output.setText(Double.toString(numberOne));
				}
				else if(action == DIV){
					numberOne /= numberTwo;
					output.setText(Double.toString(numberOne));
				}
			}else if(event.getSource().equals(clearButton)){
				numberOne = 0;
				numberTwo = 0;
				action = NONE;
				decimal = false;
				output.setText("");
			}
		}
	}
	
	public static void main(String[] args){
		JFrame frame = new JFrame ("Calculator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Calculator calculator = new Calculator();
		frame.getContentPane().add(calculator);
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
