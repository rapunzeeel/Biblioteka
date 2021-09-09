package exception;

public class PraznoPrezimeException extends Exception{
	private String valueName;

    public PraznoPrezimeException() {
        this.valueName = "Nije uneta vrednost za prezime!";
    }

    public String getValueName() {
        return valueName;
    }
}
