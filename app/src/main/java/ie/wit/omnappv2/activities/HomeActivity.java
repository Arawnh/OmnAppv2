package ie.wit.omnappv2.activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import ie.wit.omnappv2.R;
import ie.wit.omnappv2.models.FoodModel;

public class HomeActivity extends BaseActivity {

    private TextView userName;
    private TextView userCalories;
    private TextView userBmi;
    private TextView userLeftCalories;
    private TextView weightText;
    private TextView heightText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        viewAll();
        popFood();
        calculateBMI();

        if(app.useCheck == null) {

            startActivity (new Intent(this, WelcomeActivity.class));
        }


        //String caloryString = "" + user1.getTotalCalories();
        userName   = (TextView)     findViewById(R.id.userNameText);
        userCalories   = (TextView)     findViewById(R.id.userCaloriesText);
        userBmi = (TextView)     findViewById(R.id.text_userBMI);
        userLeftCalories   = (TextView)     findViewById(R.id.text_calLeft);
        weightText = (TextView) findViewById(R.id.weightText);
        heightText = (TextView) findViewById(R.id.heightText);
        userName.setText("Welcome to OmnApp " + app.userName+ "!");


        userBmi.setText("Your base metabolic rate is " + app.userBMI);
        userCalories.setText("Today you ate food worth " + app.totalCalories + " calories");
        userLeftCalories.setText("Your total calories left are " + (app.userBMI - app.totalCalories));
        weightText.setText("Your weight is: "+app.weight+"kg");
        heightText.setText("Your height is: "+app.height+"cm");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void goFood(View v)
    {
        startActivity (new Intent(this, EatenFoodActivity.class));
    }

    public void goGym(View v)
    {
        startActivity (new Intent(this, GymActivity.class));
    }

    public void goHome(View v)
    {
        Toast msg = Toast.makeText(this, "You are home already", Toast.LENGTH_LONG);
        msg.show();

    }

    public void viewAll()
    {
        Cursor res = app.myDB.getAllData();



        while(res.moveToNext()){
            if(res.getString(0).equalsIgnoreCase("1")) {
                app.useCheck = res.getString(0);
                app.userName = res.getString(1);
                app.weight = Integer.parseInt(res.getString(2));
                app.height = Integer.parseInt(res.getString(3));
                app.totalCalories = Integer.parseInt(res.getString(4));
            }
        }



    }

    public void popFood()
    {
        if(app.useCheck == null)
        {


            ArrayList<FoodModel> newFoods = new ArrayList<>();
            newFoods.add(new FoodModel("Rice        ", "300"));
            newFoods.add(new FoodModel("Bread       ", "350"));
            newFoods.add(new FoodModel("Potatoes    ", "200"));
            newFoods.add(new FoodModel("Banana      ", "455"));
            newFoods.add(new FoodModel("Peach       ", "700"));
            newFoods.add(new FoodModel("Steak       ", "500"));
            newFoods.add(new FoodModel("Peach       ", "421"));
            newFoods.add(new FoodModel("Sushi       ", "400"));
            newFoods.add(new FoodModel("Sashimi     ", "450"));
            newFoods.add(new FoodModel("Mochi       ", "450"));
            newFoods.add(new FoodModel("Seaweed     ", "780"));
            newFoods.add(new FoodModel("Ramen       ", "700"));
            newFoods.add(new FoodModel("Katsucurry  ", "800"));


            for(FoodModel asd : newFoods) {
                String t1 = asd.getFoodName();
                String t2 = asd.getCalories();


                boolean didItWork = app.myDB.insertFood(t1, t2);

                if (didItWork == false) {
                    Toast.makeText(getBaseContext(), " DB FAIL", Toast.LENGTH_LONG).show();
                }

            }

        }



    }






    public void calculateBMI()
    {
         app.userBMI = (10*(app.weight)+ 6*(app.height)-50+5);
    }

    public void goEdit(View v)
    {
        startActivity (new Intent(this, EditActivity.class));

    }

    public void resetFoods(View v)
    {

        app.myDB.deleteEatenFood();
        boolean isUpdated = app.myDB.updateCalories(app.useCheck, "0");
        app.totalCalories = 0;

        Toast.makeText(getBaseContext(), " Eaten foods deleted", Toast.LENGTH_LONG).show();

        startActivity(new Intent(this, HomeActivity.class));


    }






    }

