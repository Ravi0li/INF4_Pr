package crypto;

import java.util.*;  
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Main {
	
    public static void main(String[] args) {

        Runnable guiCreator = new Runnable() {
        	ArrayList<basicCrypt> cryptoRoutines;
        	
        	private void AddClasses () {
        		cryptoRoutines = new ArrayList<basicCrypt>();
        		
        		// -----------------------------------------------------------------
        		// ---------- Hier die neuen Klassen ergänzen ----------------------
        		// -----------------------------------------------------------------
        		
        		cryptoRoutines.add(new calculatorClass());
        		cryptoRoutines.add(new wepEncryptClass());
				cryptoRoutines.add(new wepDecryptClass());
				cryptoRoutines.add(new CaesarChiffreClass());        		
        		// -----------------------------------------------------------------
        		// -----------------------------------------------------------------
        	}
        	
            public void run() {
            	// Fenster
                JFrame fenster = new JFrame("AUT4 - Kryptografie");
                fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                fenster.setMinimumSize(new Dimension(500, 500));
                fenster.setLayout(new BoxLayout(fenster.getContentPane(), BoxLayout.Y_AXIS));
                fenster.setSize(300, 200);
                
                // Komponenten zur Auswahl
                AddClasses();
                ArrayList<String> values = new ArrayList<String>();
                for (basicCrypt b: cryptoRoutines)
                	values.add(b.getName());
                String[] stringValues = values.toArray(new String[0]);
                JComboBox combBox = new JComboBox(stringValues);
                combBox.setMaximumSize(new Dimension(20000,30));
                combBox.setSelectedIndex(0);
                
                // Komponenten für Eingabe
                JLabel labelIn1 = new JLabel("Eingabe 1:");
                labelIn1.setMinimumSize(new Dimension(20000,10));
                JTextField input1 = new JTextField();
                input1.setMaximumSize(new Dimension(20000,30));
                JLabel labelIn2 = new JLabel("Eingabe 2:");
                labelIn2.setMinimumSize(new Dimension(20000,10));
                JTextField input2 = new JTextField();
                input2.setMaximumSize(new Dimension(20000,30));
                JLabel labelIn3 = new JLabel("Eingabe 3:");
                labelIn3.setMinimumSize(new Dimension(20000,10));
                JTextField input3 = new JTextField();
                input3.setMaximumSize(new Dimension(20000,30));
                
                // Komponente Button
                JButton letsCrypt = new JButton("Berechnen");
                letsCrypt.setMaximumSize(new Dimension(20000,30));
                
                // Komponenten für Ausgabe
                JLabel labelOut1 = new JLabel("Ausgabe 1:");
                labelOut1.setMinimumSize(new Dimension(20000,10));
                JTextField output1 = new JTextField();
                output1.setMaximumSize(new Dimension(20000,30));
                JLabel labelOut2 = new JLabel("Ausgabe 2:");
                labelOut2.setMinimumSize(new Dimension(20000,10));
                JTextField output2 = new JTextField();
                output2.setMaximumSize(new Dimension(20000,30));
                JLabel labelOutLog = new JLabel("Log:");
                JTextArea outputLog = new JTextArea(5,30);
                outputLog.setFont(new Font("Courier New", Font.PLAIN, 12));
                JScrollPane scrollLogPane = new JScrollPane(outputLog);
                
                // GroupBox für Auswahl
                JPanel panelComboBox = new JPanel(new GridLayout(0, 1));
                panelComboBox.setLayout(new BoxLayout(panelComboBox, BoxLayout.Y_AXIS));
                panelComboBox.setBorder(BorderFactory.createTitledBorder("Verschlüsselung:"));
                panelComboBox.add(combBox);
                
                // GroupBox für Eingabe
                JPanel panelInput = new JPanel(new GridLayout(0, 1));
                panelInput.setLayout(new BoxLayout(panelInput, BoxLayout.Y_AXIS));
                panelInput.setBorder(BorderFactory.createTitledBorder("Eingabe:"));
                panelInput.add(labelIn1);
                panelInput.add(input1);
                panelInput.add(labelIn2);
                panelInput.add(input2);
                panelInput.add(labelIn3);
                panelInput.add(input3);
                
                // GroupBox für Ausgabe
                JPanel panelOutput= new JPanel();
                panelOutput.setLayout(new BoxLayout(panelOutput, BoxLayout.Y_AXIS));
                panelOutput.setBorder(BorderFactory.createTitledBorder("Ausgabe:"));
                panelOutput.add(labelOut1);
                panelOutput.add(output1);
                panelOutput.add(labelOut2);
                panelOutput.add(output2);
                panelOutput.add(labelOutLog);
                panelOutput.add(scrollLogPane);
              
                // Zum Fenster hinzufügen
                fenster.add(panelComboBox);
                fenster.add(panelInput);
                fenster.add(letsCrypt);
                fenster.add(panelOutput);                
                fenster.setVisible(true);
                
                // Action Listener Auswahl
                combBox.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent ev) {
                    	int id = combBox.getSelectedIndex();
                    	input1.setVisible(cryptoRoutines.get(id).getInput1Infos());
                    	labelIn1.setText(cryptoRoutines.get(id).getInput1InfosText());
                    	input2.setVisible(cryptoRoutines.get(id).getInput2Infos());
                    	labelIn2.setText(cryptoRoutines.get(id).getInput2InfosText());
                    	input3.setVisible(cryptoRoutines.get(id).getInput3Infos());
                    	labelIn3.setText(cryptoRoutines.get(id).getInput3InfosText());
                    	output1.setVisible(cryptoRoutines.get(id).getOutput1Infos());
                    	labelOut1.setText(cryptoRoutines.get(id).getOutput1InfosText());
                    	output2.setVisible(cryptoRoutines.get(id).getOutput2Infos());
                    	labelOut2.setText(cryptoRoutines.get(id).getOutput2InfosText());
                      }
                    });
                combBox.setSelectedIndex(0);
                
                // Action Listener Berechnen
                letsCrypt.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent ev) {
                    	int id = combBox.getSelectedIndex();
                    	cryptoRoutines.get(id).setInputs(input1.getText(), input2.getText(), input3.getText());
                    	cryptoRoutines.get(id).calculate();
                    	output1.setText(cryptoRoutines.get(id).getOutput1());
                    	output2.setText(cryptoRoutines.get(id).getOutput2());
                    	outputLog.setText(cryptoRoutines.get(id).getLog());
                      }
                    });
            }
        };

        // Führe den obigen Quellcode im Event-Dispatch-Thread aus
        SwingUtilities.invokeLater(guiCreator);
    }
}