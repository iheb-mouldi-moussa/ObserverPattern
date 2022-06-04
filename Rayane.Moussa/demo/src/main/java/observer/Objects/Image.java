package observer.Objects;

//import java.util.Observer;

import observer.BaseClass.BaseClass;
import observer.Interfaces.Non_playable;
import observer.Interfaces.Visual;

public class Image extends BaseClass implements Visual, Non_playable{

    private String str1, str2, str3;

    public Image(String str1, String str2, String str3)
    {
        this.str1 = str1;
        this.str2 = str2;
        this.str3 = str3;
        System.out.println("New Image object created with this parameters : " 
        + str1 + " " + str2 + " "+ str3);
    }

    @Override
    public void infoNonPlayable()
    {
        System.out.println("The non-playable object is an image with these parameters : " + str1 + " " + str2 + " "+ str3);
    }

    @Override
    public void infoVisual()
    {
        System.out.println("The visual object is an image with these parameters : " + str1 + " " + str2 + " "+ str3);
        
    }

    @Override
    public String toString()
    {
        return String.format("[ The Image is %s %s %s ]", str1, str2, str3);
    }

    
}
