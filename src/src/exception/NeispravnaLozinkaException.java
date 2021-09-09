package exception;

public class NeispravnaLozinkaException extends Exception{
	private String valueName;

    public NeispravnaLozinkaException() {
        this.valueName = "Uneta je pogresna stara lozinka!";
    }

    public String getValueName() {
        return valueName;
    }
}
