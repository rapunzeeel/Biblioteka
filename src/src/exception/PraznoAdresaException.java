package exception;

public class PraznoAdresaException extends Exception{
	private String valueName;

    public PraznoAdresaException() {
        this.valueName = "Nije uneta vrednost za adresu!";
    }

    public String getValueName() {
        return valueName;
    }
}
