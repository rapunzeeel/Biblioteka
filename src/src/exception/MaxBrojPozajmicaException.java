package exception;

public class MaxBrojPozajmicaException extends Exception {
	private String valueName;

    public MaxBrojPozajmicaException() {
        this.valueName = "Član nema mogućnost pozajmice!";
    }

    public String getValueName() {
        return valueName;
    }
}
