package exception;

public class PraznoNaslovException extends Exception{
	private String valueName;

    public PraznoNaslovException() {
        this.valueName = "Nije uneta vrednost za naslov!";
    }

    public String getValueName() {
        return valueName;
    }
}
