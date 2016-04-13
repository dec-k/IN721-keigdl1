package bit.keigdl1.displaytopartists;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.Console;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

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
            //Debug: checking api call + method works
            Toast.makeText(MainActivity.this, fetchedString, Toast.LENGTH_LONG).show();
            System.out.println(fetchedString);

        }
    }
}
