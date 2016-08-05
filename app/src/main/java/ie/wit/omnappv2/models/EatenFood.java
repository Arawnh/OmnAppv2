package ie.wit.omnappv2.models;



public class EatenFood {


    public int foodID;
    public int caloriesAmount;
    public String foodName;



    public EatenFood (int caloriesAmount, String foodName)
    {
        this.caloriesAmount = caloriesAmount;
        this.foodName = foodName;
    }


    public int getCaloriesAmount()
    {
        return caloriesAmount;
    }

    @Override
    public String toString() {
        return
                "Food: " + foodName + " Calories: " + caloriesAmount;
    }
}
