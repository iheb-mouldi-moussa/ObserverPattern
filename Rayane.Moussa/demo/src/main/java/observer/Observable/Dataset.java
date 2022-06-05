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
        System.out.println("New Objects has been added to the Dataset collection\n");
        // setChanged();
        if (baseClass instanceof Video || baseClass instanceof Audio)
            notifyObservers(true, true, baseClass);
        else
            notifyObservers(false, true, baseClass);
    }

    public void remove(BaseClass baseClass) {
        this.objects.remove(baseClass);
        System.out.println("New Objects has been deleted from the Dataset collection\n");
        // setChanged();
        if (baseClass instanceof Video || baseClass instanceof Audio)
            notifyObservers(true, false, baseClass);
        else
            notifyObservers(false, false, baseClass);

    }

    public void remove(Non_playable non_playable) {
        if (non_playable instanceof Image) {
            BaseClass baseClass = (Image) non_playable;
            remove(baseClass);
        } else {
            BaseClass baseClass = (Text) non_playable;
            remove(baseClass);

        }
    }

    public void remove(Non_visual non_visual) {
        if (non_visual instanceof Text) {
            BaseClass baseClass = (Text) non_visual;
            remove(baseClass);
        } else {
            BaseClass baseClass = (Audio) non_visual;
            remove(baseClass);
        }
    }

    public void remove(Playable Playable) {
        if (Playable instanceof Text) {
            BaseClass baseClass = (Text) Playable;
            remove(baseClass);
        } else {
            BaseClass baseClass = (Audio) Playable;
            remove(baseClass);
        }
    }

    public void remove(Visual Visual) {
        if (Visual instanceof Text) {
            BaseClass baseClass = (Text) Visual;
            remove(baseClass);
        } else {
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
    public void notifyObservers(Boolean isPlayer, Boolean isAdding, BaseClass baseClass) {

        if (isPlayer) {
            for (Iterator<Observer> it = playerObservers.iterator(); it.hasNext();) {
                Observer o = it.next();
                o.update(baseClass, isAdding);
            }
        } else {
            for (Iterator<Observer> it = viewerObservers.iterator(); it.hasNext();) {
                Observer o = it.next();
                o.update(baseClass, isAdding);
            }

        }

    }

    public BaseClass getValue() {
        return objects.get(0);
    }

    public static void main(String[] args) throws ExceptionListEmpty {
        Dataset ds = new Dataset();
        Player p1 = new Player();
        Viewer v1 = new Viewer();
        ds.register(p1);
        ds.register(v1);

        ds.add(new Audio("audioname1", "duration1", "other info1"));
        ds.add(new Audio("audioname2", "duration2", "other info2"));
        ds.add(new Audio("audioname3", "duration3", "other info3"));

        ds.add(new Video("videoname1", "duration1", "other info1"));
        ds.add(new Video("videoname2", "duration2", "other info2"));
        ds.add(new Video("videoname3", "duration3", "other info3"));
      
    
        ds.add(new Text("textname1", "other info1"));
        ds.add(new Text("textname2", "other info2"));
        ds.add(new Text("textname3", "other info3"));

        ds.add(new Image("imagename1", "dimension info1", "other info1"));
        ds.add(new Image("imagename2", "dimension info2", "other info2"));
        ds.add(new Image("imagename3", "dimension info3", "other info3"));
        ds.add(new Image("imagename4", "dimension info4", "other info4"));
        ds.add(new Image("imagename5", "dimension info5", "other info5"));

        p1.show_list();
        v1.show_list();

        Playable po = p1.currently_playing();
        po.infoPlayable();
        p1.next(Audio.class);
        po = p1.currently_playing();
        po.infoPlayable();
        p1.show_list();
        p1.previous(Audio.class);
        po = p1.currently_playing();
        po.infoPlayable();

       Non_playable np = v1.currently_viewing();
        np.infoNonPlayable();
        v1.next(Image.class);
        np = v1.currently_viewing();
        np.infoNonPlayable();
        v1.previous(Image.class);
        np = v1.currently_viewing();
        np.infoNonPlayable();
        ds.remove(np);
        v1.show_list();
        Non_playable npp = v1.currently_viewing();
        npp.infoNonPlayable();

    }

}
