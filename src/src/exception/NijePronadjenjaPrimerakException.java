package exception;

public class NijePronadjenjaPrimerakException extends Exception {
	private String valueName;

    public NijePronadjenjaPrimerakException() {
        this.valueName = "Ne postoje slobodni primerci!";
    }

    public String getValueName() {
        return valueName;
    }
}
