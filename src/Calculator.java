import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class Calculator implements ActionListener{

    JFrame frame;
    ImageIcon logo;
    JTextField results;
    JButton [] numButtons = new JButton[10];
    JButton [] funcButtons = new JButton[8];
    Font font;
    JPanel buttons;
    GridBagConstraints gbc;
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
        funcButtons[2] = new JButton("*");
        funcButtons[3] = new JButton("/");
        funcButtons[4] = new JButton(".");
        funcButtons[5] = new JButton("=");
        funcButtons[6] = new JButton("<-");
        funcButtons[7] = new JButton("C");

        for(int i=0; i<8; i++){
            funcButtons[i].setFont(font);
            funcButtons[i].setFocusable(false);
            funcButtons[i].setPreferredSize(new Dimension(75, 70));
            funcButtons[i].setMinimumSize(new Dimension(75, 70));
            funcButtons[i].addActionListener(this);
        }

        /* panel with buttons */
        buttons = new JPanel(new GridBagLayout());
        gbc = new GridBagConstraints();

        /* 1) Row */
        gbc.gridx = 0;
        gbc.gridy = 0;
        buttons.add(funcButtons[7], gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        buttons.add(funcButtons[6], gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        buttons.add(funcButtons[3], gbc);

        gbc.gridx = 3;
        gbc.gridy = 0;
        buttons.add(funcButtons[2], gbc);

        /* 2) Row */
        gbc.gridx = 0;
        gbc.gridy = 1;
        buttons.add(numButtons[7], gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        buttons.add(numButtons[8], gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        buttons.add(numButtons[9], gbc);

        gbc.gridx = 3;
        gbc.gridy = 1;
        buttons.add(funcButtons[1], gbc);

        /* 3) Row */
        gbc.gridx = 0;
        gbc.gridy = 2;
        buttons.add(numButtons[4], gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        buttons.add(numButtons[5], gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        buttons.add(numButtons[6], gbc);

        gbc.gridx = 3;
        gbc.gridy = 2;
        buttons.add(funcButtons[0], gbc);

        /* 4) Row */
        gbc.gridx = 0;
        gbc.gridy = 3;
        buttons.add(numButtons[1], gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        buttons.add(numButtons[2], gbc);

        gbc.gridx = 2;
        gbc.gridy = 3;
        buttons.add(numButtons[3], gbc);

        gbc.gridx = 3;
        gbc.gridy = 3;
        gbc.gridheight=2;
        gbc.fill = GridBagConstraints.VERTICAL;
        buttons.add(funcButtons[5], gbc);

        /* 5) Row */
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth=2;
        gbc.gridheight=1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        buttons.add(numButtons[0], gbc);

        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.gridwidth=1;
        gbc.gridheight=1;
        buttons.add(funcButtons[4], gbc);

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

            /* (*) */
            if(e.getSource() == funcButtons[2]){
                num1 = Float.parseFloat(getResults());
                operator = '*';
                setResults("");
                for(int i=0; i<4; i++){
                    funcButtons[i].setEnabled(false);
                }
            }

            /* (/) */
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
                           if (i<7)
                               funcButtons[i].setEnabled(false);
                       }
                }
                else{
                    NumberFormat nf = NumberFormat.getNumberInstance(Locale.US);
                    DecimalFormat formatter = (DecimalFormat) nf;
                    formatter.setMaximumFractionDigits(5);
                    setResults(nf.format(num2));
                }

            }

            /* (.) */
            if(e.getSource() == funcButtons[4] && !getResults().contains("."))
                setResults(getResults() + '.');

            /* (<-) */
            if(e.getSource() == funcButtons[6])
                setResults(getResults().substring(0, getResults().length() - 1));
        }

        /* (C) function */
        if(e.getSource() == funcButtons[7]){
            setResults("");
            for(int i=0; i<10; i++){
                numButtons[i].setEnabled(true);
                if (i<8)
                    funcButtons[i].setEnabled(true);
            }
        }

    }

    public static void main(String[] args){
        new Calculator();
    }

}