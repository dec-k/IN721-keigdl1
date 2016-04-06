package bit.keigdl1.countrysearch;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
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

        //Populate dropdown box at form load
        populateDropdown();

        //Ref the button
        Button btnShowResults = (Button)findViewById(R.id.btnShowCities);

        //Bind button to a click handler
        btnShowResults.setOnClickListener(new cityClickHandler());
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
        Cursor recordSet = db.rawQuery(selectQuery, null);

        int recordCount = recordSet.getCount();

        int countryNameIndex = recordSet.getColumnIndex("countryName");

        recordSet.moveToFirst();

        for(int r=0; r < recordCount; r++){
            String countryName = recordSet.getString(countryNameIndex);
            //Add country name to list
            countries.add(countryName);

            //move to the next entry in the results of the select query
            recordSet.moveToNext();
        }

        //return list of countries for the dropdown selector
        return countries;
    }

    public class cityClickHandler implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            //Call method to output all matching countries into the listview
            populateListView();
        }
    }

    public void populateListView(){
        //Create an arraylist of cities
        ArrayList<String> cities = new ArrayList<String>();

        //Get a reference to the spinner first
        Spinner spinner = (Spinner)findViewById(R.id.spinnerCountries);

        //Extract the currently selected item from the spinner
        String selectedText = spinner.getSelectedItem().toString();

        //Run a method to build a select string, based on the value pulled from the spinner
        cities = getCitiesOfCountry(selectedText);

        //Create an adapter for the listview
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,R.layout.support_simple_spinner_dropdown_item,cities);

        //Ref & bind listview
        ListView lv = (ListView)findViewById(R.id.listView);
        lv.setAdapter(adapter);

    }

    public ArrayList<String> getCitiesOfCountry(String selectedCountry){
        //Declare a new arraylist
        ArrayList<String> cities = new ArrayList<String>();

        //Select all data in one blob.
        String selectQuery = "SELECT DISTINCT cityName FROM tblCity WHERE countryName = " + "\"" + selectedCountry + "\"";

        //Create a cursor object
        Cursor recordSet = db.rawQuery(selectQuery, null);

        int recordCount = recordSet.getCount();

        int cityNameIndex = recordSet.getColumnIndex("cityName");

        recordSet.moveToFirst();

        for(int r=0; r < recordCount; r++){
            String cityName = recordSet.getString(cityNameIndex);
            //Add country name to list
            cities.add(cityName);

            //move to the next entry in the results of the select query
            recordSet.moveToNext();
        }

        //return list of countries for the dropdown selector
        return cities;
    }
}
