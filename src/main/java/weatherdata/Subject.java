package weatherdata;

public interface Subject {
    void registerObserver(Observer o);
    void  removeObserver(Observer o);

    void notifyObserver();
}
