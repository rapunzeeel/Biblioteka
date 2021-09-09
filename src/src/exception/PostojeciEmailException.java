package exception;

public class PostojeciEmailException extends Exception{
	private String valueName;

    public PostojeciEmailException() {
        this.valueName = "Korisnik sa unetim email-om već postoji ili je polje prazno!";
    }

    public String getValueName() {
        return valueName;
    }
}
