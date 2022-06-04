package observer.Observers;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import observer.BaseClass.BaseClass;
import observer.Interfaces.Playable;
import observer.Observable.Dataset;

public class Player implements Observer {

    private List<BaseClass> objects = new ArrayList<>();

    @Override
    public void update(Observable observable, Object isPlayable) {
        
        if(observable instanceof Dataset && "playable".equals(isPlayable))
        {
            System.out.println("Playable Observers has been notified");
            objects.add(((Dataset) observable).getValue());
        }
        
    }


   
    
}
