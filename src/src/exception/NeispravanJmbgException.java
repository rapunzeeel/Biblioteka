package exception;

public class NeispravanJmbgException extends Exception{
	private String valueName;

    public NeispravanJmbgException() {
        this.valueName = "Neispravan jmbg!";
    }

    public String getValueName() {
        return valueName;
    }
}
