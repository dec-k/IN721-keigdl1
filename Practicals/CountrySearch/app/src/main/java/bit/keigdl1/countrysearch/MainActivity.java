package bit.keigdl1.countrysearch;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //Declare an sqlite database
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Run method queries to create integrated database
        createTable();
        populateTable();

        //Populate form controls at load
        populateDropdown();
    }

    public void createTable(){
        //instantiate db
        db = openOrCreateDatabase("countries",MODE_PRIVATE,null);

        //create table
        String createQuery = "CREATE TABLE IF NOT EXISTS tblCity("+
                            "cityID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            "cityName TEXT NOT NULL, " +
                            "countryName TEXT NOT NULL);";

        //Execute table create query
        db.execSQL(createQuery);
    }

    private void populateTable() {
        //insert dummy data into table - bad form, data is not parameterised.
        db.execSQL("INSERT INTO tblCity VALUES(null,'Wellington','New Zealand')");
        db.execSQL("INSERT INTO tblCity VALUES(null,'Auckland','New Zealand')");
        db.execSQL("INSERT INTO tblCity VALUES(null,'Brisbane','Australia')");
        db.execSQL("INSERT INTO tblCity VALUES(null,'Sydney','Australia')");
        db.execSQL("INSERT INTO tblCity VALUES(null,'Tokyo','Japan')");
        db.execSQL("INSERT INTO tblCity VALUES(null,'Another Place','Japan')");
    }

    public void populateDropdown(){
        //ref spinner
        Spinner spinner = (Spinner)findViewById(R.id.spinnerCountries);

        //Get list of countries in the database
        ArrayList<String> availableCountries = getCountries();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,R.layout.support_simple_spinner_dropdown_item,availableCountries);
        spinner.setAdapter(adapter);
    }

    public ArrayList<String> getCountries(){
        //Declare a new arraylist
        ArrayList<String> countries = new ArrayList<String>();

        //Select all data in one blob.
        String selectQuery = "SELECT DISTINCT countryName FROM tblCity";

        //Create a cursor object
        Cursor recordSet = db.rawQuery(selectQuery,null);

        int recordCount = recordSet.getCount();

        int countryNameIndex = recordSet.getColumnIndex("countryName");

        recordSet.moveToFirst();

        for(int r=0; r < recordCount; r++){
            String countryName = recordSet.getString(countryNameIndex);
            countries.add(countryName);

            recordSet.moveToNext();
        }

        return countries;
    }
}
