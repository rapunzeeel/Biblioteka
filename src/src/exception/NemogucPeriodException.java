package exception;

public class NemogucPeriodException extends Exception{
	private String valueName;

    public NemogucPeriodException() {
        this.valueName = "Uneta je pogre�an vremenski interval!";
    }

    public String getValueName() {
        return valueName;
    }
}
