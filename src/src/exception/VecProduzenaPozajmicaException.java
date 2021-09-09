package exception;

public class VecProduzenaPozajmicaException extends Exception{
	private String valueName;

    public VecProduzenaPozajmicaException() {
        this.valueName = "Rok za vracanje je vec produzen!";
    }

    public String getValueName() {
        return valueName;
    }
}
