package bit.keigdl1.displaytopartists;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Inner async class
    class AsyncAPIShowRawJSON extends AsyncTask<Void,Void,String>{

        @Override
        protected String doInBackground(Void... params) {
            //Declare json string, this will eventually return a raw json string
            String JSONString = null;

            try{
                //URL of the location we want to fetch json from
                String jsonSourceURL = "http://ws.audioscrobbler.com/2.0/?" + //base url
                        "method=chart.gettopartists&" + //method call
                        "api_key=58384a2141a4b9737eacb9d0989b8a8c&limit=10&" + //patricia's api key
                        "format=json"; //output format

                //Convert string to URLObject
                URL URLObject = new URL(jsonSourceURL);

                //Create httpURLconnection object that holds the url object
                HttpURLConnection connection = (HttpURLConnection) URLObject.openConnection();

                //Call connect on the urlconnect object, this pings the server being like:
                //"ay dog you got any more of those urls?"
                connection.connect();

                //Fetch response code, 200 = all good. != 200? You done goofed.
                int responseCode = connection.getResponseCode();

            }catch(Exception e){e.printStackTrace();}
        }
    }
}
