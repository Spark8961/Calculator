import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserInterface extends JFrame implements ActionListener {

    private final int HEIGHT = 500;
    private final int WIDTH = 400;
    private JPanel container = new JPanel();
    private JTextField textField = new JTextField();
    private JPanel buttonPanel = new JPanel(new GridLayout(4, 4)); 
    private String[] buttonText = {

        "1", "2", "3", "+",
        "4", "5", "6", "-", 
        "7", "8", "9", "*",
        "0", ".", "/", "="
    };
    private Calculation calculation = new Calculation(); 
    
    public UserInterface(){

        setBounds();
        setLayout();
        setComponentProperties();
        addComponents();
        showFrame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() instanceof JButton){

            String current = textField.getText();

            JButton button = (JButton) e.getSource();
            String add = button.getText();

            if(add != "="){

                current += add;
            }else{

                current = Float.toString(calculation.calculate(current));
            }
            System.out.println(current);
            textField.setText(current);
        }  
    }

    private void setBounds(){

        textField.setPreferredSize(new Dimension(WIDTH, HEIGHT/5));
        buttonPanel.setPreferredSize(new Dimension(WIDTH, (4*HEIGHT)/5));
    }

    private void setComponentProperties(){

        textField.setHorizontalAlignment(SwingConstants.RIGHT);
        textField.setEditable(false);
    }

    private void setLayout(){

        container.setLayout(new BorderLayout());
    }
    
    private void addComponents(){

        container.add(BorderLayout.NORTH, textField);

        addButtons();
        container.add(BorderLayout.CENTER, buttonPanel);
    }

    private void addButtons(){

        for(int i = 0; i < buttonText.length; i++ ){

            JButton button = new JButton(buttonText[i]);
            button.addActionListener(this);
            buttonPanel.add(button);
        }
    }

    private void showFrame(){

        setTitle("Simple Calculator");
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add(container);
        setVisible(true);
    }
}
