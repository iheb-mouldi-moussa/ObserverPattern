package observer.Observers;

//import java.lang.ProcessBuilder.Redirect.Type;
import java.util.ArrayList;
import java.util.List;

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
    private static Non_playable non_playable;
    @Override
    public void update(Observable arg0, Boolean arg1) {
        
        if(arg0 instanceof Dataset && arg1)
        {
            //System.out.println(((Dataset) arg0).getValue());
            objects.add(((Dataset) arg0).getValue());
            non_playable = (Non_playable) objects.get(0);
            System.out.println("Viewers observers have been notified and new non-playable object added");
        }
        else
        {
            objects.remove(((Dataset) arg0).getValue());
            non_playable =  objects.isEmpty() ? null  : (Non_playable) objects.get(0);
            System.out.println("Viewers observers have been notified and new non-playable object deleted");
        }
        
    }

    public Non_playable currently_viewing() throws ExceptionListEmpty
    {
        if(objects.isEmpty() || non_playable == null)
        {
            throw new ExceptionListEmpty("NonPlayable List Is Empty");
        }
        return non_playable;
    }

    public void show_list()
    {
        System.out.println(objects);
    }


    public void next(Class<?> baseClass) throws ExceptionListEmpty
    {
        int saveIndex = currTextIndex;
        int saveIndex2 = currImageIndex;
        if(objects.isEmpty())
        {
            throw new ExceptionListEmpty("NonPlayable List Is Empty");
        }
        else if (baseClass == Text.class)
        {
            while(++currTextIndex < objects.size())
            {
                if(objects.get(currTextIndex) instanceof Text)
                {
                    non_playable = (Non_playable) (objects.get(currTextIndex));
                    return;
                }
            }
            
            System.out.println("NO MORE TEXT !!!!!!!!!!!!!!");
            currTextIndex = saveIndex;
        }
        else
        {
            while(++currImageIndex < objects.size())
            {
                if(objects.get(currImageIndex) instanceof Image)
                {
                    non_playable = (Non_playable) (objects.get(currImageIndex));
                    return;
                }
            }
            
            System.out.println("NO MORE IMAGE !!!!!!!!!!!!!!");
            currImageIndex = saveIndex2;
        }
    }

    public void previous(Class<?> baseClass) throws ExceptionListEmpty
    {
        int saveIndex = currTextIndex;
        int saveIndex2 = currImageIndex;
        if(objects.isEmpty())
        {
            throw new ExceptionListEmpty("List Is Empty");
        }
        else if (baseClass == Text.class)
        {
            while(--currTextIndex >= 0)
            {
                if(objects.get(currTextIndex) instanceof Text)
                {
                    non_playable = (Non_playable) (objects.get(currTextIndex));
                    return;
                }
            }
            
            System.out.println("NO PREVIOUS TEXT !!!!!!!!!!!!!!");
            currTextIndex = saveIndex;
        }
        else
        {
            while(--currImageIndex >= 0)
            {
                if(objects.get(currImageIndex) instanceof Image)
                {
                    non_playable = (Non_playable) (objects.get(currImageIndex));
                    return;
                }
            }
            
            System.out.println("NO PREVIOUS IMAGE !!!!!!!!!!!!!!");
            currImageIndex = saveIndex2;
        }
    }

    
}
