package ie.wit.omnappv2.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import ie.wit.omnappv2.R;

public class EditActivity extends BaseActivity {

    EditText editName, editWeight, editHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        editName = (EditText) findViewById(R.id.editTextName);
        editWeight = (EditText) findViewById(R.id.editTextWeight);
        editHeight = (EditText) findViewById(R.id.editTextHeight);

    }


    public void userUpdate(View v) {

        //startActivity (new Intent(this, HomeActivity.class));
        //
        String tempCal = Integer.toString(app.totalCalories);

        boolean isUpdated = app.myDB.updateData(app.useCheck, editName.getText().toString(), editWeight.getText().toString(),
                editHeight.getText().toString(), tempCal);


        if (isUpdated) {
            // app.useCheck == null;

            startActivity (new Intent(this, HomeActivity.class));
            //Toast.makeText(EditActivity.this, "Db working!!", Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(EditActivity.this, "Something is wrong with the DB!", Toast.LENGTH_LONG).show();
        }

    }

}
