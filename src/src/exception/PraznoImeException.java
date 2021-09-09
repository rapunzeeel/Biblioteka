package exception;

public class PraznoImeException extends Exception{
	private String valueName;

    public PraznoImeException() {
        this.valueName = "Nije uneta vrednost za ime!";
    }

    public String getValueName() {
        return valueName;
    }
}
