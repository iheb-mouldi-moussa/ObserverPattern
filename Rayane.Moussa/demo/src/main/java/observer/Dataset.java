package observer;

import java.util.ArrayList;
import java.util.List;

import javax.xml.crypto.Data;

public class Dataset {
   

    private List<BaseClass> objects = new ArrayList<>();
    public void add(BaseClass baseClass)
    {
        this.objects.add(baseClass);
        if(baseClass instanceof Image)
        {
            System.out.println("Image object");   
        }
        System.out.println("New Objects has been added to the collection");
    }

    public static void main(String[] args) {
        Dataset dataset = new Dataset();
        dataset.add(new Image());
    }
}
