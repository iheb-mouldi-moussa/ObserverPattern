package observer.Observers;

import java.util.ArrayList;
import java.util.List;

import observer.BaseClass.BaseClass;
import observer.Exceptions.ExceptionListEmpty;
import observer.Interfaces.Playable;
import observer.Objects.Audio;
import observer.Objects.Video;
import observer.Observable.Dataset;
import observer.Observable.Observable;
//import observer.Observers.Observer;

public class Player implements Observer {



    private List<BaseClass> objects = new ArrayList<>();
    private static int currImageIndex, currTextIndex = 0;
    private static Playable playable;
    @Override
    public void update(Observable arg0, Boolean arg1) {
        
        if(arg0 instanceof Dataset && arg1)
        {
            //System.out.println(((Dataset) arg0).getValue());
            objects.add(((Dataset) arg0).getValue());
            playable = (Playable) objects.get(0);
            System.out.println("Player observers have been notified and new playable object added");
        }
        else
        {
            objects.remove(((Dataset) arg0).getValue());
            playable =  objects.isEmpty() ? null  : (Playable) objects.get(0);
            System.out.println("Player observers have been notified and new playable object deleted");
        }
        
    }

    public Playable currently_playing() throws ExceptionListEmpty
    {
        if(objects.isEmpty() || playable == null)
        {
            throw new ExceptionListEmpty("Playable List Is Empty");
        }
        return playable;
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
        else if (baseClass == Video.class)
        {
            while(++currTextIndex < objects.size())
            {
                if(objects.get(currTextIndex) instanceof Video)
                {
                    playable = (Playable) (objects.get(currTextIndex));
                    return;
                }
            }
            
            System.out.println("NO MORE VIDEO !!!!!!!!!!!!!!");
            currTextIndex = saveIndex;
        }
        else
        {
            while(++currImageIndex < objects.size())
            {
                if(objects.get(currImageIndex) instanceof Audio)
                {
                    playable = (Playable) (objects.get(currImageIndex));
                    return;
                }
            }
            
            System.out.println("NO MORE AUDIO !!!!!!!!!!!!!!");
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
        else if (baseClass == Video.class)
        {
            while(--currTextIndex >= 0)
            {
                if(objects.get(currTextIndex) instanceof Video)
                {
                    playable = (Playable) (objects.get(currTextIndex));
                    return;
                }
            }
            
            System.out.println("THIS WAS THE LAST PREVIOUS VIDEO !!!!!!!!!!!!!!");
            currTextIndex = saveIndex;
        }
        else
        {
            while(--currImageIndex >= 0)
            {
                if(objects.get(currImageIndex) instanceof Audio)
                {
                    playable = (Playable) (objects.get(currImageIndex));
                    return;
                }
            }
            
            System.out.println("NO PREVIOUS AUDIO !!!!!!!!!!!!!!");
            currImageIndex = saveIndex2;
        }
    }


   
    
}
