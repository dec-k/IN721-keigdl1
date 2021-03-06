package bit.keigdl1.teleportertour;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.Console;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    double lat;
    double lng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Ref and bind btn
        Button btnTele = (Button) findViewById(R.id.btnTele);
        btnTele.setOnClickListener(new teleportClickHandler());

        //Loc manager setup
        //Create a location manager instance
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        //Create a criteria instance, criteria types hold criterium for determining the GPS method to use.
        Criteria cri = new Criteria();
        //Set provider info
        String providerName = LocationManager.NETWORK_PROVIDER;

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //This is where I could code dialog windows asking the user for permissions.
            //Having this stub here w/o said code prevents a compiler error.
            return;
        }
        lm.requestLocationUpdates(providerName, 400, 1, new customLocListener());

        //Set a last known location
        Location loc = lm.getLastKnownLocation(providerName);
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

    private class customLocListener implements LocationListener{
        @Override
        public void onLocationChanged(Location location){
            lat = location.getLatitude();
            lng = location.getLongitude();
            //Setup async processing to get nearby city and display.
            AsyncFetchNearestCityNoFail APIThread = new AsyncFetchNearestCityNoFail(MainActivity.this);

            //Execute async call
            APIThread.execute();
        }

        //Other methods not used
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }
        @Override
        public void onProviderEnabled(String provider) {

        }
        @Override
        public void onProviderDisabled(String provider) {

        }
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
            AsyncFetchNearestCityNoFail APIThread = new AsyncFetchNearestCityNoFail(MainActivity.this);

            //Execute async call
            APIThread.execute();
        }
    }

    private String buildJSONfromURL(String url){
        String jString = null;

        try {
            //Create a url object
            URL URLObject = new URL(url);

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

            if(jString.equals("[[]]")){
                return "f";
            }
        }
        catch(Exception e){e.printStackTrace();}

        return jString;
    }

    private String buildImageURL(String cityName){
        String imgURL = null;

        String flickrApiCall = "https://api.flickr.com/services/rest/?" +
                "method=flickr.photos.search" +                 //API Method
                "&api_key=1ea787f450e9b9b35ab211f8ca1a4dcd" +   //API Key
                "&tags=" + cityName +                           //Search tag
                "&format=json" +                                //Output Format
                "&nojsoncallback=1";                            //Prevents that weird text appended to flickr calls.

        //Call a method to convert the URL into a json object
        flickrApiCall = buildJSONfromURL(flickrApiCall);

        //Begin building a flickr image url in accordance w/ api guidelines
        try{
            //Raw json -> object
            JSONObject rawData = new JSONObject(flickrApiCall);

            //grab value of object defined
            JSONObject rawObj = rawData.getJSONObject("photos");

            //Inside photos, there is an array called 'photo', get it.
            JSONArray photoArray = rawObj.getJSONArray("photo");

            if(photoArray.length() > 0){
                //Get only the first result of the photo array
                JSONObject firstPhoto = photoArray.getJSONObject(0);

                //Begin extracting details of the photo object required to build a url
                String fID = firstPhoto.getString("farm");      //Server farm ID
                String sID = firstPhoto.getString("server");    //Server ID
                String pID = firstPhoto.getString("id");        //Photo ID
                String sec = firstPhoto.getString("secret");    //(Photo's) Secret ID

                //Concatenate those strings to match required url format
                imgURL = "https://farm" + fID +         //Farm ID
                        ".staticflickr.com/" + sID +   //Server ID
                        "/" + pID + "_" + sec +        //Photo ID + Secret
                        "_m.jpg";                        //Desired Output format
            }else{
                imgURL = "f";
            }
        }catch(JSONException e){e.printStackTrace();}

        //Return the generated img url
        return imgURL;
    }

    private Bitmap bmpFromUrl(String imgURL){
        //Declare an empty bmp
        Bitmap img = null;

        try{
            //String to URLObj
            URL URLObject = new URL(imgURL);

            //Declare connection & connect.
            HttpURLConnection connection = (HttpURLConnection) URLObject.openConnection();
            connection.connect();

            //Test response code
            int responseCode = connection.getResponseCode();

            //200 == successful request
            if(responseCode == 200){
                InputStream is = connection.getInputStream();
                img = BitmapFactory.decodeStream(is);
            }

        }catch(java.io.IOException e){e.printStackTrace();}

        return img;
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

            //Url of where we retrieve json data from.
            String requestURL = "http://www.geoplugin.net/extras/location.gp?" +
                    "lat=" + lat +
                    "&long=" + lng +
                    "&format=json";

            return jString;
        }

        @Override
        protected void onPostExecute(String fetchedString){
            String cData = extractCityFromJson(fetchedString);

            populateCityName(cData);

            populateLatLng(lat, lng);
        }
    }

    class AsyncFetchNearestCityNoFail extends AsyncTask<String,Void,String> {
        boolean viableImage;

        String cityimgURL;
        Bitmap imgBMP;

        //Create an instance of a progress dialog that will be called on this thread
        ProgressDialog pd;

        public AsyncFetchNearestCityNoFail(MainActivity activity) {
            pd = new ProgressDialog(activity);
        }

        @Override
        protected void onPreExecute(){
            pd.setMessage("Finding Location...");
            pd.show();
        }

        @Override
        protected String doInBackground(String... params) {
            String jString = null;

            while(extractCityFromJson(jString) == (null)){
                /*Generate lat and lng vals
                lat = genLocParameter(90);
                lng = genLocParameter(180);*/

                //Url of where we retrieve json data from.
                String requestURL = "http://www.geoplugin.net/extras/location.gp?" +
                        "lat=" + lat +
                        "&long=" + lng +
                        "&format=json";

                jString = buildJSONfromURL(requestURL);

                //attempt to establish an image from the city in question
                cityimgURL = buildImageURL(extractCityFromJson(jString));

                if(jString.equals("f")){
                    //Return early if jString returned an 'f' value. Means viable location missing.
                    return jString;
                }

                if(cityimgURL == null || cityimgURL.equals("f")){
                    viableImage = false;
                }else{
                    viableImage = true;
                    imgBMP = bmpFromUrl(cityimgURL);
                }
            }

            return jString;
        }

        @Override
        protected void onPostExecute(String fetchedString){
            //Ref textbox, used if flickr cant return a solid image.
            TextView txtFailed = (TextView)findViewById(R.id.txtFailedLoad);

            //Close the progress dialog if it is currently showing, very fancy!
            if(pd.isShowing()){
                pd.dismiss();
            }

            //Populate fields
            if(fetchedString.equals("f")){
                //You in the wopwops. :japanese_goblin:
                populateCityName("The WopWops");
            }else{
                populateCityName(extractCityFromJson(fetchedString));
            }
            populateLatLng(lat, lng);

            if(viableImage){
                populateImageView(imgBMP);
                txtFailed.setText("");
            }else{
                //ref image view
                ImageView img = (ImageView)findViewById(R.id.imgLoc);
                img.setImageResource(0);

                txtFailed.setText("No Viable Image.");
            }

        }
    }

    private void populateImageView(Bitmap bmp){
        //Ref picturebox
        ImageView img = (ImageView)findViewById(R.id.imgLoc);
        img.setImageBitmap(bmp);
    }

    private String extractCityFromJson(String jString){
        String retCity = null;
        String cName;
        String cCountryCode;

        try{
            if (jString == null){
                return null;
            }else{
                //Convert string to jsonObject
                JSONObject cObject = new JSONObject(jString);

                //Pluck some vals out of it
                cName = cObject.optString("geoplugin_place");
                cCountryCode = cObject.optString("geoplugin_countryCode");
            }

            //Build a formatted string
            retCity = cName; //TODO return city name and country code w/o breaking flickr search
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
