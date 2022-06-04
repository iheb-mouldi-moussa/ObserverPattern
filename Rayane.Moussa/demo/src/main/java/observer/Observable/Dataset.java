package observer.Observable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//import javax.xml.crypto.Data;

import observer.BaseClass.BaseClass;
import observer.Exceptions.ExceptionListEmpty;
import observer.Interfaces.Non_playable;
import observer.Interfaces.Non_visual;
import observer.Interfaces.Playable;
import observer.Interfaces.Visual;
import observer.Objects.Audio;
import observer.Objects.Image;
import observer.Objects.Text;
import observer.Objects.Video;
import observer.Observers.Observer;
import observer.Observers.Player;
import observer.Observers.Viewer;

public class Dataset implements Observable {

    private List<BaseClass> objects = new ArrayList<>();
    private List<Observer> playerObservers = new ArrayList<>();
    private List<Observer> viewerObservers = new ArrayList<>();
    // private List<Observer> viewerObservers = new ArrayList<>();
    // BaseClass baseClass;

    public void add(BaseClass baseClass) {
        this.objects.add(baseClass);
        System.out.println("New Objects has been added to the Dataset collection");
        //setChanged();
        if (baseClass instanceof Video || baseClass instanceof Audio)
            notifyObservers(true, true);
        else
            notifyObservers(false, true);
    }

    public void remove(BaseClass baseClass) {
        this.objects.remove(baseClass);
        System.out.println("New Objects has been deleted from the Dataset collection");
        //setChanged();
        if (baseClass instanceof Video || baseClass instanceof Audio)
            notifyObservers(true, false);
        else
            notifyObservers(false, false);

    }

    public void remove(Non_playable non_playable)
    {
        if(non_playable instanceof Image)
        {
            BaseClass baseClass = (Image) non_playable;
            remove(baseClass);
        }
        else
        {
            BaseClass baseClass = (Text) non_playable;
            remove(baseClass);

        }
    }

    public void remove(Non_visual non_visual)
    {
        if(non_visual instanceof Text)
        {
            BaseClass baseClass = (Text) non_visual;
            remove(baseClass);
        }
        else
        {
            BaseClass baseClass = (Audio) non_visual;
            remove(baseClass);
        }
    }

    public void remove(Playable Playable)
    {
        if(Playable instanceof Text)
        {
            BaseClass baseClass = (Text) Playable;
            remove(baseClass);
        }
        else
        {
            BaseClass baseClass = (Audio) Playable;
            remove(baseClass);
        }
    }

    public void remove(Visual Visual)
    {
        if(Visual instanceof Text)
        {
            BaseClass baseClass = (Text) Visual;
            remove(baseClass);
        }
        else
        {
            BaseClass baseClass = (Audio) Visual;
            remove(baseClass);
        }
    }


    @Override
    public void register(Observer observer) {
        if (observer == null)
               throw new NullPointerException("can't add null observer");
        if (observer instanceof Player)
            playerObservers.add(observer);
        else
            viewerObservers.add(observer);
    }

    @Override
    public void remove_observer(Observer observer) {

        if (observer instanceof Player)
            playerObservers.remove(observer);
        else
            viewerObservers.remove(observer);
    }

    @Override
    public void notifyObservers(Boolean isPlayer, Boolean isAdding) {

        if (isPlayer) {
            for (Iterator<Observer> it = playerObservers.iterator(); it.hasNext();) {
                Observer o = it.next();
                o.update(this, isAdding);
            }
        }
        else
        {
            for (Iterator<Observer> it = viewerObservers.iterator(); it.hasNext();) {
                Observer o = it.next();
                o.update(this, isAdding);
            }

        }

    }

    public BaseClass getValue() {
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
        viewer.previous(Text.class);
        viewer.previous(Image.class);
        Non_playable non_playable = viewer.currently_viewing();
        non_playable.infoNonPlayable();
        dataset.remove(non_playable);

    }

}
