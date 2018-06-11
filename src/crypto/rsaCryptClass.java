package crypto;
import java.math.BigInteger;

public class rsaCryptClass extends crypto.basicCrypt {
	
	private long prime1;
	private long prime2;
	private long Phi;
	private long privateKey;
	private long publicKey;
	private long modulus;
	private String text;
	long[] zahlen;
	
	// Im Konstruktor das Aussehen definieren
		public rsaCryptClass()
		{
			className = "RSA Encrypt";
			input1Title = "Prime1;Prime2 ";
			input2Title = "Text ";
			input3Title = "";
			output1Title = "Key Info: ";
			output2Title = "EnkryptedText: ";
			
			//Prime Numbers   p,q (1k digits each)  n= p*q
			
		}
		private int p,q,n;
		// Funktion zum berechnen
		@Override
		public void calculate ()
		{	
			// Eingabevariablen
			String primesString = input1Text;
			prime1 = Integer.parseInt(primesString.split("[;]")[0]);
			prime2 = Integer.parseInt(primesString.split("[;]")[1]);
			outputLogText = "Prime1: " + prime1 + "\r\n";
			outputLogText += "Prime2: " + prime2 + "\r\n";
			
			// Key Gen
			Phi = (prime1-1)*(prime2-1);
			modulus = prime1*prime2;
	        publicKey = 9;  //  2^x +1    with x < 32  <- from prof arndt's lecture
			privateKey = modInverse(publicKey,modulus);
			
			outputLogText += "Phi = (Prime1 - 1)*(Prime2 - 1) = " + Phi + "\r\n";
			outputLogText += "Modulus = Prime1*Prime2 = " + modulus + "\r\n";
			outputLogText += "Public Key: Teilerfremd zu Modulus  2^x +1  mit x<=32 klappt immer => wir verwenden 9 \r\n";
			outputLogText += "Private Key: Inverse Element zu public Key. Hier: " + privateKey + "\r\n";
			
			//Text to Numbers
			text = input2Text;
			zahlen = codier(text);
			
			outputLogText += "Text als Zahlen dargestellt: \r\n";
			for(int i=0;i<zahlen.length;i++) {
				outputLogText += zahlen[i] + " ";
			}
			//Encrypt
			
			for (int i=0,j=0; i<zahlen.length; i+=2, j++) {
			      if (i+1 < zahlen.length) {
			         zahlen[j] = text.charAt(i);
			         zahlen[j] = zahlen[j]*100+text.charAt(i+1);
			      } else
			         zahlen[j] = text.charAt(i)*100;
			      zahlen[j] = exp_modulus(zahlen[j], publicKey, modulus);
			   }
			
			outputLogText += "\r\n";
			outputLogText += "Text Enkrypted: \r\n";
			
			// Ausgabe
			output1Text =  "Modulus: "+modulus+" Public Key: "+publicKey+" Private Key " + privateKey;
			
			for(int i=0;i<zahlen.length;i++) {
				output2Text += zahlen[i] + " ";
				outputLogText += zahlen[i] + " ";
			}
			outputLogText += "\r\n";

	}
		
	/*	public KeyGenerator(p,q){
			int tn = (q-1)*(p-1);
			int e=8; 
			ind d;
			e*d
		}*/
		
		
		
		public long  exp_modulus(long basis,long expo,long m) {

				long quad = basis;
				long halb = expo;
				long erg  = 1;
				while (halb > 0) {
					if (halb % 2 > 0)
						erg = (erg * quad) % m;
					quad = (quad * quad) % m;
					halb = halb / 2;
				}
				return erg;
			}
		
		long[] codier(String text)
		{
			long[] code = new long[text.length()];
		   int  i;
		   
		   for (i=0; i<text.length(); i++) {
		      if (text.charAt(i) == ' ')
		    	  code[i] = 0;
		      else
		         code[i] = Character.toLowerCase(text.charAt(i)) - 'a' +1;
		   }
		   return code;
		}	
		static long modInverse(long a, long m)
	    {
	        a = a % m;
	        for (int x = 1; x < m; x++)
	           if ((a * x) % m == 1)
	              return x;
	        return 1;
	    }
		}


