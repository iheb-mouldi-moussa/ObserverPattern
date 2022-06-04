package observer.Observable;
import observer.Observers.Observer;;

public interface Observable {
    
    public void register(Observer o);
    public void remove_observer(Observer o);
    public void notifyObservers(Boolean isPlayer, Boolean isAdding);
}
