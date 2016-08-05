package ie.wit.omnappv2.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import ie.wit.omnappv2.R;

public class WelcomeActivity extends BaseActivity {


    EditText editName, editWeight, editHeight;
    //Button enterAppBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        editName = (EditText) findViewById(R.id.editTextName);
        editWeight = (EditText) findViewById(R.id.editTextWeight);
        editHeight = (EditText) findViewById(R.id.editTextHeight);
        //enterAppBtn = (Button) findViewById(R.id.enterAppBtn);

    }

    public void goHome(View v) {

        String tempCal = Integer.toString(app.totalCalories);

        boolean isInserted = app.myDB.insertData(editName.getText().toString(), editWeight.getText().toString(),
                editHeight.getText().toString(), tempCal);


        if (isInserted) {
            // app.useCheck == null;

            startActivity (new Intent(this, HomeActivity.class));
            //Toast.makeText(WelcomeActivity.this, "Db working!!", Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(WelcomeActivity.this, "Something is wrong with the DB!", Toast.LENGTH_LONG).show();
        }

    }




}
