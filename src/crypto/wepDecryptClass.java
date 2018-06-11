package crypto;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class wepDecryptClass extends crypto.basicCrypt {

	// Im Konstruktor das Aussehen definieren
	public wepDecryptClass()
	{
		className = "WEP-Entschlüsseln";
		input1Title = "Verschlüsselte Nachricht: ";
		input2Title = "Schlüssel: ";
		input3Title = "";
		output1Title = "Nachricht: ";
		output2Title = "";
	}
	
	// Funktion zum berechnen
	@Override
	public void calculate ()
	{	
		// Einlesen der Variablen
		String[] msgString = input1Text.substring(1, input1Text.length() - 1).split(",");
		byte[] msg = new byte[msgString.length];
		try
		{
			for (int i=0, len=msg.length; i<len; i++)
				msg[i] = Byte.parseByte(msgString[i].trim());   
		}
		catch (Exception exception)
		{
			outputLogText = "FEHLER: Eingangsstring ist fehlerhaft\r\n";
			return;
		}
		byte[] key = input2Text.getBytes();
		
		// Länge der Nachricht berechnen
		outputLogText = "Eingabe länge: " + msg.length + "\r\n";
		outputLogText += "MSG:          " + getBin(msg) + "\r\n";
		
		// IV separieren
		byte[] iv = Arrays.copyOfRange(msg,0,3);
		outputLogText += "IV:           " + getBin(iv) + "\r\n";
		
		// Message separieren
		if (msg.length <= 3)
		{
			outputLogText += "FEHLER: zu kurzer Eingangsstring\r\n";
			return;
		}
		msg = Arrays.copyOfRange(msg,3,msg.length);
		outputLogText += "MSG:          " + getBin(msg) + "\r\n";
		
		// Initialisierungsvektor und Key zusammenhängen
		byte[] ivKey = new byte[iv.length + key.length];
		System.arraycopy(iv, 0, ivKey, 0, iv.length);
		System.arraycopy(key, 0, ivKey, iv.length, key.length);
		outputLogText += "IV+KEY:       " + getBin(ivKey) + "\r\n";
		
		// RC4 Zufallszahl erstellen
		byte[] cryptKey = RC4(ivKey);
		outputLogText += "RC4(In+Key):  " + getBin(cryptKey) + "\r\n";
		
		// XOR
		byte output[] = new byte[msg.length];
		int j = 0;
		for (int c=0; c < msg.length; c++)
		{
			output[c] = (byte)(0xff & ((int)msg[c] ^ (int)cryptKey[j]));
			j++;
			if (j == cryptKey.length) j = 0;
		}
		outputLogText += "XOR:          " + getBin(output) + "\r\n";
		output1Text = Arrays.toString(output);
		
		// Nachricht anzeigen
		if (output.length-4 <= 0)
		{
			outputLogText += "FEHLER: Paketlänge fehlerhaft\r\n";
			return;
		}
		byte[] msgKlar = new byte[output.length-4];
		try
		{
			msgKlar = Arrays.copyOfRange(output,0,output.length-4);
		}
		catch (Exception exception)
		{
			outputLogText += "FEHLER: Paketlänge fehlerhaft\r\n";
			return;
		}
		outputLogText += "OUT:          " + getBin(msgKlar) + "\r\n";
		output1Text = new String(msgKlar, StandardCharsets.UTF_8);
		
		// CRC Summe berechnen
		int crcint = CRC32(msgKlar);
		outputLogText += "CRC(Msg):     " + getBin(crcint) + "\r\n";
		byte[] crc = new byte[4];
		crc[3]   = (byte)  crcint;
		crc[2] = (byte) (crcint >> 8);
		crc[1] = (byte) (crcint >> 0x10);
		crc[0] = (byte) (crcint >> 0x18);
		
		// CRC vergleichen
		byte[] crcMsg = Arrays.copyOfRange(output,output.length-4,output.length);
		outputLogText += "CRC:          " + getBin(crcMsg) + "\r\n";
		if (Arrays.equals(crc, crcMsg))
			outputLogText += "CRC-CHECK:    OK\r\n";
		else
			outputLogText += "CRC-CHECK:    FEHLER\r\n";
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

