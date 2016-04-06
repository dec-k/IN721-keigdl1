package bit.keigdl1.listfromfile;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //declare arraylist
    ArrayList<String> cityList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //instantiate citylist
        cityList = new ArrayList<String>();

        //Call list generator method
        generateCityList();

        //Make array adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,cityList);

        //bind adapter to lv
        ListView lv = (ListView)findViewById(R.id.lvStuff);
        lv.setAdapter(adapter);
    }

    public void generateCityList(){
        //Asset file name
        String assetFileName = "citiesoftheworld.txt";

        //Create an assetmanager
        AssetManager am = getAssets();

        //Input stream
        try {
            //Ain't got no idea what any of this does fam
            InputStream is = am.open(assetFileName);
            InputStreamReader ir = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(ir);

            //Declare string to save a read in city into
            String newCity;

            //iterate over file lines
            while((newCity = br.readLine()) != null) {
                //Add the new city to the list
                cityList.add(newCity);
            }

            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
