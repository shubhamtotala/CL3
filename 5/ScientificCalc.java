/*Assignment no:5
- A Mobile App for Calculator having Trigonometry functionality is to be designed and tested. The data storage uses 1.textles, 2. XML Use latest open source software modeling, Designing and testing tool/Scrum-it. Implement the design using HTML-5/Scala/ Python/Java/C++/Rubi on Rails. Perform Positive and Negative testing.
----------------------------------------------------------------
*/
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class ScientificCalc implements ActionListener
{
	JFrame frame;
	JPanel panel;
	JTextField ansfield;
	JButton buttons[];
	String buttonsText[]={"C","MC","MR","M+","M-","sqrt","X^2","1/X","SIN","COS","TAN","+/-","0","1","2","3","4","5","6","7","8","9","+","-","*","/",".","="};
int maxx=400,maxy=500;


static final String DIGITS = "0123456789.";
 Boolean userIsInTheMiddleOfTypingANumber = false;
 CalculatorBrain mCalculatorBrain=new CalculatorBrain();

	public ScientificCalc() {
		frame = new JFrame("Scientific Calculator");
		frame.setSize(maxx, maxy);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel = new JPanel();
		panel.setLayout(null);
		
		ansfield = new JTextField();
		ansfield.setBounds(10,30,maxx-40,40);
		ansfield.setHorizontalAlignment(JTextField.RIGHT);


		buttons=new JButton[buttonsText.length];

		int currentx=0,currenty=0;
		
		for(int i=0;i<buttonsText.length;i++)
		{
			buttons[i]=new JButton(buttonsText[i]);
			buttons[i].addActionListener(this);
			if(currentx==0&&currenty==0)
			{
				buttons[i].setBounds(10,100,70,30);
				currentx=10;
				currenty=100;
			}
			else
			{
				if(currentx<maxx-100)
				{
				currentx+=100;
				buttons[i].setBounds(currentx,currenty,70,30);
				}
				else
				{
					currentx=10;
					currenty+=50;
					buttons[i].setBounds(currentx,currenty,70,30);	
				}
			
			}
		panel.add(buttons[i]);
		}
		
		panel.add(ansfield);
		frame.add(panel);
		frame.setVisible(true);
	}
	public static void main(String args[])
	{

		new ScientificCalc();	
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		String buttonObj="";		

		for(int i=0;i<buttonsText.length;i++)
		{
			if(ae.getSource()==buttons[i])
			{
			buttonObj=buttons[i].getText().toString();
			
			break;
			}
		}
		calc(buttonObj);
	}
	private void calc(String buttonObj) {
		// TODO Auto-generated method stub
if (DIGITS.contains(buttonObj)) {

            
            if (userIsInTheMiddleOfTypingANumber) {

                if (buttonObj.equals(".") && ansfield.getText().toString().contains(".")) {
                    
                } else {
                    ansfield.setText(ansfield.getText()+buttonObj);
                }

            } else {

                if (buttonObj.equals(".")) {
                    
                   
                    ansfield.setText(0 + buttonObj);
                } else {
                    ansfield.setText(buttonObj);
                }

                userIsInTheMiddleOfTypingANumber = true;
            }

        } else {
            
            if (userIsInTheMiddleOfTypingANumber) {

                mCalculatorBrain.setOperand(Double.parseDouble(ansfield.getText().toString()));
                userIsInTheMiddleOfTypingANumber = false;
            }
            try
            {
            mCalculatorBrain.performOperation(buttonObj);
            }catch(Exception e){}
            ansfield.setText(""+mCalculatorBrain.getResult());

        }
	}

public class CalculatorBrain {

    
    private double mOperand;
    private double mWaitingOperand;
    private String mWaitingOperator;
    private double mCalculatorMemory;


    public static final String ADD = "+";
    public static final String SUBTRACT = "-";
    public static final String MULTIPLY = "*";
    public static final String DIVIDE = "/";

    public static final String CLEAR = "C" ;
    public static final String CLEARMEMORY = "MC";
    public static final String ADDTOMEMORY = "M+";
    public static final String SUBTRACTFROMMEMORY = "M-";
    public static final String RECALLMEMORY = "MR";
    public static final String SQUAREROOT = "sqrt";
    public static final String SQUARED = "X^2";
    public static final String INVERT = "1/X";
    public static final String TOGGLESIGN = "+/-";
    public static final String SINE = "SIN";
    public static final String COSINE = "COS";
    public static final String TANGENT = "TAN";
    
    PrintWriter writer;
    public CalculatorBrain() {
        mOperand = 0;
        mWaitingOperand = 0;
        mWaitingOperator = "";
        mCalculatorMemory = 0;
    }

    public void setOperand(double operand) {
        mOperand = operand;
    }

   public double getResult() {
        return mOperand;
    }
   
    protected double performOperation(String operator) throws Exception {

        if (operator.equals(CLEAR)) {
            mOperand = 0;
            mWaitingOperator = "";
            mWaitingOperand = 0;
             mCalculatorMemory = 0;
        } else if (operator.equals(CLEARMEMORY)) {
            mCalculatorMemory = 0;
 	    System.out.println(""+mOperand);
        } else if (operator.equals(ADDTOMEMORY)) {
            mCalculatorMemory = mCalculatorMemory + mOperand;
             System.out.println("added to memory"+mOperand);
        
        } else if (operator.equals(SUBTRACTFROMMEMORY)) {
            mCalculatorMemory = mCalculatorMemory - mOperand;
            
         System.out.println("removed from memory"+mOperand);
        } else if (operator.equals(RECALLMEMORY)) {
 	System.out.println("display from memory"+mOperand);
            mOperand = mCalculatorMemory;
        } else if (operator.equals(SQUAREROOT)) {
 	System.out.println("squareroot is"+mOperand);
            mOperand = Math.sqrt(mOperand);

        } else if (operator.equals(SQUARED)) {
            mOperand = mOperand * mOperand;
 		System.out.println("square is"+mOperand);
        } else if (operator.equals(INVERT)) {
            if (mOperand != 0) {
                mOperand = 1 / mOperand;
 		System.out.println("invert is"+mOperand);
            }
        } else if (operator.equals(TOGGLESIGN)) {
            mOperand = -mOperand;
 		System.out.println(""+mOperand);
        } else if (operator.equals(SINE)) {
            mOperand = Math.sin(Math.toRadians(mOperand)); 
            System.out.println("sine is"+mOperand);
        } else if (operator.equals(COSINE)) {
            mOperand = Math.cos(Math.toRadians(mOperand)); 
 		System.out.println("cosine is"+mOperand);
        } else if (operator.equals(TANGENT)) {
            mOperand = Math.tan(Math.toRadians(mOperand)); 
 		System.out.println("tangent is"+mOperand);
        } else {
            performWaitingOperation();
            mWaitingOperator = operator;
            mWaitingOperand = mOperand;
 //System.out.println(""+mOperand);
        }
        
        return mOperand;
    }

    protected void performWaitingOperation() {

        if (mWaitingOperator.equals(ADD)) {
            mOperand = mWaitingOperand + mOperand;
 System.out.println("addition is "+mOperand);
        } else if (mWaitingOperator.equals(SUBTRACT)) {
            mOperand = mWaitingOperand - mOperand;
 System.out.println("subtraction is"+mOperand);
        } else if (mWaitingOperator.equals(MULTIPLY)) {
            mOperand = mWaitingOperand * mOperand;
 System.out.println("multiplication is "+mOperand);
        } else if (mWaitingOperator.equals(DIVIDE)) {
            if (mOperand != 0) {
                mOperand = mWaitingOperand / mOperand;
 System.out.println("division is "+mOperand);
            }
        }
    }
}

}
/*-------------------------------------------------------------
projadmin@proj-25:~/Desktop$ javac ScientificCalc.java
projadmin@proj-25:~/Desktop$ java ScientificCalc
addition is 136.0
subtraction is113.0
square is12769.0
division is 185.05797101449275
sine is-0.08816363289057813
cosine is0.9999988161311083
squareroot is69.0
squareroot is8.306623862918075


*/
