package exception;

public class PrijavljenAdministratorException extends Exception{
	private String valueName;

    public PrijavljenAdministratorException() {
        this.valueName = "";
    }

    public String getValueName() {
        return valueName;
    }
}
