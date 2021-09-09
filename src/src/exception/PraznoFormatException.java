package exception;

public class PraznoFormatException extends Exception{
	private String valueName;

    public PraznoFormatException() {
        this.valueName = "Nije uneta vrednost za format!";
    }

    public String getValueName() {
        return valueName;
    }
}
