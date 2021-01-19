import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator implements ActionListener{

    JFrame frame;
    ImageIcon logo;
    JTextField results;
    JButton [] numButtons = new JButton[10];
    JButton [] funcButtons = new JButton[10];
    Font font;
    JPanel buttons;
    float num1=0, num2=0;
    char operator = ' ';

    Calculator(){

        /* frame */
        logo = new ImageIcon("logo.jpg");
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());
        frame.setIconImage(logo.getImage());

        font = new Font("Arial", Font.PLAIN, 30);

        /* results */
        results = new JTextField();
        results.setPreferredSize(new Dimension(300, 90));
        results.setFont(font);
        results.setBackground(Color.LIGHT_GRAY);
        results.setBorder(BorderFactory.createEmptyBorder(0 ,5, 0, 0));
        results.setEditable(false);

        /* buttons */
        for (int i=0; i<10; i++){
            numButtons[i] = new JButton(String.valueOf(i));
            numButtons[i].setFont(font);
            numButtons[i].setFocusable(false);
            numButtons[i].setPreferredSize(new Dimension(75, 70));
            numButtons[i].setMinimumSize(new Dimension(75, 70));
            numButtons[i].addActionListener(this);
        }

        funcButtons[0] = new JButton("+");
        funcButtons[1] = new JButton("-");
        funcButtons[2] = new JButton("x");
        funcButtons[3] = new JButton("\u00F7"); // division (/)
        funcButtons[4] = new JButton(".");
        funcButtons[5] = new JButton("=");
        funcButtons[6] = new JButton("\u2190"); // delete
        funcButtons[7] = new JButton("C");
        funcButtons[8] = new JButton("+/-");
        funcButtons[9] = new JButton("\u221A"); //square root

        for(int i=0; i<10; i++){
            funcButtons[i].setFont(font);
            funcButtons[i].setFocusable(false);
            funcButtons[i].setPreferredSize(new Dimension(75, 70));
            funcButtons[i].setMinimumSize(new Dimension(75, 70));
            funcButtons[i].addActionListener(this);
        }

        /* adds the buttons to the panel */
        buttons = new JPanel(new GridLayout(5, 4));

        buttons.add(funcButtons[7]);
        buttons.add(funcButtons[6]);
        buttons.add(funcButtons[9]);
        buttons.add(funcButtons[3]);

        buttons.add(numButtons[7]);
        buttons.add(numButtons[8]);
        buttons.add(numButtons[9]);
        buttons.add(funcButtons[2]);

        buttons.add(numButtons[4]);
        buttons.add(numButtons[5]);
        buttons.add(numButtons[6]);
        buttons.add(funcButtons[1]);

        buttons.add(numButtons[1]);
        buttons.add(numButtons[2]);
        buttons.add(numButtons[3]);
        buttons.add(funcButtons[0]);

        buttons.add(funcButtons[8]);
        buttons.add(numButtons[0]);
        buttons.add(funcButtons[4]);
        buttons.add(funcButtons[5]);

        /* adds the components to the frame */
        frame.add(results, BorderLayout.NORTH);
        frame.add(buttons, BorderLayout.CENTER);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public String getResults(){
        return results.getText();
    }

    public void setResults(String string){
        results.setText(string);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        /* input numbers */
        for(int i=0; i<10; i++)
            if(e.getSource() == numButtons[i] && !getResults().equals("0"))
                setResults(getResults() + i);

        /* functions */
        if(getResults().length() > 0){

            /* (+) */
            if(e.getSource() == funcButtons[0]){
                num1 = Float.parseFloat(getResults());
                operator = '+';
                setResults("");
                for(int i=0; i<4; i++){
                    funcButtons[i].setEnabled(false);
                }
            }

            /* (-) */
            if(e.getSource() == funcButtons[1]){
                num1 = Float.parseFloat(getResults());
                operator = '-';
                setResults("");
                for(int i=0; i<4; i++){
                    funcButtons[i].setEnabled(false);
                }
            }

            /* (x) */
            if(e.getSource() == funcButtons[2]){
                num1 = Float.parseFloat(getResults());
                operator = '*';
                setResults("");
                for(int i=0; i<4; i++){
                    funcButtons[i].setEnabled(false);
                }
            }

            /* (division (/) ) */
            if(e.getSource() == funcButtons[3]){
                num1 = Float.parseFloat(getResults());
                operator = '/';
                setResults("");
                for(int i=0; i<4; i++){
                    funcButtons[i].setEnabled(false);
                }
            }

            /* (=) */
            if(e.getSource() == funcButtons[5] && operator != ' '){

                num2 = Float.parseFloat(getResults());
                boolean divByZero = false;
                switch (operator) {
                    case '+' -> {
                        num2 = num1 + num2;
                        operator = ' ';
                    }
                    case '-' -> {
                        num2 = num1 - num2;
                        operator = ' ';
                    }
                    case '*' -> {
                        num2 = num1 * num2;
                        operator = ' ';
                    }
                    case '/' -> {
                        if (num2 == 0)
                            divByZero = true;
                        num2 = num1 / num2;
                        operator = ' ';
                    }
                }

                for(int i=0; i<4; i++){
                    funcButtons[i].setEnabled(true);
                }

                if(divByZero){
                    setResults("Cannot divide by zero");
                       for(int i=0; i<10; i++){
                           numButtons[i].setEnabled(false);
                           //sets all the buttons enabled except C (Clear)
                           if(i!=7)
                               funcButtons[i].setEnabled(false);
                       }
                }
                else{
                    if(num2 % 1 != 0)
                        setResults(String.valueOf(num2));
                    else
                        setResults(String.valueOf((int)num2));
                }

            }

            /* (.) */
            if(e.getSource() == funcButtons[4] && !getResults().contains("."))
                setResults(getResults() + '.');

            /* (delete) */
            if(e.getSource() == funcButtons[6])
                setResults(getResults().substring(0, getResults().length() - 1));

            /* (+/-) */
            if(e.getSource() == funcButtons[8]){
                float temp = Float.parseFloat(getResults());
                temp*=-1;
                if(temp % 1 != 0)
                    setResults(String.valueOf(temp));
                else
                    setResults(String.valueOf((int)temp));
            }

            /* (square root) */
            if(e.getSource() == funcButtons[9]){
                float temp = Float.parseFloat(getResults());
                if(temp<0){
                    setResults("Invalid input");
                    for(int i=0; i<10; i++){
                        numButtons[i].setEnabled(false);
                        //sets all the buttons enabled except C (Clear)
                        if(i!=7)
                            funcButtons[i].setEnabled(false);
                    }
                }
                else{
                    temp = (float) Math.sqrt(temp);
                    if(temp % 1 != 0)
                        setResults(String.valueOf(temp));
                    else
                        setResults(String.valueOf((int)temp));
                }
            }

        }

        /* (C) */
        if(e.getSource() == funcButtons[7]){
            setResults("");
            for(int i=0; i<10; i++){
                numButtons[i].setEnabled(true);
                funcButtons[i].setEnabled(true);
            }
        }

    }

    public static void main(String[] args){
        new Calculator();
    }

}