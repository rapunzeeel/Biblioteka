package exception;

public class RecenzijaVecPostojiException extends Exception{
	private String valueName;

    public RecenzijaVecPostojiException() {
        this.valueName = "Vec ste dali recenziju za ovu knjigu!";
    }

    public String getValueName() {
        return valueName;
    }
}
