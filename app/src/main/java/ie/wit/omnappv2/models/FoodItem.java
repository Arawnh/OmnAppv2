package ie.wit.omnappv2.models;


public class FoodItem {


    public int offset;
    public String group;
    public String name;
    public String ndbno;


    public FoodItem(int offset, String group, String name, String ndbno)
    {
        this.offset = offset;
        this.group = group;
        this.name = name;
        this.ndbno = ndbno;
    }


    public String getDbNo()
    {

        return ndbno;
    }

    public String getName()
    {

        return name;
    }

    @Override
    public String toString() {
        return  "Name: " +name;
    }
}