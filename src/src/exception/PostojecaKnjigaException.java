package exception;

public class PostojecaKnjigaException extends Exception{
	private String valueName;

    public PostojecaKnjigaException() {
        this.valueName = "Knjiga sa tim ISBN-om već postoji!";
    }

    public String getValueName() {
        return valueName;
    }
}
