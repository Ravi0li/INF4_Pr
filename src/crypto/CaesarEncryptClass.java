package crypto;

public class CaesarEncryptClass extends crypto.basicCrypt {

	// Im Konstruktor das Aussehen definieren
	public CaesarEncryptClass() {
		className = "Cäsar Chiffre Verschlüsseln";
		input1Title = "Legen Sie den Wert für k fest: ";
		input2Title = "Geben Sie ein, was Sie verschlüsseln moechten: ";
		input3Title = "";
		output1Title = "Verschlüsselte Nachricht: ";
		output2Title = "";
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
				if (stelle >= 26) {
					stelle = stelle % 26;
				}
				TempCharArray[i] = (char) (stelle + 'a');
			}
		}

		String EncodedString = new String(TempCharArray);

		return EncodedString;
	}

	// Funktion zum berechnen
	@Override
	public void calculate() {
		// Eingabevariablen
		int k = Integer.parseInt(input1Text);
		String alphabet = "a b c d e f g h i j k l m n o p q r s t u v w x y z";
		String userString = input2Text.toLowerCase().replaceAll("[^\\dA-Za-z ]", "");
		
		outputLogText = "\"Neues Alphabet\"\r\n================\r\n";
		outputLogText += "a b c d e f g h i j k l m n o p q r s t u v w x y z \r\n";
		outputLogText += encode(k, alphabet) + "\r\n\r\n";
		
		int len = userString.length();
		int stelle;

		char[] TempCharArray = new char[len];

		for (int i = 0; i < len; i++) {
			if (userString.charAt(i) == ' ') {
				TempCharArray[i] = userString.charAt(i);
			} else {
				stelle = (userString.charAt(i) - 'a' + k);
				if (stelle >= 26) {
					stelle = stelle % 26;
				}
				TempCharArray[i] = (char) (stelle + 'a');
			}
			
			outputLogText += userString.charAt(i) + " --> " + TempCharArray[i] + "\r\n";
		}

		output1Text = new String(TempCharArray);

	}
	
}
