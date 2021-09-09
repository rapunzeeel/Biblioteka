package exception;

public class NijePronadjenjaKnjigaException extends Exception{
	private String valueName;

    public NijePronadjenjaKnjigaException() {
        this.valueName = "Unet je nepostojeći ISBN!";
    }

    public String getValueName() {
        return valueName;
    }
}
