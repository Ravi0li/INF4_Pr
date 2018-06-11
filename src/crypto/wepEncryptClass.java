package crypto;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Random;

public class wepEncryptClass extends crypto.basicCrypt {

	// Im Konstruktor das Aussehen definieren
	public wepEncryptClass()
	{
		className = "WEP-Verschlüsselung";
		input1Title = "Text: ";
		input2Title = "Schlüssel: ";
		input3Title = "";
		output1Title = "Veschlüsselt: ";
		output2Title = "";
	}
	
	// Funktion zum berechnen
	@Override
	public void calculate ()
	{	
		// Einlesen der Variablen
		byte[] msg = input1Text.getBytes();
		byte[] key = input2Text.getBytes();
		
		// Länge der Nachricht berechnen
		outputLogText = "Eingabe länge: " + msg.length + "\r\n";
		outputLogText += "MSG:          " + getBin(msg) + "\r\n";
		
		// Key in Byte
		int keyLength = key.length;
		outputLogText += "Key Länge: " + keyLength + "\r\n";
		outputLogText += "Key in bytes: " + getBin(key) + "\r\n";
		if (keyLength < 5 || keyLength > 13)
		{
			outputLogText += "FEHLER: Key ist nicht zwischen 5 bis 13 Zeichen lang\r\n";
			return;
		}
		
		// Zufälliger Initialisierungsvektor
		byte[] iv = new byte[3];
		int inLength = iv.length;
		new Random().nextBytes(iv);
		outputLogText += "IV 24 Bit:    " + getBin(iv) + "\r\n";
		
		// Initialisierungsvektor und Key zusammenhängen
		byte[] ivKey = new byte[inLength + keyLength];
		System.arraycopy(iv, 0, ivKey, 0, inLength);
		System.arraycopy(key, 0, ivKey, inLength, keyLength);
		outputLogText += "IV+KEY:       " + getBin(ivKey) + "\r\n";
		
		// RC4 Zufallszahl erstellen
		byte[] cryptKey = RC4(ivKey);
		outputLogText += "RC4(In+Key):  " + getBin(cryptKey) + "\r\n";
		
		// CRC Summe berechnen
		int crc = CRC32(msg);
		outputLogText += "ICV=CRC(Msg): " + getBin(crc) + "\r\n";
		
		// Anhängen der Checksumme an die Nachricht
		byte[] msgCrc = new byte[msg.length + 4];
		System.arraycopy(msg, 0, msgCrc, 0, msg.length);
		msgCrc[msg.length+3]   = (byte)  crc;
		msgCrc[msg.length+2] = (byte) (crc >> 8);
		msgCrc[msg.length+1] = (byte) (crc >> 0x10);
		msgCrc[msg.length+0] = (byte) (crc >> 0x18);
		outputLogText += "MSG+IVC:      " + getBin(msgCrc) + "\r\n";
		
		// IV vorne anhängen
		byte output[] = new byte[3 + msgCrc.length];
		output[0] = iv[0];
		output[1] = iv[1];
		output[2] = iv[2];
		
		// Kombinieren des Schlüssels und Message mit XOR
		int j = 0;
		for (int c=0; c < msgCrc.length; c++)
		{
			output[c+3] = (byte)(0xff & ((int)msgCrc[c] ^ (int)cryptKey[j]));
			j++;
			if (j == cryptKey.length) j = 0;
		}
		outputLogText += "OUTPUT:       " + getBin(output) + "\r\n";
		output1Text = Arrays.toString(output);
	}
	
	// Zeigt ein Int in Binär zahlen
	private String getBin(int i)
	{
		byte[] bytes = ByteBuffer.allocate(4).putInt(i).array();
		return getBin(bytes);
	}
	
	// Zeigt ein Byte Array in Binär zahlen
	private String getBin(byte[] b)
	{
		String out = "";
		for(byte elem : b)
			out += getBin(elem) + " ";
		return out;
	}
	
	// Zeigt Byte in Binärzahlen an
	private String getBin(byte in)
	{
		return String.format("%8s", Integer.toBinaryString(in & 0xFF)).replace(' ', '0');
	}
	
	// RC4 Zufallszahl bestimmen
	private byte[] RC4(byte[] ivKey)
	{
		byte[] cryptKey = new byte[ivKey.length];
		int i = 0;
		int j = 0;
		for (int c=0; c < ivKey.length; c++)
		{
		    i = (i + 1) % ivKey.length;
		    j = (j + ivKey[i] & 0xFF) % ivKey.length;
		    byte t = ivKey[i];
		    ivKey[i] = ivKey[j];
		    ivKey[j] = t;
		    cryptKey[c] = ivKey[(ivKey[i] & 0xFF + ivKey[j] & 0xFF) % ivKey.length];
		}
		return cryptKey;
	}
	
	// Berechnet CRC32
	// Validiert durch: http://www.sunshine2k.de/coding/javascript/crc/crc_js.html
	private int CRC32(byte[] bytes)
	{
		int crc = 0xFFFFFFFF;  
        int poly = 0xEDB88320;

        for (byte b : bytes) {
            int temp = (crc ^ b) & 0xff;
            // immer acht Bit lesen
            for (int i = 0; i < 8; i++) {
                if ((temp & 1) == 1) temp = (temp >>> 1) ^ poly;
                else                 temp = (temp >>> 1);
            }
            crc = (crc >>> 8) ^ temp;
        }
        // Bits Flip
        return crc ^ 0xffffffff;
	}
	
}

