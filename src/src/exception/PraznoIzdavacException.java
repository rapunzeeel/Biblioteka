package exception;

public class PraznoIzdavacException extends Exception{
	private String valueName;

    public PraznoIzdavacException() {
        this.valueName = "Nije uneta vrednost za izdavaca!";
    }

    public String getValueName() {
        return valueName;
    }
}
