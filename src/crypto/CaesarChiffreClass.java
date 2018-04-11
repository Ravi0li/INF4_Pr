package crypto;

public class CaesarChiffreClass extends crypto.basicCrypt {

	// Im Konstruktor das Aussehen definieren
	public CaesarChiffreClass() {
		className = "Cäsar Chiffre";
		input1Title = "Legen Sie den Wert für k fest: ";
		input2Title = "Geben Sie ein, was Sie ver- oder entschlüsseln moechten: ";
		input3Title = "";
		output1Title = "Ergebnis Verschlüsselung: ";
		output2Title = "Ergebnis Entschlüsselung: ";
	}

	static String encode(int k, String s) {

		int len = s.length();
		int stelle;

		char[] TempCharArray = new char[len];

		for (int i = 0; i < len; i++) {
			if (s.charAt(i) == ' ') {
				TempCharArray[i] = s.charAt(i);
			} else {
				stelle = (s.charAt(i) - 'a' + k);
				if (stelle > 26) {
					stelle = stelle % 26;
				}
				TempCharArray[i] = (char) (stelle + 'a');
			}
		}

		String EncodedString = new String(TempCharArray);

		return EncodedString;
	}

	static String decode(int k, String s) {
		int len = s.length();
		int stelle;

		char[] TempCharArray = new char[len];

		for (int i = 0; i < len; i++) {
			if (s.charAt(i) == ' ') {
				TempCharArray[i] = s.charAt(i);
			} else {
				stelle = (s.charAt(i) - 'a' - k);
				if (stelle < 0) {
					stelle = stelle % 26 + 26;
				}
				TempCharArray[i] = (char) (stelle + 'a');
			}
		}

		String DecodedString = new String(TempCharArray);

		return DecodedString;
	}

	// Funktion zum berechnen
	@Override
	public void calculate() {
		// Eingabevariablen
		int k = Integer.parseInt(input1Text);
		String userString = input2Text;

		// Ausgabe
		output1Text = encode(k, userString.toLowerCase());
		output2Text = decode(k, userString.toLowerCase());

		// Log verwenden
		outputLogText = "Zahl1: " + input1Text + "\r\n";
		outputLogText += "Zahl2: " + input2Text + "\r\n";
		outputLogText += "Zahl3: " + input3Text + "\r\n";
		outputLogText += "Berechnung beendet";
	}
}