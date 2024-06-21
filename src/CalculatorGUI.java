import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorGUI extends JFrame implements ActionListener {

    private JTextField inputField;
    private String num1 = "";
    private String num2 = "";
    private String operator = "";

    public CalculatorGUI() {
        createUI();
    }

    private void createUI() {
        setTitle("Calculator");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        inputField = new JTextField();
        inputField.setEditable(false);
        inputField.setFont(new Font("Times new roman", Font.BOLD, 24));
        inputField.setHorizontalAlignment(JTextField.RIGHT);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4, 20, 20));

        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", "C", "=", "+"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Times new roman", Font.BOLD, 24));
            button.addActionListener(this);
            panel.add(button);
        }

        setLayout(new BorderLayout());
        add(inputField, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if ((command.charAt(0) >= '0' && command.charAt(0) <= '9') || command.equals(".")) {
            if (!operator.equals("")) {
                num2 += command;
            } else {
                num1 += command;
            }
            inputField.setText(num1 + operator + num2);
        } else if (command.equals("C")) {
            num1 = operator = num2 = "";
            inputField.setText("");
        } else if (command.equals("=")) {
            double result;
            switch (operator) {
                case "+":
                    result = Double.parseDouble(num1) + Double.parseDouble(num2);
                    break;
                case "-":
                    result = Double.parseDouble(num1) - Double.parseDouble(num2);
                    break;
                case "*":
                    result = Double.parseDouble(num1) * Double.parseDouble(num2);
                    break;
                case "/":
                    result = Double.parseDouble(num1) / Double.parseDouble(num2);
                    break;
                default:
                    return;
            }
            inputField.setText(num1 + operator + num2 + "=" + result);
            num1 = Double.toString(result);
            operator = num2 = "";
        } else {
            if (operator.equals("") || num2.equals("")) {
                operator = command;
            } else {
                double result;
                switch (operator) {
                    case "+":
                        result = Double.parseDouble(num1) + Double.parseDouble(num2);
                        break;
                    case "-":
                        result = Double.parseDouble(num1) - Double.parseDouble(num2);
                        break;
                    case "*":
                        result = Double.parseDouble(num1) * Double.parseDouble(num2);
                        break;
                    case "/":
                        result = Double.parseDouble(num1) / Double.parseDouble(num2);
                        break;
                    default:
                        return;
                }
                num1 = Double.toString(result);
                operator = command;
                num2 = "";
            }
            inputField.setText(num1 + operator + num2);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CalculatorGUI calculator = new CalculatorGUI();
            calculator.setVisible(true);
        });
    }
}
