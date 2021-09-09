package exception;

public class PraznoIsbnException extends Exception{
	private String valueName;

    public PraznoIsbnException() {
        this.valueName = "Nije uneta vrednost za ISBN ili već postoji!";
    }

    public String getValueName() {
        return valueName;
    }
}
