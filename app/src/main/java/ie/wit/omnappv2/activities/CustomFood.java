package ie.wit.omnappv2.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import ie.wit.omnappv2.R;

public class CustomFood extends NewFoodActivity {

    EditText newFoodName;
    EditText newFoodCal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_food);


        newFoodName = (EditText) findViewById(R.id.newFName_txt);
        newFoodCal = (EditText) findViewById(R.id.newFCal_txt);



    }


    public void addNewFood(View v)
    {

        String tempCal = Integer.toString(app.totalCalories);

        boolean isUpdated = app.myDB.insertFood(newFoodName.getText().toString()+"      ", newFoodCal.getText().toString());


        if (isUpdated) {



            int newCal = Integer.parseInt(newFoodCal.getText().toString());
            app.totalCalories = app.totalCalories+newCal;


            String tempCal2 = Integer.toString(app.totalCalories);
            boolean isAdded = app.myDB.updateCalories(app.useCheck, tempCal2);


            boolean didItWork = app.myDB.eatFood(newFoodName.getText().toString()+"      ", newFoodCal.getText().toString());

            startActivity(new Intent(this, NewFoodActivity.class));
            Toast.makeText(CustomFood.this, "You just ate food worth " + newFoodCal.getText().toString() + " calories", Toast.LENGTH_LONG).show();



        } else {
            Toast.makeText(CustomFood.this, "Something is wrong with the DB!", Toast.LENGTH_LONG).show();
        }

    }








}
