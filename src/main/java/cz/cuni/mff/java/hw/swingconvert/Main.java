package cz.cuni.mff.java.hw.swingconvert;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ExecutionException;

public class Main {
    private static JTextField textField = new JTextField();
    private static JTextArea textArea = new JTextArea();
    private static JComboBox<String> options = new JComboBox<String>();

    private static void createAndShowGUI() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Converter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container container = frame.getContentPane();

        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        container.add(textField, BorderLayout.NORTH);

        textArea.setEditable(false);

        options.addItem("foot to cm");
        options.addItem("cm to foot");
        options.addItem("inch to cm");

        container.add(options);

        JLabel label = new JLabel(" ");
        label.setPreferredSize(new Dimension(300, 100));
        container.add(label);

        textField.addActionListener(e -> {
            Double num = Double.parseDouble(textField.getText().trim());
            if (num != null) {
                SwingWorker<String, Object> sw = new SwingWorker<String, Object>() {
                    @Override
                    protected String doInBackground() {
                        String[] units = options.getSelectedItem().toString().split(" to ");
                        // Converter converter = new Converter(num, units[0], units[1]);
                        double result;

                        if (units[0].equals("foot")) {
                            ConverterFromFootToCm converterFromFootToCm = new ConverterFromFootToCm(num, units[0], units[1]);
                            result = converterFromFootToCm.convert(num);
                        } else if(units[0].equals("cm")) {
                            ConverterFromCmToFoot converterFromCmToFoot = new ConverterFromCmToFoot(num, units[0], units[1]);
                            result = converterFromCmToFoot.convert(num);
                        } else {
                            InchToCm inchToCm = new InchToCm(num);
                            result = inchToCm.convert(num);
                        }
                        return String.valueOf(result);
                    }

                    @Override
                    protected void done() {
                        try {
                            label.setText(get());
                        } catch (InterruptedException | ExecutionException ex) {
                            System.out.println("nastala chyba");
                            System.exit(0);
                        }
                        textArea.setEnabled(true);
                    }
                };
                sw.execute();
            }
        });

        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::createAndShowGUI);
    }
}
