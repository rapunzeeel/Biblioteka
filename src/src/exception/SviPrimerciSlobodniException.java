package exception;

public class SviPrimerciSlobodniException extends Exception{
	private String valueName;

    public SviPrimerciSlobodniException() {
        this.valueName = "Nije moguće potvrditi rezervaciju!";
    }

    public String getValueName() {
        return valueName;
    }
}
