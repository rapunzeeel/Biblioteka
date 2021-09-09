package exception;

public class ClanarinaUplacenaException extends Exception{
	private String valueName;

    public ClanarinaUplacenaException() {
        this.valueName = "Članarina je važeća!";
    }

    public String getValueName() {
        return valueName;
    }
}
