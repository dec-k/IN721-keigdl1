package bit.keigdl1.formattedfile;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //Filename to get json data from
    public static final String JSONFileName = "dunedin_events.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get the specified filename as a JSON string and convert it to an ArrayList
        jStringToArray(makeJSONString(JSONFileName));
    }

    public String makeJSONString(String fileName){
        //The asset filename as a string
        String assetFileName = fileName;

        //String which will be loaded with JSON data and returned
        String JSONInput = "";

        //Create an asset manager
        AssetManager am = getAssets();

        //Mandatory try-catch. Java is strange.
        try {
            //Create an input stream so we can access the file's contents in a raw form
            InputStream inputStream = am.open(assetFileName);

            //Assess the number of bytes in the file we're going to read in
            int fileSizeInBytes = inputStream.available();
            byte[] JSONBuffer = new byte[fileSizeInBytes];

            //Read the stream into the buffer and then close it
            inputStream.read(JSONBuffer);
            inputStream.close();

            //Convert that byte array into a very very long string
            JSONInput = new String(JSONBuffer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Return the json input string
        return JSONInput;
    }

    public ArrayList<String> jStringToArray(String JSONInput){
        //Setup a return list -- debug
        ArrayList<String> outputItems = new ArrayList<String>();

        try {
            //Create a new JSONObject from the passed in string
            JSONObject eventData = new JSONObject(JSONInput);

            //Save the event object by its key (events)
            JSONObject eventObject = eventData.getJSONObject("events");

            //Now get the 'value' of the above object (which is an array called 'event')
            JSONArray objectArray = eventObject.getJSONArray("event");

            //DEBUG: Get the object in the first slot
            JSONObject firstSlotItem = objectArray.getJSONObject(1);

            //DEBUG: Toast output
            Toast.makeText(this,firstSlotItem.toString(),Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //Give the return list -- debug
        return outputItems;
    }
}
