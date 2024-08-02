import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Converter extends JFrame implements ActionListener 
{

    private JLabel amountLabel, fromCurrencyLabel, toCurrencyLabel, resultLabel;
    private JTextField amountField;
    private JComboBox<String> fromCurrencyBox, toCurrencyBox;
    private JButton convertBtn;

    private final double[][] exchangeRates = 
    {
        {1, 83.40, 0.92}, // USD to USD, INR, EUR
        {0.012, 1, 0.011}, // INR to USD, INR, EUR
        {1.08, 90.40, 1},   // EUR to USD, INR, EUR
        {1,2,3}
    };

    public Converter() 
    {
        super("Currency Converter");

        amountLabel = createLabel("Enter amount:");
        fromCurrencyLabel = createLabel("From currency:");
        toCurrencyLabel = createLabel("To currency:");
        resultLabel = createLabel("Converted amount:");

        amountField = new JTextField(10);
        
        String[] currencies = {"USD", "INR", "EUR","abc"};
        fromCurrencyBox = new JComboBox<>(currencies);
        toCurrencyBox = new JComboBox<>(currencies);

        convertBtn = new JButton("Convert");
        convertBtn.addActionListener(this);

        JPanel mainPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        mainPanel.add(amountLabel);
        mainPanel.add(amountField);
        mainPanel.add(fromCurrencyLabel);
        mainPanel.add(fromCurrencyBox);
        mainPanel.add(toCurrencyLabel);
        mainPanel.add(toCurrencyBox);
        mainPanel.add(resultLabel);
        mainPanel.add(convertBtn);

        add(mainPanel);
        setSize(600, 300);
        setVisible(true);
    }

    private JLabel createLabel(String text) 
    {
        JLabel label = new JLabel(text);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        return label;
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if (e.getSource() == convertBtn) 
        {
            try 
            {
                double amount = Double.parseDouble(amountField.getText());
                int fromIndex = fromCurrencyBox.getSelectedIndex();
                int toIndex = toCurrencyBox.getSelectedIndex();

                if (fromIndex == toIndex) {
                    resultLabel.setText("Please select different currencies");
                } else {
                    double convertedAmount = amount * exchangeRates[fromIndex][toIndex];
                    resultLabel.setText(String.format("%.2f", convertedAmount) + " " + toCurrencyBox.getSelectedItem());
                }
            } 

            catch (NumberFormatException ex) 
            {
                resultLabel.setText("Invalid input. Please enter a numeric value.");
            }
        }
    }

    public static void main(String[] args) 
    {
        new Converter();
    }
}
