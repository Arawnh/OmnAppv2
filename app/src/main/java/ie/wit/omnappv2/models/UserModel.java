package ie.wit.omnappv2.models;



public class UserModel {

    private String name;
    private int totalCalories;

        public UserModel(String name, int totalCalories)
        {
            this.name = name;
            this.totalCalories = totalCalories;

        }


    public String getName()
    {
    return name;
    }

    public int getTotalCalories()
    {
        return totalCalories;
    }

    public void setCalories(int totalCalories)
    {
        this.totalCalories = totalCalories;

    }
}
