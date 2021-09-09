package exception;

public class PrijavljenObradjivacException extends Exception{
	private String valueName;

    public PrijavljenObradjivacException() {
        this.valueName = "";
    }

    public String getValueName() {
        return valueName;
    }
}
