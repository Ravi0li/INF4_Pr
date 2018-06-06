package crypto;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

public class SHA1Class extends crypto.basicCrypt{
	// Variablen
	protected String className = "SHA Encrypt";
	protected String input1Title = "Geben sie ein String ein:";
	protected String input2Title = "";
	protected String input3Title = "";
	protected String output1Title = "SHA-1 Wert:";
	protected String output2Title = "SHA-512 Wert:";
	
	// Ein/Ausgabe
	protected String input1Text = "";	
	protected String output1Text = "";
	protected String output2Text = "";
	public static String outputLogText = "";
	
	// Setzt die Eingaben
	public void setInputs (String input1, String input2, String input3)
	{
		input1Text = input1;
		input2Text = input2;
		input3Text = input3;
	}
	
	// Gibt die Ausgaben 1
	public String getOutput1 ()
	{
		return output1Text;
	}
	
	// Gibt die Ausgaben 2
	public String getOutput2 ()
	{
		return output2Text;
	}
	
	// gibt die Log zurück
	public String getLog ()
	{
		return outputLogText;
	}
	
	// gibt den Klassennamen zurück
	public String getName ()
	{
		return className;
	}
	
	// gibt Infos zur 1. Eingabe
	public boolean getInput1Infos ()
	{
		if (input1Title.length() > 1)
			return true;
		else
			return false;
	}
	
	// gibt Infos zur 1. Eingabe
	public String getInput1InfosText ()
	{
		return input1Title;
	}
	
	// gibt Infos zur 2. Eingabe
	public boolean getInput2Infos ()
	{
		if (input2Title.length() > 1)
			return true;
		else
			return false;
	}
	
	// gibt Infos zur 2. Eingabe
	public String getInput2InfosText ()
	{
		return input2Title;
	}
	
	// gibt Infos zur 3. Eingabe
	public boolean getInput3Infos ()
	{
		if (input3Title.length() > 1)
			return true;
		else
			return false;
	}
	
	// gibt Infos zur 3. Eingabe
	public String getInput3InfosText ()
	{
		return input3Title;
	}
	
	// gibt Infos zur 1. Ausgabe
	public String getOutput1InfosText ()
	{
		return output1Title;
	}
	
	// gibt Infos zur 1. Ausgabe
	public boolean getOutput1Infos()
	{
		if (output1Title.length() > 1)
			return true;
		else
			return false;
	}
	
	// gibt Infos zur 2. Ausgabe
	public String getOutput2InfosText ()
	{
		return output2Title;
	}
	
	// gibt Infos zur 1. Ausgabe
	public boolean getOutput2Infos()
	{
		if (output2Title.length() > 1)
			return true;
		else
			return false;
	}
	
	// Funktion zum Berechnen
	public void calculate ()
	{	
		output1Text=encryptPassword(input1Text,1);
		output2Text=encryptPassword(input1Text,2);
	    outputLogText="Eingabe: "+input1Text + "\n";
	    outputLogText+="SHA-1:(160 Bit) "+output1Text + "\n";
	    outputLogText+="SHA-512:(512 Bit) "+output2Text + "\n";
		
		
	}
	
	
	private static String encryptPassword(String password,int k)
	{
	    String sha1 = "";
	    String method=new String("SHA-1");
	    try
	    {
	    	if(k==1)method="SHA-1";
	    	if(k==2)method="SHA-512";
	        MessageDigest crypt = MessageDigest.getInstance(method);
	        crypt.reset();
	        crypt.update(password.getBytes("UTF-8"));
	        sha1 = byteToHex(crypt.digest());
	    }
	    catch(NoSuchAlgorithmException e)
	    {
	        e.printStackTrace();
	    }
	    catch(UnsupportedEncodingException e)
	    {
	        e.printStackTrace();
	    }
	  
	
	    
	    return sha1;
	}

	private static String byteToHex(final byte[] hash)
	{
	    Formatter formatter = new Formatter();
	    for (byte b : hash)
	    {
	        formatter.format("%02x", b);
	    }
	    String result = formatter.toString();
	    formatter.close();
	    return result;
	}
}
