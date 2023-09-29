import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserInterface extends JFrame implements ActionListener {

    private final int HEIGHT = 500;
    private final int WIDTH = 400;
 
    private JPanel container = new JPanel();
    private JTextField textField = new JTextField();
    private JPanel buttonPanel = new JPanel(new GridLayout(4, 4));
    private JButton clearButton = new JButton("CLEAR");

    private String[] buttonText = {

        "1", "2", "3", "+",
        "4", "5", "6", "-", 
        "7", "8", "9", "*",
        "0", ".", "/", "=",
    };
    
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

            switch(add){

                case "CLEAR" :
                    current = "";
                break;
                case "=" :
                    try {
                                                
                        current = new Calculation(current).evaluate();
                    } catch (Exception ex) {

                        ex.printStackTrace();
                        current = "ERROR";
                    } 
                break;
                default :
                    current += add;
                break;
            }
            textField.setText(current);
        }  
    }

    private void setBounds(){

        container.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        textField.setPreferredSize(new Dimension(WIDTH, HEIGHT / 5));
        buttonPanel.setPreferredSize(new Dimension(WIDTH, (4 * HEIGHT) / 5));
        clearButton.setPreferredSize(new Dimension(WIDTH, HEIGHT / 7));
    }

    private void setComponentProperties(){

        textField.setHorizontalAlignment(SwingConstants.RIGHT);
        textField.setFont(new Font("Arial", Font.BOLD , 25));
        textField.setEditable(false);
    }

    private void setLayout(){

        container.setLayout(new BorderLayout());
    }
    
    private void addComponents(){

        container.add( textField, BorderLayout.NORTH);
        container.add(clearButton, BorderLayout.SOUTH);

        addButtons();
        container.add( buttonPanel, BorderLayout.CENTER);
    }

    private void addButtons(){

        for(int i = 0; i < buttonText.length; i++ ){

            JButton button = new JButton(buttonText[i]);
            button.addActionListener(this);
            buttonPanel.add(button);
        }
        clearButton.addActionListener(this);
    }

    private void showFrame(){

        setTitle("Simple Calculator");
        setResizable(false);
        add(container);
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
