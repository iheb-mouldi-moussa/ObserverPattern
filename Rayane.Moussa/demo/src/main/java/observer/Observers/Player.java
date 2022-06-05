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
    private static int currAudioIndex, currVideoIndex = 0;
    private static Playable currently_playing;

    public Player(Dataset ds)
    {
        ds.register(this);
    }

    public Player()
    {}

    @Override
    public void update(BaseClass arg0, Boolean arg1) {
        
        if(arg1)
        {
            //System.out.println(((Dataset) arg0).getValue());
            objects.add(arg0);
            if(currently_playing == null)
                currently_playing = (Playable) objects.get(0);
            System.out.println("Player observers have been notified and new playable object "  + arg0.toString() + " added");
        }
        else
        {
            if(currently_playing == arg0)
            {
                try {
                    next(arg0.getClass());
                    objects.remove(arg0);
                    return;
                } catch (Exception e) {
                    //TODO: handle exception
                }
            }
            objects.remove(arg0);
            if(arg0 instanceof Video)
            {
                
                currVideoIndex = (currVideoIndex == 0) ? 0 : currVideoIndex--;
                currently_playing = (Playable) objects.get(currVideoIndex);
            } else {
                currAudioIndex = (currAudioIndex == 0) ? 0 : currAudioIndex--;
                currently_playing = (Playable) objects.get(currAudioIndex);
            }
            
            System.out.println("Player observers have been notified and the playable object " + arg0.toString() +" deleted");
        }
        
    }

    public void setCurrIndex() {
        int temp1 = currVideoIndex;
        int temp2 = currAudioIndex;
        while (temp1 < objects.size()) {
            if (objects.get(temp1) instanceof Video)
                break;
            temp1++;
        }
        while (temp2 < objects.size()) {
            if (objects.get(temp2) instanceof Audio)
                break;
            temp2++;
        }
        currAudioIndex = temp2;
        currVideoIndex = temp1;

    }
    public Playable currently_playing() throws ExceptionListEmpty
    {
        if(objects.isEmpty() || currently_playing == null)
        {
            throw new ExceptionListEmpty("Playable List Is Empty");
        }
        return currently_playing;
    }

    public void show_list()
    {
        System.out.println(objects);
    }


    public void next(Class<?> baseClass) throws ExceptionListEmpty
    {
        int saveIndex = currVideoIndex;
        int saveIndex2 = currAudioIndex;
        if(objects.isEmpty())
        {
            throw new ExceptionListEmpty("Playable List Is Empty");
        }
        else if (baseClass == Video.class)
        {
            while(++currVideoIndex < objects.size())
            {
                if(objects.get(currVideoIndex) instanceof Video)
                {
                    currently_playing = (Playable) (objects.get(currVideoIndex));
                    return;
                }
            }

            System.out.println("NO MORE VIDEO !!!!!!!!!!!!!!");
            currVideoIndex = saveIndex;
        }
        else
        {
            while(++currAudioIndex < objects.size())
            {
                if(objects.get(currAudioIndex) instanceof Audio)
                {
                    currently_playing = (Playable) (objects.get(currAudioIndex));
                    return;
                }
            }
            
            System.out.println("NO MORE AUDIO !!!!!!!!!!!!!!");
            currAudioIndex = saveIndex2;
        }
    }

    public void previous(Class<?> baseClass) throws ExceptionListEmpty
    {
        int saveIndex = currVideoIndex;
        int saveIndex2 = currAudioIndex;
        if(objects.isEmpty())
        {
            throw new ExceptionListEmpty("List Is Empty");
        }
        else if (baseClass == Video.class)
        {
            while(--currVideoIndex >= 0)
            {
                if(objects.get(currVideoIndex) instanceof Video)
                {
                    currently_playing = (Playable) (objects.get(currVideoIndex));
                    return;
                }
            }
            
            System.out.println("THIS WAS THE LAST PREVIOUS VIDEO !!!!!!!!!!!!!!");
            currVideoIndex = saveIndex;
        }
        else
        {
            while(--currAudioIndex >= 0)
            {
                if(objects.get(currAudioIndex) instanceof Audio)
                {
                    currently_playing = (Playable) (objects.get(currAudioIndex));
                    return;
                }
            }
            
            System.out.println("NO PREVIOUS AUDIO !!!!!!!!!!!!!!");
            currAudioIndex = saveIndex2;
        }
    }


   
    
}
