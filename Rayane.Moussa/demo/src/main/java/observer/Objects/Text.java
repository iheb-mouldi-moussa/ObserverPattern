package observer.Objects;

import observer.BaseClass.BaseClass;
import observer.Interfaces.Non_playable;

public class Text extends BaseClass implements Non_playable {
    
    private String str1, str2;

    public Text(String str1, String str2)
    {
        this.str1 = str1;
        this.str2 = str2;
        System.out.println("New Text object created with this parameters : " 
        + str1 + " " + str2);
    }

    @Override
    public void info()
    {
        System.out.println("The non-playable object is a text with these parameters : " + str1 + " " + str2);
    }

    @Override
    public String toString()
    {
        return String.format("The text is %s %s", str1, str2);
    }
}
