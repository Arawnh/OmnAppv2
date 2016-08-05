package ie.wit.omnappv2.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import ie.wit.omnappv2.R;
import ie.wit.omnappv2.models.FoodItem;

public class SearchActivity extends BaseActivity {
    ArrayList<FoodItem> item = new ArrayList<FoodItem>();
    ListView newList;
    TextView newText;
    EditText newEdit;
    String finalList = "";
    List<FoodItem> derp = new ArrayList<FoodItem>();
    String tempDBNO="";
    String tempName="";
    String tempCal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        newList = (ListView) findViewById(R.id.listView);
        newEdit = (EditText) findViewById(R.id.editText);

        Button btnHit = (Button) findViewById(R.id.button);


        btnHit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try  {
                    InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                } catch (Exception e) {

                }






                String searchWord = newEdit.getText().toString();

                new JSONTask().execute("http://api.nal.usda.gov/ndb/search/?format=json&q="+searchWord+"&sort=n&max=100&offset=0&api_key=1GVsnHXzfzbRyUjshBIY4VNqzC2yRZuZ1C8QcMHR");
                //searchApi();
            }
        });








    }




    public void testTest(final List<FoodItem> list)
    {


        ArrayAdapter<FoodItem> adapter = new ArrayAdapter<FoodItem>(this, android.R.layout.simple_list_item_1, list);
        newList.setAdapter(adapter);

        AdapterView.OnItemClickListener click1 = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



                tempDBNO = list.get(position).getDbNo();
                tempName = list.get(position).getName();




                new JSONTask2().execute("http://api.nal.usda.gov/ndb/reports/?ndbno="+tempDBNO+"&type=b&format=json&api_key=1GVsnHXzfzbRyUjshBIY4VNqzC2yRZuZ1C8QcMHR");

            }
        };

        newList.setOnItemClickListener(click1);



    }

    public void showNewCals(String result)
    {

        String newCal = result;
        String newName = tempName;
        boolean didItWork = app.myDB.insertFood(newName, newCal);


        if (didItWork) {



            int newCal2 = Integer.parseInt(newCal);
            app.totalCalories = app.totalCalories+newCal2;


            String tempCal2 = Integer.toString(app.totalCalories);
            boolean isAdded = app.myDB.updateCalories(app.useCheck, tempCal2);


            boolean didItWork2 = app.myDB.eatFood(newName, newCal);

            startActivity(new Intent(this, NewFoodActivity.class));
            Toast.makeText(SearchActivity.this, "You just ate food worth " + newCal + " calories", Toast.LENGTH_LONG).show();



        } else {
            Toast.makeText(SearchActivity.this, "Something is wrong with the DB!", Toast.LENGTH_LONG).show();
        }






        Toast.makeText(getBaseContext(), "Item "+tempName +" calories are: "+result, Toast.LENGTH_LONG).show();






    }




    class JSONTask extends AsyncTask<String, String, List<FoodItem>> {


        @Override
        protected List<FoodItem> doInBackground(String... params) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            //String newText="";

            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer = new StringBuffer();

                String line = "";
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                String finalJson = buffer.toString();
                JSONObject parentObject = new JSONObject(finalJson);
                JSONObject parentList = parentObject.getJSONObject("list");
                JSONArray itemArray = parentList.getJSONArray("item");


                List<FoodItem> newFoodList = new ArrayList<FoodItem>();

                StringBuffer finalBufferedData = new StringBuffer();
                for (int i = 0; i < itemArray.length(); i++) {
                    JSONObject finalObject = itemArray.getJSONObject(i);
                    int k1 = finalObject.getInt("offset");
                    String k2 = finalObject.getString("group");
                    String k3 = finalObject.getString("name");
                    String k4 = finalObject.getString("ndbno");
                    newFoodList.add(new FoodItem(k1, k2, k3, k4));
                }


                return newFoodList;

                //return buffer.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            //  return newText;

            //return "DErp";
            return null;
        }


        @Override
        protected void onPostExecute(List<FoodItem> result) {
            super.onPostExecute(result);
            //finalList = result;
            //newText.setText(result);

            derp =result;

            testTest(result);




        }
    }











    class JSONTask2 extends AsyncTask<String, String, String> {


        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            //String newText="";

            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer = new StringBuffer();

                String line = "";
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                String finalJson =  buffer.toString();
                JSONObject parentObject = new JSONObject(finalJson);
                JSONObject parentList = parentObject.getJSONObject("report");
                JSONObject secondList = parentList.getJSONObject("food");
                JSONArray itemArray = secondList.getJSONArray("nutrients");


                StringBuffer finalBufferedData = new StringBuffer();
                /*for(int i=0; i < itemArray.length(); i++)
                {
                    JSONObject finalObject = itemArray.getJSONObject(i);
                    //int k1  = finalObject.getInt("offset");
                   // String k2 = finalObject.getString("group");
                    //String k3 = finalObject.getString("name");
                    //String k4 = finalObject.getString("ndbno");
                    //finalBufferedData.append(k1 + k2+" - "+k3+ " - "+k4+"\n");


                    String k1 = finalObject.getString("name");
                    String k2 = finalObject.getString("group");
                    String k3 = finalObject.getString("value");
                    finalBufferedData.append(k1+" " + k2+ " " + k3 + "\n");
                }*/

                JSONObject jsonCal = itemArray.getJSONObject(1);
                String k1 = jsonCal.getString("name");
                String k2 = jsonCal.getString("value");


                return k2;

                //return buffer.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            //  return newText;

            return "DErp";
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            //newText.setText(result);
            tempCal = result;
            showNewCals(result);

        }

    }

}
