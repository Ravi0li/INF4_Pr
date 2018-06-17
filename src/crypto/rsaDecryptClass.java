package crypto;

import java.math.BigInteger;

public class rsaDecryptClass extends crypto.basicCrypt {

	private BigInteger privateKey;
	private BigInteger message;
	private BigInteger modulus;

	long[] zahlen;

	// Im Konstruktor das Aussehen definieren
	public rsaDecryptClass() {
		className = "RSA Decrypt";
		input1Title = "Modulus;PrivateKey";
		input2Title = "EnkryptedMessage";
		input3Title = "";
		output1Title = "DecryptedMessage: ";
		output2Title = "";

	}

	// Funktion zum berechnen
	@Override
	public void calculate() {
		// Eingabevariablen
		String primesString = input1Text;
		modulus = new BigInteger(primesString.split("[;]")[0]);
		privateKey = new BigInteger(primesString.split("[;]")[1]);
		message = new BigInteger(input2Text);
		outputLogText = "" ;
	
		// Encrypt

		message = message.modPow(privateKey, modulus);

		outputLogText += "\r\n";
		outputLogText += "Message Dekrypted: \r\n";
		outputLogText += new String(message.toByteArray());
		output1Text = new String(message.toByteArray());
		// Ausgabe

	}


}
