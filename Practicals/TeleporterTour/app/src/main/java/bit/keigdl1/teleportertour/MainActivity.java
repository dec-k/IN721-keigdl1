package bit.keigdl1.teleportertour;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Ref and bind btn
        Button btnTele = (Button)findViewById(R.id.btnTele);
        btnTele.setOnClickListener(new teleportClickHandler());
    }

    private double genLocParameter(double param){
        Random r = new Random();
        double genVal = r.nextDouble();

        //(maxlat * 2) * genval will give a range of 0~180.
        //90 is then flatly substracted from this value.
        //This allows us to generate random numbers that include negatives.
        double latlngCalc = ((param * 2) * genVal) - param;

        return latlngCalc;
    }

    private void populateLatLng(double lat, double lng){
        //Ref text fields
        TextView txtLat = (TextView) findViewById(R.id.txtLat);
        TextView txtLng = (TextView) findViewById(R.id.txtLong);

        //Populate, displaying only 3 decimal places. the info of non-displayed decimals is not lost.
        txtLat.setText("" + new DecimalFormat("##.###").format(lat));
        txtLng.setText("" + new DecimalFormat("##.###").format(lng));
    }

    public class teleportClickHandler implements View.OnClickListener{

        @Override
        public void onClick(View v) {

            //Setup async processing to get nearby city and display.
            AsyncFetchNearestCity APIThread = new AsyncFetchNearestCity();

            //Execute async call
            APIThread.execute();

            /*
            //Generate a lat and a lng, then feed them to a displayer method.
            double lat = genLocParameter(90);
            double lng = genLocParameter(180);

            //Populate both fields on the form.
            populateLatLng(lat, lng);
            */
        }
    }

    class AsyncFetchNearestCity extends AsyncTask<String,Void,String> {
        double lat;
        double lng;

        @Override
        protected String doInBackground(String... params) {
            //Generate lat and lng vals
            lat = genLocParameter(90);
            lng = genLocParameter(180);

            String jString = null;

            try {

                //Url of where we retrieve json data from.
                String requestURL = "http://www.geoplugin.net/extras/location.gp?" +
                        "lat=" + lat +
                        "&long=" + lng +
                        "&format=json";

                //Create a url object
                URL URLObject = new URL(requestURL);

                //Create http url connection
                HttpURLConnection connection = (HttpURLConnection)URLObject.openConnection();

                //Send url
                connection.connect();

                //Behold, the joys of java.
                InputStream is = connection.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);

                //Read input into var
                String responseString;
                StringBuilder sb = new StringBuilder();

                //Step through retrieved json and add it to sb
                while((responseString = br.readLine()) != null){
                    sb = sb.append(responseString);
                }

                //turn the sb to reg string
                jString = sb.toString();

            }
            catch(Exception e){e.printStackTrace();}

            return jString;
        }

        @Override
        protected void onPostExecute(String fetchedString){
            String cData = extractCityFromJson(fetchedString);

            populateCityName(cData);

            populateLatLng(lat,lng);
        }
    }

    private String extractCityFromJson(String jString){
        String retCity = "";

        String cName;
        String cCountryCode;

        try{
            if (jString.equals("[[]]")){
                cName = "Bad Loc";
                cCountryCode = "??";
            }else{
                //Convert string to jsonObject
                JSONObject cObject = new JSONObject(jString);

                //Pluck some vals out of it
                cName = cObject.getString("geoplugin_place");
                cCountryCode = cObject.getString("geoplugin_countryCode");
            }
            
            retCity = cName + ", " + cCountryCode;
        }
        catch(JSONException e){
            e.printStackTrace();
        }

        return retCity;
    }

    private void populateCityName(String cData){
        //Ref textfield for city
        TextView txtCity = (TextView)findViewById(R.id.txtLocName);

        //Update
        txtCity.setText(cData);
    }
}
