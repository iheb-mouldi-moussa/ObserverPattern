package observer.Objects;

import observer.BaseClass.BaseClass;
import observer.Interfaces.Non_visual;
import observer.Interfaces.Playable;

public class Audio extends BaseClass implements Playable, Non_visual {
    
    private String str1;
    private String str2;
    private String str3;

    public Audio(String str1, String str2, String str3)
    {
        this.str1 = str1;
        this.str2 = str2;
        this.str3 = str3;
        System.out.println("New Audio object created with this parameters : " 
        + str1 + " " + str2 + " "+ str3);
    }
    @Override
    public void infoNonVisual()
    {

        System.out.println("The non-visual object is an audio with these parameters : " + str1 + " " + str2 + " "+ str3);
    }

    @Override
    public void infoPlayable()
    {

        System.out.println("The playable object is an audio with these parameters : " + str1 + " " + str2 + " "+ str3);
    }


    @Override
    public String toString()
    {
        return String.format("[ The Audio is %s %s %s ]", str1, str2, str3);
    }
}
