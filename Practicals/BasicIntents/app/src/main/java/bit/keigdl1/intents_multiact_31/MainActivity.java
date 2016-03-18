package bit.keigdl1.intents_multiact_31;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Grab a ref to button control
        Button btnNextAct = (Button) findViewById(R.id.btnNextAct);
        //Bind the click listener
        btnNextAct.setOnClickListener(new btnClickHandler());
    }

    public class btnClickHandler implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            //Create an intent which is used to move from activity A to B.
            Intent bIntent = new Intent(MainActivity.this, ActivityB.class);
            //Start the specified activity
            startActivity(bIntent);
        }
    }
}
