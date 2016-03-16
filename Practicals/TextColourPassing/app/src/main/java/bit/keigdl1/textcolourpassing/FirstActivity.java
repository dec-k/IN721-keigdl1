package bit.keigdl1.textcolourpassing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        //ref btn
        Button btnSettings = (Button) findViewById(R.id.btnChangeColour);
        //bind
        btnSettings.setOnClickListener(new StartForResultHandler());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == 0){
            //ref textview
            TextView txtColour = (TextView) findViewById(R.id.txtIpsum);

            //get int result
            int result = data.getIntExtra("colourHex",0);
            txtColour.setTextColor(result);
        }
    }

    public class StartForResultHandler implements View.OnClickListener{
        @Override
        public void onClick(View v){
            //Setup intent
            Intent resultIntent = new Intent(FirstActivity.this,SecondActivity.class);
            //Start an activity which is only instantiated for a result, the activity is never shown on-screen.
            startActivityForResult(resultIntent,0);
        }
    }


}
