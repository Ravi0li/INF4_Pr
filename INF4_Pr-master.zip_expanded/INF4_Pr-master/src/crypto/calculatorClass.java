package crypto;

public class calculatorClass extends crypto.basicCrypt {

	// Im Konstruktor das Aussehen definieren
	public calculatorClass()
	{
		className = "Taschenrechner";
		input1Title = "Erste Zahl: ";
		input2Title = "Zweite Zahl: ";
		input3Title = "";
		output1Title = "Ergebnis Addition: ";
		output2Title = "Ergebnis Subtraktion: ";
	}
	
	// Funktion zum berechnen
	@Override
	public void calculate ()
	{	
		// Eingabevariablen
		int num1 = Integer.parseInt(input1Text);
		int num2 = Integer.parseInt(input2Text);
		
		// Berechnung
		int resAdd = num1 + num2;
		int resSub = num1 - num2;
		
		// Ausgabe
		output1Text =  Integer.toString(resAdd);
		output2Text =  Integer.toString(resSub);
		
		// Log verwenden
		outputLogText = "Zahl1: " + input1Text + "\r\n";
		outputLogText += "Zahl2: " + input2Text + "\r\n";
		outputLogText += "Zahl3: " + input3Text + "\r\n";
		outputLogText += "Berechnung beendet";
}
}
