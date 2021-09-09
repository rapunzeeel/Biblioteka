package observer;

public interface Publisher {
	public void addObserver(Observer observer);
	
	public void removeObserver(Observer observer);
	
	public void notifyObservers();

	public void notifyObservers(Object o);
}
