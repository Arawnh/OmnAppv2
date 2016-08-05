package ie.wit.omnappv2.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import ie.wit.omnappv2.R;

public class GymActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gym);

    }

    public void goFood(View v)
    {
        startActivity(new Intent(this, FoodActivity.class));
    }

    public void goGym(View v)
    {

        Toast msg = Toast.makeText(this, "You are at gym page already", Toast.LENGTH_LONG);
        msg.show();
    }

    public void goHome(View v)
    {

        startActivity (new Intent(this, HomeActivity.class));

    }


}
