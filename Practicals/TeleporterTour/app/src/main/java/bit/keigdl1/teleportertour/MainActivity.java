package bit.keigdl1.teleportertour;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
            //Generate a lat and a lng, then feed them to a displayer method.
            double lat = genLocParameter(90);
            double lng = genLocParameter(180);

            //Populate both fields on the form.
            populateLatLng(lat, lng);
        }
    }
}
