package crypto;

public class basicCrypt {
	// Variablen
	protected String className = "";
	protected String input1Title = "";
	protected String input2Title = "";
	protected String input3Title = "";
	protected String output1Title = "";
	protected String output2Title = "";
	
	// Ein/Ausgabe
	protected String input1Text = "";
	protected String input2Text = "";
	protected String input3Text = "";
	protected String output1Text = "";
	protected String output2Text = "";
	protected String outputLogText = "";
	
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
	}
}
