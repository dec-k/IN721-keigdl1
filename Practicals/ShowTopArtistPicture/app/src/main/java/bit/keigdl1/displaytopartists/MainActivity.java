package bit.keigdl1.displaytopartists;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
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
    //Is there a better place to put this?
    String artistPictureURL;
    ImageView artistImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Ref btn
        Button btnShowArtists = (Button)findViewById(R.id.btnShowArtists);
        //bind
        btnShowArtists.setOnClickListener(new customClickHandler());

        //ref imageview
        artistImageView = (ImageView)findViewById(R.id.imageView);

    }

    public void populateListView(ArrayList<String> events){


        // TODO: 4/14/2016 CODE A CUSTOM ADAPTER INSTEAD OF CONCAT STRING  

        //Create an adapter that uses the passed-in arraylist
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,events);


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

                //Access a specific key of that object
                String artistName = currentEventObject.getString("name");
                String listenerCount = currentEventObject.getString("listeners");
                //Debug: also pulls image name as string
                String imgString = currentEventObject.getString("image");

                //Merge two strings
                String artAndListens = artistName + ", " + listenerCount + ", " + imgString;

                //Add that event name to the list
                outputItems.add(artAndListens);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //Give the return list
        return outputItems;
    }

    //Custom click handler
    class customClickHandler implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            //Create secondary thread to make an API request
            AsyncAPIShowRawJSON APIThread = new AsyncAPIShowRawJSON();

            //Use .execute() to execute code tied to the second string
            APIThread.execute();
        }
    }

    //Second asynctask used to sort out the image
    //This basically mimics the android dev example
    class LastFmAsync extends AsyncTask<String,Void,Bitmap>{

        @Override
        protected Bitmap doInBackground(String... params) {
            String pictureURL = params[0];
            Bitmap bmp = null;

            try {
                URL urlObject = new URL(pictureURL);
                HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
                connection.connect();

                int responseCode = connection.getResponseCode();
                if(responseCode == 200){
                    InputStream inputStream = connection.getInputStream();
                    bmp = BitmapFactory.decodeStream(inputStream);
                }
            }catch(Exception e){e.printStackTrace();}

            return bmp;
        }

        protected void onPostExecute(Bitmap bmp){
            artistImageView.setImageBitmap(bmp);
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
                        "method=chart.gettopartists&limit=1&" +
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
            //In this post execute, we will eventually pass to another async task that will
            //sort out how to display the image.
            try{
                //Strip away the layers of JSON data until we get to the level we want.
                JSONObject jData = new JSONObject(fetchedString); //raw
                JSONObject artistObject = jData.getJSONObject("artists");
                JSONArray artistArray = artistObject.getJSONArray("artist");

                //Retrieve the first object, we only need the top 1 result.
                JSONObject artistTop = artistArray.getJSONObject(0);

                //Last.fm stores multiple sizes of its artist pictures inside an array which
                //forms the 'value' of the key 'image'. So we have to go even deeper.
                JSONArray artistImageArray = artistTop.getJSONArray("image");

                //Iterate over the list of IMAGES in the array, the size we want is "large".
                for(int i = 0; i < artistImageArray.length();i++){
                    JSONObject img = artistImageArray.getJSONObject(i);
                    //Check if the image we just got ahold of is 'large'.
                    if(img.getString("size").equals("large")){
                        //in last.fm json, #text under the image array holds the url of the picture we want!
                        artistPictureURL = img.getString("#text");
                    }
                }


            }catch (Exception e){e.printStackTrace();}

            //Create an instance of our 2nd async class that deals with images
            LastFmAsync lastfmAsync = new LastFmAsync();
            //Pass the found url into the second async class, which will handle its drawing.
            lastfmAsync.execute(artistPictureURL);
        }
    }
}
