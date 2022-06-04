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

    public String getStr1() {
        return this.str1;
    }

    public void setStr1(String str1) {
        this.str1 = str1;
    }

    public String getStr2() {
        return this.str2;
    }

    public void setStr2(String str2) {
        this.str2 = str2;
    }

    public String getStr3() {
        return this.str3;
    }

    public void setStr3(String str3) {
        this.str3 = str3;
    }

    @Override
    public void info()
    {

    }
}
