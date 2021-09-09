package exception;

public class NepronadjeneVrednostiException extends Exception{
	
	private String valueName;

    public NepronadjeneVrednostiException() {
        this.valueName = "Nepostoje vrednosti za zadati kriterijum!";
    }

    public String getValueName() {
        return valueName;
    }
}
