package bit.keigdl1.complexhandler_spinner_22;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SplashAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //Ref for button
        Button startApp = (Button) findViewById(R.id.btnStart);
        //Add click listener to button ref
        startApp.setOnClickListener(new startClickedController());
    }

    public class startClickedController implements View.OnClickListener{
        @Override
        public void onClick(View v){
            //Create an intent
            Intent endSplashScreen = new Intent(SplashAct.this, MainActivity.class);
            //Change activities by using the intent declared above.
            startActivity(endSplashScreen);
        }
    }
}
