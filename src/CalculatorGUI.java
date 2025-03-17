import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorGUI extends JFrame {
    private JPanel calcPanel;
    private JTextField zaehler1;
    private JTextField nenner2;
    private JTextField zaehler2;
    private JTextField nenner1;
    private JComboBox operatoren;
    private JLabel zaehlerErgebnis;
    private JLabel nennerErgebnis;
    private JButton berechnenBtn;

    public CalculatorGUI() {
        setTitle("Buchrechner");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        setContentPane(calcPanel);
        pack();
        setVisible(true);

        berechnenBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                berechneBruch();
            }
        });
    }
    private void berechneBruch() {
        try {
            int z1 = Integer.parseInt(zaehler1.getText());
            int n1 = Integer.parseInt(nenner1.getText());
            int z2 = Integer.parseInt(zaehler2.getText());
            int n2 = Integer.parseInt(nenner2.getText());

            int ergZaehler = 0;
            int ergNenner = 0;

            String operator = (String) operatoren.getSelectedItem();

            switch (operator) {
                case "+":
                    ergZaehler = z1 * n2 + z2 * n1;
                    ergNenner = n1 * n2;
                    break;
                case "-":
                    ergZaehler = z1 * n2 - z2 * n1;
                    ergNenner = n1 * n2;
                    break;
                case "*":
                    ergZaehler = z1 * z2;
                    ergNenner = n1 * n2;
                    break;
                case "/":
                    ergZaehler = z1 * n2;
                    ergNenner = n1 * z2;
                    break;
            }

            int ggt = berechneGGT(ergZaehler, ergNenner);
            ergZaehler /= ggt;
            ergNenner /= ggt;

            zaehlerErgebnis.setText(String.valueOf(ergZaehler));
            nennerErgebnis.setText(String.valueOf(ergNenner));

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Bitte gültige ganze Zahlen eingeben.");
        }
        zaehlerErgebnis.setVisible(true);
        nennerErgebnis.setVisible(true);
    }

    // Methode zur Berechnung des größten gemeinsamen Teilers (GGT)
    private int berechneGGT(int a, int b) {
        if (b == 0) {
            return Math.abs(a);
        }
        return berechneGGT(b, a % b);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(CalculatorGUI::new);
    }
}

