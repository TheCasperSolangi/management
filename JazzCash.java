package travel.management.system;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class JazzCash extends JFrame {
    private JProgressBar progressBar;
    private JLabel statusLabel;

    JazzCash() {
        JEditorPane j = new JEditorPane();
        j.setEditable(false);

        try {
            j.setPage("https://paytm.com/electricity-bill-payment");
        } catch (Exception e) {
            j.setContentType("text/html");
            j.setText("<html>Could not load</html>");
        }

        JScrollPane scrollPane = new JScrollPane(j);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(scrollPane);
        setPreferredSize(new Dimension(800, 600));

        statusLabel = new JLabel("Initiating Payment. Please Wait ");
        statusLabel.setBounds(50, 470, 300, 30);
        j.add(statusLabel);

        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);
        progressBar.setBounds(50, 500, 700, 30);
        j.add(progressBar);

        JButton back = new JButton("Back");
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        back.setBounds(610, 20, 80, 40);
        j.add(back);

        setSize(800, 600);
        setLocation(600, 220);
        setVisible(true);

        simulatePayment();
    }

    private void simulatePayment() {
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                for (int i = 0; i <= 100; i++) {
                    progressBar.setValue(i);
                    Thread.sleep(100);
                }
                return null;
            }

            @Override
            protected void done() {
                statusLabel.setText("Payment Successful");
                JOptionPane.showMessageDialog(JazzCash.this, "Payment Successful", "Success", JOptionPane.INFORMATION_MESSAGE);
                setVisible(false);
            }
        };

        worker.execute();
    }

    public static void main(String[] args) {
        new JazzCash().setVisible(true);
    }
}