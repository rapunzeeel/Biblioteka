package exception;

public class NedostajacaVrednostException extends Exception{
	private String valueName;

    public NedostajacaVrednostException() {
        this.valueName = "Nije uneta vrednost!";
    }

    public String getValueName() {
        return valueName;
    }
}
