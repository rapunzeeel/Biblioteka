package exception;

public class IsteklaClanarinaException extends Exception{
	private String valueName;

    public IsteklaClanarinaException() {
        this.valueName = "Istekla Vam je članarina!";
    }

    public String getValueName() {
        return valueName;
    }
}
