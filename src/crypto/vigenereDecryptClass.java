package crypto;

public class vigenereDecryptClass extends crypto.basicCrypt {

	// Im Konstruktor das Aussehen definieren
	public vigenereDecryptClass() {
		className = "Vigenere Chiffre Entschlüsseln";
		input1Title = "Wie lautet das Schlüsselwort? ";
		input2Title = "Geben Sie ein, was Sie entschlüsseln moechten: ";
		input3Title = "";
		output1Title = "Entschlüsselte Nachricht: ";
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
				stelle = (userString.charAt(i) - 'a') - (key.charAt(i) - 'a');

				outputLogText += userString.charAt(i) + " in Nachricht ist " + (userString.charAt(i) - 'a') + ". Buchstabe im Alphabet\r\n";
				outputLogText += key.charAt(i) + " im Schlüssel ist " + (key.charAt(i) - 'a') + ". Buchstabe im Alphabet\r\n";
				
				if (stelle < 0) {
					outputLogText += "Subtraktion ergibt " + stelle + "te Stelle -> Anpassung nötig!\r\n";
					stelle = stelle % 26 + 26;
				}
								
				TempCharArray[i] = (char) (stelle + 'a');
				
				outputLogText += "--> Im Chiffretext also der " + stelle + ". Buchstabe \"" + TempCharArray[i] + "\"\r\n\r\n";
			}

		}

		output1Text = new String(TempCharArray);

		
	}
	
}
