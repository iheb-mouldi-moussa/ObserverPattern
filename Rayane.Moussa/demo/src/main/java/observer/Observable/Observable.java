package observer.Observable;
import observer.BaseClass.BaseClass;
import observer.Observers.Observer;;

public interface Observable {
    
    public void register(Observer o);
    public void remove_observer(Observer o);
    public void notifyObservers(Boolean isPlayer, Boolean isAdding, BaseClass baseClass);
}
