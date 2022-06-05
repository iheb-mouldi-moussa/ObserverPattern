package observer.Observers;

//import java.lang.ProcessBuilder.Redirect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.View;

//import javax.lang.model.util.ElementScanner6;

import observer.BaseClass.BaseClass;
import observer.Exceptions.ExceptionListEmpty;
import observer.Interfaces.Non_playable;
//import observer.Interfaces.Playable;
import observer.Objects.Image;
import observer.Objects.Text;
import observer.Observable.Dataset;
import observer.Observable.Observable;

//import observer.Observers.Observer;
public class Viewer implements Observer {

    private List<BaseClass> objects = new ArrayList<>();
    private static int currImageIndex, currTextIndex = 0;
    private static Non_playable currently_viewing = null;

    public Viewer(Dataset ds)
    {
        ds.register(this);
    }

    public Viewer()
    {}

    @Override
    public void update(BaseClass arg0, Boolean arg1) {

        if (arg1) {
            // System.out.println(((Dataset) arg0).getValue());
            objects.add(arg0);
            if (currently_viewing == null)
                currently_viewing = (Non_playable) objects.get(0);
            System.out.println(
                    "Viewer observer has been notified and new non-playabled object " + arg0.toString() + " added");
        } else {
            if (currently_viewing == arg0) {
                try {
                    next(arg0.getClass());
                    objects.remove(arg0);
                    return;
                } catch (ExceptionListEmpty e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            objects.remove(arg0);
            if (arg0 instanceof Text) {
                currTextIndex = (currTextIndex == 0) ? 0 : currTextIndex--;
                currently_viewing = (Non_playable) objects.get(currTextIndex);
            } else {
                currImageIndex = (currImageIndex == 0) ? 0 : currImageIndex--;
                currently_viewing = (Non_playable) objects.get(currImageIndex);
            }
            // else
            // currently_viewing = objects.isEmpty() ? null : (Non_playable) objects.get(0);
            // System.out.println("Current viewing is " + currently_viewing);
            System.out.println(
                    "Viewer observer has been notified and the non-playable object " + arg0.toString() + " deleted");
        }

    }

    public void setCurrIndex() {
        int temp1 = currTextIndex;
        int temp2 = currImageIndex;
        while (temp1 < objects.size()) {
            if (objects.get(temp1) instanceof Text)
                break;
            temp1++;
        }
        while (temp2 < objects.size()) {
            if (objects.get(temp2) instanceof Image)
                break;
            temp2++;
        }
        currImageIndex = temp2;
        currTextIndex = temp1;

    }

    public Non_playable currently_viewing() throws ExceptionListEmpty {
        if (objects.isEmpty() || currently_viewing == null) {
            throw new ExceptionListEmpty("NonPlayable List Is Empty");
        }
        return currently_viewing;
    }

    public void show_list() {
        System.out.println(objects);
    }

    public void next(Class<?> baseClass) throws ExceptionListEmpty {
        setCurrIndex();
        int saveIndex = currTextIndex;
        int saveIndex2 = currImageIndex;
        if (objects.isEmpty()) {
            throw new ExceptionListEmpty("NonPlayable List Is Empty");
        } else if (baseClass == Text.class) {
            while (++currTextIndex < objects.size()) {
                if (objects.get(currTextIndex) instanceof Text) {
                    currently_viewing = (Non_playable) (objects.get(currTextIndex));
                    return;
                }
            }

            System.out.println("NO MORE TEXT !!!!!!!!!!!!!!");
            currTextIndex = saveIndex;
        } else {
            while (++currImageIndex < objects.size()) {
                if (objects.get(currImageIndex) instanceof Image) {
                    currently_viewing = (Non_playable) (objects.get(currImageIndex));
                    return;
                }
            }

            System.out.println("NO MORE IMAGE !!!!!!!!!!!!!!");
            currImageIndex = saveIndex2;
        }
    }

    public void previous(Class<?> baseClass) throws ExceptionListEmpty {
        setCurrIndex();
        int saveIndex = currTextIndex;
        int saveIndex2 = currImageIndex;
        if (objects.isEmpty()) {
            throw new ExceptionListEmpty("List Is Empty");
        } else if (baseClass == Text.class) {
            while (--currTextIndex >= 0) {
                if (objects.get(currTextIndex) instanceof Text) {
                    currently_viewing = (Non_playable) (objects.get(currTextIndex));
                    return;
                }
            }

            System.out.println("NO PREVIOUS TEXT !!!!!!!!!!!!!!");
            currTextIndex = saveIndex;
        } else {
            while (--currImageIndex >= 0) {
                if (objects.get(currImageIndex) instanceof Image) {
                    currently_viewing = (Non_playable) (objects.get(currImageIndex));
                    return;
                }
            }

            System.out.println("NO PREVIOUS IMAGE !!!!!!!!!!!!!!");
            currImageIndex = saveIndex2;
        }
    }

}
