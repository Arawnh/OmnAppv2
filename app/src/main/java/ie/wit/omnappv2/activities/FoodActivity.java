package ie.wit.omnappv2.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ie.wit.omnappv2.R;
import ie.wit.omnappv2.models.EatenFood;

public class FoodActivity extends BaseActivity {

    HashMap<String, List<EatenFood>> Food_types;
    List<String> Foods_list;
    ExpandableListView Exp_list;
    FoodsAdapter adapter;
    int previousItem = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        Exp_list = (ExpandableListView) findViewById(R.id.exp_list);

        Food_types = DataProvider.getInfo();

        Foods_list = new ArrayList<String>(Food_types.keySet());
        adapter= new FoodsAdapter(this, Food_types, Foods_list);
        Exp_list.setAdapter(adapter);
        //Exp_list.ex

        Exp_list.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {


            @Override
            public void onGroupExpand(int groupPosition) {
                if(groupPosition != previousItem )
                    Exp_list.collapseGroup(previousItem);
                previousItem = groupPosition;
            }

        });

        Exp_list.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                /*Toast.makeText(getBaseContext(),

                        Food_types.get(Foods_list.get(groupPosition)).get(childPosition)+ "from category " +
                                Foods_list.get(groupPosition) + " is selected ", Toast.LENGTH_LONG).show(); */
                    String derp1 ="" + Food_types.get(Foods_list.get(groupPosition)).get(childPosition);
                    String derp = derp1.replaceAll("[^0-9]", "");
                    int newDerp = Integer.parseInt(derp);
                    int newTotal = app.totalCalories+newDerp;





                    app.totalCalories = newTotal;
                    String tempCal = Integer.toString(app.totalCalories);
                    boolean isUpdated = app.myDB.updateCalories(app.useCheck, tempCal);
                    Toast.makeText(getBaseContext(), app.totalCalories+" is current calories", Toast.LENGTH_LONG).show();

                return false;
            }
        });




    }


    public void addNewFood(View v)
    {


        startActivity(new Intent(this, NewFoodActivity.class));

    }

    public void goFood(View v)
    {
        Toast msg = Toast.makeText(this, "You are at food page already", Toast.LENGTH_LONG);
        msg.show();
    }

    public void goGym(View v)
    {


        startActivity(new Intent(this, GymActivity.class));

    }

    public void goHome(View v)
    {

        startActivity (new Intent(this, HomeActivity.class));

    }
}

class FoodsAdapter extends BaseExpandableListAdapter
{
    private Context ctx;
    private HashMap<String, List<EatenFood>> Food_types;
    private List<String> Foods_list;


    public FoodsAdapter(Context ctx, HashMap<String, List<EatenFood>> Food_types, List<String> Foods_list)
    {
        this.ctx=ctx;
        this.Food_types = Food_types;
        this.Foods_list = Foods_list;
    }

    @Override
    public int getGroupCount() {
        return Foods_list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {



        return Food_types.get(Foods_list.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return Foods_list.get(groupPosition);
    }

    @Override
    public Object getChild(int parent, int child) {


        return Food_types.get(Foods_list.get(parent)).get(child);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int parent, int child) {
        return child;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int parent, boolean isExpanded, View convertView, ViewGroup parentView) {
        String group_title = (String) getGroup(parent);

        if(convertView ==  null)
        {
            LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.layout_test, parentView, false);
        }

        TextView parent_textview = (TextView) convertView.findViewById(R.id.parent_txt);

        parent_textview.setTypeface(null, Typeface.BOLD);
        parent_textview.setText(group_title);
        return convertView;


    }

    @Override
    public View getChildView(int parent, int child, boolean lastChild, View convertview, ViewGroup parentView) {


        EatenFood child_title = (EatenFood) getChild(parent, child);
        String foodText = child_title.toString();
        if(convertview == null) {

            LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertview = inflater.inflate(R.layout.child_layout, parentView, false);
        }
        TextView child_textview = (TextView) convertview.findViewById(R.id.child_txt);
        child_textview.setText(foodText);


        return convertview;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}


class DataProvider {

    public static HashMap<String, List<EatenFood>> getInfo() {

        HashMap<String, List<EatenFood>> FoodDetails = new HashMap<String, List<EatenFood>>();
        List<EatenFood> breakfast = new ArrayList<EatenFood>();
        breakfast.add(new EatenFood(200, "Rice"));
        breakfast.add(new EatenFood(333, "Bread"));
        breakfast.add(new EatenFood(444, "Eggs"));
        breakfast.add(new EatenFood(555, "Bacon"));
        List<EatenFood> snacks = new ArrayList<EatenFood>();
        snacks.add(new EatenFood(132, "Cookies"));
        snacks.add(new EatenFood(553, "Kitkat"));
        snacks.add(new EatenFood(777, "Icecream"));
        snacks.add(new EatenFood(433, "Lollipop"));
        List<EatenFood> dinner = new ArrayList<EatenFood>();
        dinner.add(new EatenFood(432, "Steak"));
        dinner.add(new EatenFood(777, "Fish"));
        dinner.add(new EatenFood(443, "Spring roll"));
        dinner.add(new EatenFood(323, "Curry"));

        FoodDetails.put("Breakfast", breakfast);
        FoodDetails.put("Snacks", snacks);
        FoodDetails.put("Dinner", dinner);

        return FoodDetails;





    }




}
