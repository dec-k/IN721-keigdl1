package bit.keigdl1.datapassing;

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

        //Get button ref
        Button btnSettings = (Button) findViewById(R.id.btnSettings);
        //Bind click handler to settings button
        btnSettings.setOnClickListener(new GoToSettingsHandler());

        //Fetch the intent
        Intent launchIntent = getIntent();
        //Retrieve extra data by accessing its key
        String uName = launchIntent.getStringExtra("username");

        //Get ref to label
        TextView txtUsername = (TextView) findViewById(R.id.txtUsername);
        //Put the retrieved data into the textview
        txtUsername.setText(uName);
    }

    public class GoToSettingsHandler implements View.OnClickListener{
        @Override
        public void onClick(View v){
            //Declare intent
            Intent changeActivity = new Intent(FirstActivity.this, SecondActivity.class);
            //Swap intents
            startActivity(changeActivity);
        }
    }
}
