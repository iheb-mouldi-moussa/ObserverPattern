package observer.Observable;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.xml.crypto.Data;

import observer.BaseClass.BaseClass;
import observer.Exceptions.ExceptionListEmpty;
import observer.Interfaces.Non_playable;
import observer.Objects.Audio;
import observer.Objects.Image;
import observer.Objects.Text;
import observer.Objects.Video;
import observer.Observers.Player;
import observer.Observers.Viewer;

public class Dataset extends Observable {
   

    private List<BaseClass> objects = new ArrayList<>();
    //private List<Observer> observers = new ArrayList<>();
    //private List<Observer> viewerObservers = new ArrayList<>();
   // BaseClass baseClass;
    public void add(BaseClass baseClass)
    {
        //boolean isPlayable = false;
     //   this.baseClass = baseClass;
        String temp = "add-non-playable";
        this.objects.add(baseClass);
        System.out.println("New Objects has been added to the Dataset collection");
        setChanged();
        if(baseClass instanceof Video || baseClass instanceof Audio)
            temp = "add-playable";
        notifyObservers(temp);    
    }

    public void remove(BaseClass baseClass)
    {
        String temp = "del-non-playable";
        this.objects.remove(baseClass);
        System.out.println("New Objects has been deleted from the Dataset collection");
        setChanged();
        if(baseClass instanceof Video || baseClass instanceof Audio)
            temp = "del-playable";
        notifyObservers(temp);    
                
    }


    public void register(Observer observer)
    {
        addObserver(observer);
    }

    public void remove_observer(Observer observer)
    {
        deleteObserver(observer);
    }

    public BaseClass getValue()
    {
        return objects.get(objects.size() - 1);
    }

    public static void main(String[] args) throws ExceptionListEmpty {
        Dataset dataset = new Dataset();
        Player player = new Player();
        Viewer viewer = new Viewer();
        dataset.register(player);
        dataset.register(viewer);
        dataset.add(new Image("test1", "test2", "test3"));
        dataset.add(new Text("text1", "text2"));
        dataset.add(new Image("2", "2", "2"));
        viewer.show_list();
        viewer.next(Image.class);
        viewer.next(Text.class);

        Non_playable non_playable = viewer.currently_viewing();
        non_playable.info();

    }
}
