package observer;

public class RepositoryUpdateEvent {
	private Object akcija;
	
	public RepositoryUpdateEvent() {}
	
	public RepositoryUpdateEvent(Object akcija) {
		this.akcija = akcija;
	}
	
	public Object getAkcija()
	{
		return akcija;
	}
}
