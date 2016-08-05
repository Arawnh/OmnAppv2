package ie.wit.omnappv2.models;

/**
 * Created by Pav on 12/03/2016.
 */
public class FoodModel {

    public String foodID;
    public String calories;
    public String foodName;

    public FoodModel(String foodName, String calories)
    {
        this.calories = calories;
        this.foodName = foodName;

    }


    public String getCalories()
    {
        return calories;
    }

    public String getFoodName()
    {
        return foodName;
    }

    @Override
    public String toString() {
        return "" + foodName + "   Calories: " +calories;
    }
}
