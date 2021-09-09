package exception;

public class ZahtevVecPostojiException extends Exception{
	private String valueName;

    public ZahtevVecPostojiException() {
        this.valueName = "Zahtev za datu knjigu već postoji!";
    }

    public String getValueName() {
        return valueName;
    }
}
