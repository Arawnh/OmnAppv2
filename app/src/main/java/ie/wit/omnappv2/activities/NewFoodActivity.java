package ie.wit.omnappv2.activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import ie.wit.omnappv2.R;
import ie.wit.omnappv2.models.FoodModel;


public class NewFoodActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_food);
        ListView newFoodList;


        final ArrayList<FoodModel> dbFoods = new ArrayList<>();

        Cursor res = app.myDB.getAllFood();



        while(res.moveToNext()){
                String t1 = res.getString(1);
                String t2 = res.getString(2);
                dbFoods.add(new FoodModel(t1,t2));
        }



            newFoodList = (ListView) findViewById(R.id.newFoodList);
            ArrayAdapter<FoodModel> adapter = new ArrayAdapter<FoodModel>(this,  android.R.layout.simple_list_item_1, dbFoods);
            newFoodList.setAdapter(adapter);


             AdapterView.OnItemClickListener click1 = new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                    String tempName = dbFoods.get(position).getFoodName();
                    String tempCal = dbFoods.get(position).getCalories();
                    boolean didItWork = app.myDB.eatFood(tempName,tempCal);



                    String cleanCal = tempCal.replaceAll("[^0-9]", "");
                    int newCal = Integer.parseInt(cleanCal);
                    int newTotal = app.totalCalories+newCal;
                    app.totalCalories = newTotal;



                    String tempCal2 = Integer.toString(app.totalCalories);
                    boolean isUpdated = app.myDB.updateCalories(app.useCheck, tempCal2);
                    Toast.makeText(getBaseContext(), "You just ate food worth " + newCal + " calories", Toast.LENGTH_LONG).show();


                }
            };

        newFoodList.setOnItemClickListener(click1);
    }



    public void goFood(View v)
    {

        startActivity(new Intent(this, EatenFoodActivity.class));
    }

    public void addCustom(View v)
    {

        startActivity(new Intent(this, CustomFood.class));
    }

    public void goSearchFood(View v)
    {

        startActivity(new Intent(this, SearchActivity.class));

    }

}
