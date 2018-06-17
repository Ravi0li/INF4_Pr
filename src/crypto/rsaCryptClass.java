package crypto;

import java.math.BigInteger;
import java.security.SecureRandom;

public class rsaCryptClass extends crypto.basicCrypt {

	 private final static BigInteger one      = new BigInteger("1");
	   private final static SecureRandom random = new SecureRandom();

	   private BigInteger privateKey;
	   private BigInteger publicKey;
	   private BigInteger modulus;
	   
	private BigInteger prime1;
	private BigInteger prime2;
	private BigInteger Phi;
	
	private String text;
	

	// Im Konstruktor das Aussehen definieren
	public rsaCryptClass() {
		className = "RSA Encrypt";
		input1Title = "Key Size";
		input2Title = "Text ";
		input3Title = "";
		output1Title = "Key Info: ";
		output2Title = "EnkryptedText: ";

		// Prime Numbers p,q (1k digits each) n= p*q

	}



	// Funktion zum berechnen
	@Override
	public void calculate() {
		// Eingabevariablen
		
		publicKey  = new BigInteger("65537"); 
		String primesString = input1Text;
		int N = Integer.parseInt(primesString.split("[;]")[0]);
		
		if(primesString.split("[;]").length>1) {
			publicKey = new BigInteger(primesString.split("[;]")[1]);
		}
		
		
		BigInteger prime1 = BigInteger.probablePrime(N/2, random);
	    BigInteger prime2 = BigInteger.probablePrime(N/2, random);
	      
		outputLogText = "Prime1: " + prime1 + "\r\n";
		outputLogText += "Prime2: " + prime2 + "\r\n";

		// Key Gen
		BigInteger Phi = (prime1.subtract(one)).multiply(prime2.subtract(one));
		modulus    = prime1.multiply(prime2);   
		
			
		privateKey = publicKey.modInverse(Phi);

		outputLogText += "Phi = (Prime1 - 1)*(Prime2 - 1) = " + Phi + "\r\n";
		outputLogText += "Modulus = Prime1*Prime2 = " + modulus + "\r\n";
		outputLogText += "Public Key: "+ publicKey +" \r\n";
		outputLogText += "Private Key: Inverse Element zu public Key. Hier: " + privateKey + "\r\n";

		// Text to Numbers
		text = input2Text;
		byte[] bytes = text.getBytes();
	    BigInteger message = new BigInteger(bytes);
		
	    if(message.compareTo(modulus)==1) {
	    	outputLogText += "Modulus zu klein für Nachricht";
	    }

		outputLogText += "Text als Zahlen dargestellt: \r\n";
		
		outputLogText += message;
		
		// Encrypt

		message =  message.modPow(publicKey, modulus);

		outputLogText += "\r\n";
		outputLogText += "Text Enkrypted: \r\n";

		// Ausgabe
		output1Text = modulus+ ";" + privateKey;

		
		output2Text += message;
	
		outputLogText += message;
		outputLogText += "\r\n";
		
	}

	/*
	 * public KeyGenerator(p,q){ int tn = (q-1)*(p-1); int e=8; ind d; e*d }
	 */

	public long exp_modulus(long basis, long expo, long m) {

		long quad = basis;
		long halb = expo;
		long erg = 1;
		while (halb > 0) {
			if (halb % 2 > 0)
				erg = (erg * quad) % m;
			quad = (quad * quad) % m;
			halb = halb / 2;
		}
		return erg;
	}

	long[] codier(String text) {
		long[] code = new long[text.length()];
		int i;

		for (i = 0; i < text.length(); i++) {
			if (text.charAt(i) == ' ')
				code[i] = 0;
			else
				code[i] = Character.toLowerCase(text.charAt(i)) - 'a' + 1;
		}
		return code;
	}

	static long modInverse(long a, long m) {
		a = a % m;
		for (int x = 1; x < m; x++)
			if ((a * x) % m == 1)
				return x;
		return 1;
	}
	
}
