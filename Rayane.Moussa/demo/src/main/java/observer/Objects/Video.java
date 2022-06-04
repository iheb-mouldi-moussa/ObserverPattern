package observer.Objects;

import observer.BaseClass.BaseClass;
import observer.Interfaces.Playable;
import observer.Interfaces.Visual;

public class Video extends BaseClass implements Visual, Playable{

    

    private String str1, str2, str3;

    public Video(String str1, String str2, String str3)
    {
        this.str1 = str1;
        this.str2 = str2;
        this.str3 = str3;
        System.out.println("New Video object created with this parameters : " 
        + str1 + " " + str2 + " "+ str3);
    }

    @Override
    public void infoPlayable() {
       
        System.out.println("The playable object is a video with these parameters : " + str1 + " " + str2 + " "+ str3);
    }

    @Override
    public void infoVisual() {
        
        System.out.println("The visual object is a video with these parameters : " + str1 + " " + str2 + " "+ str3);
    }
    
    @Override
    public String toString()
    {
        return String.format("[ The Video is %s %s %s ]", str1, str2, str3);
    }
}
