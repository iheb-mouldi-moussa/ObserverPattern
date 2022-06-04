package observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.xml.crypto.Data;

public class Dataset extends Observable {
   

    private List<BaseClass> objects = new ArrayList<>();
    private List<Observer> observers = new ArrayList<>();
    
    public void add(BaseClass baseClass)
    {
        this.objects.add(baseClass);
        if(baseClass instanceof Image) // || Audio
        {
            notifyObservers();
            // NOTIFY PLAYER OBSERVER
            //System.out.println("Image object");   
        }
        else 
        {
          //  
        }
        System.out.println("New Objects has been added to the collection");
    }

    public void register(Observer observer)
    {

    }

    public static void main(String[] args) {
        Dataset dataset = new Dataset();
        dataset.add(new Image());
    }
}
