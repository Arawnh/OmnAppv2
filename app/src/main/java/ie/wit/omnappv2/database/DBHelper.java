package ie.wit.omnappv2.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Pav on 10/03/2016.
 */
public class DBHelper extends SQLiteOpenHelper {


    //User DB attributes
    public static final String dbName = "OmnAppDB";
    public static final String tableName = "UserTable";
    public static final String ID = "ID";
    public static final String NAME = "NAME";
    public static final String WEIGHT = "WEIGHT";
    public static final String HEIGHT = "HEIGHT";
    public static final String CALORIES = "CALORIES";


    //Eaten food DB attributes
    public static final String foodID = "ID";
    public static final String FOODNAME = "FOODNAME";
    public static final String FOODCALORIES = "FOODCALORIES";
    public static final String foodTableName = "EatenFood";


    //Added food DB attributes
    public static final String newFoodID = "ID";
    public static final String newFoodCal = "CALORIES";
    public static final String newFoodName = "NAME";
    public static final String newFoodTableName = "Food";

    String sql1 = ("create table " + tableName + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,WEIGHT TEXT, HEIGHT TEXT, CALORIES TEXT)");
    String sql2 = ("create table " + newFoodTableName + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,CALORIES TEXT)");
    String sql3 = ("create table " + foodTableName + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,FOODNAME TEXT,FOODCALORIES TEXT)");
    String[] statements = new String[]{sql1,sql2,sql3};


    public DBHelper(Context context) {
        super(context, dbName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

            //db.execSQL("create table " + tableName + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,WEIGHT TEXT, HEIGHT TEXT, CALORIES TEXT)");
            //db.execSQL("create table "+ foodTableName + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,FOODNAME TEXT,FOODCALORIES TEXT)");
            //db.execSQL("create table " + newFoodTableName + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,CALORIES TEXT)");

            for(String sql : statements) {
                db.execSQL(sql);
            }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + tableName);
        onCreate(db);
    }

    public boolean insertData(String name, String weight, String height, String calories)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cntValue = new ContentValues();
        cntValue.put(NAME,name);
        cntValue.put(WEIGHT,weight);
        cntValue.put(HEIGHT, height);
        cntValue.put(CALORIES, calories);
        long result = db.insert(tableName,null,cntValue);

        if(result==-1) {
        return false;
        }
        else {
            return true;
        }
    }


    public boolean updateData(String id, String name, String weight, String height, String calories)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cntValue = new ContentValues();

        cntValue.put(ID,id);
        cntValue.put(NAME,name);
        cntValue.put(WEIGHT,weight);
        cntValue.put(HEIGHT, height);
        cntValue.put(CALORIES, calories);
        db.update(tableName, cntValue, "ID = ?",new String[] { id });


        return true;
    }

    public boolean updateCalories(String id, String calories)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cntValue = new ContentValues();

        cntValue.put(ID,id);
        cntValue.put(CALORIES, calories);


        db.update(tableName, cntValue, "ID = ?",new String[] { id });
        return true;
    }



    public Cursor getAllData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + tableName, null);
        return res;
    }

    public boolean insertFood(String name, String calories)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cntValue = new ContentValues();
        cntValue.put(newFoodName, name);
        cntValue.put(newFoodCal, calories);
        long result = db.insert(newFoodTableName,null,cntValue);

        if(result==-1) {
            return false;
        }
        else {
            return true;
        }
    }

    public Cursor getAllFood()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + newFoodTableName, null);
        return res;
    }

    public boolean eatFood(String name, String calories)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cntValue = new ContentValues();
        cntValue.put(FOODNAME, name);
        cntValue.put(FOODCALORIES, calories);
        long result = db.insert(foodTableName,null,cntValue);

        if(result==-1) {
            return false;
        }
        else {
            return true;
        }
    }

    public Cursor getEatenFood()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + foodTableName, null);
        return res;
    }

    public boolean deleteEatenFood()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ foodTableName);
        return true;

    }


}
