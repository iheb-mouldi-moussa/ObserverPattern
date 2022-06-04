package observer.Observers;

import observer.Observable.Observable;

public interface Observer {
    
    public void update(Observable observable, Boolean isAdding);
}
