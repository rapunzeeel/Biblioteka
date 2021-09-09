package exception;

public class NepostojeciPostanskiBrojException extends Exception{
	private String valueName;

    public NepostojeciPostanskiBrojException() {
        this.valueName = "Uneti poštanski broj je nepostojeći!";
    }

    public String getValueName() {
        return valueName;
    }
}
