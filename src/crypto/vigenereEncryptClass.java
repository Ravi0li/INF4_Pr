package crypto;

public class vigenereEncryptClass extends crypto.basicCrypt {

	// Im Konstruktor das Aussehen definieren
	public vigenereEncryptClass() {
		className = "Vigenere Chiffre Verschlüsseln";
		input1Title = "Legen Sie ein Schlüsselwort fest: ";
		input2Title = "Geben Sie ein, was Sie verschlüsseln moechten: ";
		input3Title = "";
		output1Title = "Verschlüsselte Nachricht: ";
		output2Title = "";
	}
	
	private static String adjustKey(String key, int lenKey, int len) {
		
		int j = 0;
		
		do {

			key = key.concat(String.valueOf(key.charAt(j)));
			if (j > lenKey) { j = 0; } else { j++; }
			lenKey = key.length();

		} while (lenKey != len);
		
		return key;
	}
	
	static String encode(String key, String s) {

		int lenKey = key.length();
		int len = s.length();
		int stelle;

		char[] TempCharArray = new char[len];

		if (lenKey != len) {

			key = adjustKey(key, lenKey, len);

		}

		for (int i = 0; i < len; i++) {

			if (s.charAt(i) == ' ') {
				TempCharArray[i] = s.charAt(i);
			} else {
				stelle = (s.charAt(i) - 'a') + (key.charAt(i) - 'a');

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
		
		String key = input1Text.toLowerCase().replaceAll("[^\\dA-Za-z ]", "");
		String userString = input2Text.toLowerCase().replaceAll("[^\\dA-Za-z ]", "");
		
		int lenKey = key.length();
		int len = userString.length();
		int stelle;

		char[] TempCharArray = new char[len];

		if (lenKey != len) {
			outputLogText = "lenKey != len --> Schlüssellänge muss angepasst werden! \r\n";
			key = adjustKey(key, lenKey, len);
			outputLogText += "Neuer Schlüssel --> " + key + "\r\n\r\n";
		}

		for (int i = 0; i < len; i++) {

			if (userString.charAt(i) == ' ') {
				TempCharArray[i] = userString.charAt(i);
			} else {
				stelle = (userString.charAt(i) - 'a') + (key.charAt(i) - 'a');

				outputLogText += userString.charAt(i) + " im Klartext ist " + (userString.charAt(i) - 'a') + ". Buchstabe im Alphabet\r\n";
				outputLogText += key.charAt(i) + " im Schlüssel ist " + (key.charAt(i) - 'a') + ". Buchstabe im Alphabet\r\n";
								
				if (stelle >= 26) {
					outputLogText += "Addition ergibt " + stelle + "te Stelle -> Modulo Operation nötig!\r\n";
					stelle = stelle % 26;
				}
				TempCharArray[i] = (char) (stelle + 'a');
				
				outputLogText += "--> Im Chiffretext also der " + stelle + ". Buchstabe \"" + TempCharArray[i] + "\"\r\n\r\n";
			}

		}

		output1Text = new String(TempCharArray);
			
	}
	
}
