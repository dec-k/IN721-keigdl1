package bit.keigdl1.formattedfile;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
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

    //String that contains a very long JSON file
    String JSONInput = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Ref button
        Button btn = (Button)findViewById(R.id.btnLoadList);

        //Bind handler to button
        btn.setOnClickListener(new onEventLoadHandler());
    }

    public class onEventLoadHandler implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            //Build a string that holds the entire JSON file
            JSONInput = makeJSONString(JSONFileName);

            //Convert the JSON string into an arraylist of events
            ArrayList<String> eventList = jStringToArray(JSONInput);

            //Setup the listview using the above arraylist
            populateListView(eventList);
        }
    }

    public void populateListView(ArrayList<String> events){
        //ref list
        ListView eventList = (ListView)findViewById(R.id.listEvents);

        //Create an adapter that uses the passed-in arraylist
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,events);

        //bind adapter to list
        eventList.setAdapter(adapter);
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
        //Setup a return list
        ArrayList<String> outputItems = new ArrayList<String>();

        try {
            //Create a new JSONObject from the passed in string
            JSONObject eventData = new JSONObject(JSONInput);

            //Save the event object by its key (events)
            JSONObject eventObject = eventData.getJSONObject("events");

            //Now get the 'value' of the above object (which is an array called 'event')
            JSONArray objectArray = eventObject.getJSONArray("event");

            //Get a count of the amount of array items to loop through
            int nEvents = objectArray.length();

            //Loop through the objectArray
            for (int i=0; i < nEvents; i++){
                //Grab an element from the array
                JSONObject currentEventObject = objectArray.getJSONObject(i);

                //Access a specific key of that object (title)
                String eventName = currentEventObject.getString("title");

                //Add that event name to the list
                outputItems.add(eventName);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //Give the return list
        return outputItems;
    }
}
