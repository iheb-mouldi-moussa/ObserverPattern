package observer.Observers;

import observer.BaseClass.BaseClass;
import observer.Observable.Observable;

public interface Observer {
    
    public void update(BaseClass baseClass, Boolean isAdding);
}
