package exception;

public class NijePronadjenClanExcepiton extends Exception{
	private String valueName;

    public NijePronadjenClanExcepiton() {
        this.valueName = "Nije pronadjen član za zadati email.";
    }

    public String getValueName() {
        return valueName;
    }
}
