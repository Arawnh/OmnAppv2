package ie.wit.omnappv2.activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import ie.wit.omnappv2.R;

public class EatenFoodActivity extends BaseActivity {

    ListView newFoodList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eaten_food);


        ArrayList<String> dbEaten = new ArrayList<>();

        Cursor res = app.myDB.getEatenFood();

        if(res!=null)
        {
        while(res.moveToNext()){
            dbEaten.add("Name: "+res.getString(1) +" Calories: " + res.getString(2));
        }
        }
        else{
            dbEaten.add("There is no foods eaten today");
        }




        newFoodList = (ListView) findViewById(R.id.eatenFoodList);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,  android.R.layout.simple_list_item_1, dbEaten);
        newFoodList.setAdapter(adapter);



    }






    public void popList()
    {



    }


    public void goHome(View v)
    {
        startActivity(new Intent(this, HomeActivity.class));
    }

    public void addNewFood(View v)
    {
        startActivity(new Intent(this, NewFoodActivity.class));
    }

}
