package bit.keigdl1.displaytopartists;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.Console;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Ref btn
        Button btnShowArtists = (Button)findViewById(R.id.btnShowArtists);
        //bind
        btnShowArtists.setOnClickListener(new customClickHandler());

    }

    //Method to return a parsed-down json string as an array list
    public ArrayList<String> rawJSONToArrayList(String jInput){
        //Setup a return list
        ArrayList<String> outputItems = new ArrayList<String>();

        try {
            //Create a new JSONObject from the passed in string
            JSONObject eventData = new JSONObject(jInput);

            //Save the event object by its key (events)
            JSONObject eventObject = eventData.getJSONObject("artists");

            //Now get the 'value' of the above object (which is an array called 'event')
            JSONArray objectArray = eventObject.getJSONArray("artist");

            //Get a count of the amount of array items to loop through
            int nEvents = objectArray.length();

            //Loop through the objectArray
            for (int i=0; i < nEvents; i++){
                //Grab an element from the array
                JSONObject currentEventObject = objectArray.getJSONObject(i);

                //Access a specific key of that object (title)
                String eventName = currentEventObject.getString("name");
                String listenerCount = currentEventObject.getString("listeners");

                //Add that event name to the list
                outputItems.add(eventName);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //Give the return list
        return outputItems;
    }

    class customClickHandler implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            //Create secondary thread to make an API request
            AsyncAPIShowRawJSON APIThread = new AsyncAPIShowRawJSON();

            //Use .execute() to execute code tied to the second string
            APIThread.execute();
        }
    }

    //Inner async class
    class AsyncAPIShowRawJSON extends AsyncTask<Void,Void,String>{

        @Override
        protected String doInBackground(Void... params) {
            //Declare json string, this will eventually return a raw json string
            String JSONString = null;

            try{
                //URL of the location we want to fetch json from
                String jsonSourceURL = "http://ws.audioscrobbler.com/2.0/?" +
                        "method=chart.gettopartists&limit=20&" +
                        "api_key=5cff6bd4a02a240bbfef15567f21c45d&" +
                        "format=json";

                //Convert string to URLObject
                URL URLObject = new URL(jsonSourceURL);

                //Create httpURLconnection object that holds the url object
                HttpURLConnection connection = (HttpURLConnection) URLObject.openConnection();

                //Send url
                connection.connect();

                //Fetch response code, 200 = all good. != 200? You done goofed.
                int responseCode = connection.getResponseCode();

                //Java filereader fluff time
                InputStream inputStream = connection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                //Read the input to a variable
                String responseString;
                StringBuilder sb = new StringBuilder();

                //.readline() through the retrieved json
                while((responseString = bufferedReader.readLine()) != null){
                    sb = sb.append(responseString);
                }

                //Turn the sb into a regular string
                JSONString = sb.toString();

            }//End of try
            catch(Exception e){e.printStackTrace();}

            //Return the raw json string
            return JSONString;
        }

        //OnPostExecute does not run on a seperate thread. The system will automatically call this
        //method and provide it the returned string of doInBackground that we wrote above.
        @Override
        protected void onPostExecute(String fetchedString){
            

        }
    }
}
