package main;

public interface ISubject {
	void addObserver(IObserver observer);
	void removerObserver(IObserver observer);
	void notifyObservers();
}
